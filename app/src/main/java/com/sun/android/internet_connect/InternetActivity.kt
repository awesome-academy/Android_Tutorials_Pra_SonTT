package com.sun.android.internet_connect

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.sun.android.R
import com.sun.android.databinding.ActivityInternetBinding
import org.json.JSONException
import org.json.JSONObject

class InternetActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
    val binding by lazy { ActivityInternetBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (supportLoaderManager.getLoader<String>(0) != null) {
            supportLoaderManager.initLoader(0, null, this)
        }
        binding.buttonSearch.setOnClickListener {
            val queryString: String = binding.editTextBookInput.text.toString()
            val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputManager.hideSoftInputFromWindow(
                binding.editTextBookInput.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )

            if (isNetworkAvailable(this) && queryString.isNotEmpty()) {
                val queryBundle = Bundle()
                queryBundle?.putString(R.string.query_string.toString(), queryString)
                supportLoaderManager.restartLoader(0, queryBundle, this)
                binding.textAuthor.text = ""
                binding.textTitle.setText(R.string.loading)
            } else {
                if (queryString.isEmpty()) {
                    binding.textAuthor.text = ""
                    binding.textTitle.setText(R.string.no_search_term)
                } else {
                    binding.textAuthor.text = ""
                    binding.textTitle.setText(R.string.no_network)
                }
            }
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        var queryString = ""

        if (args != null) {
            queryString = args.getString(R.string.query_string.toString()).toString()
        }

        return BookLoader(this, queryString)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String) {
        try {
            val jsonObject = JSONObject(data)
            val itemsArray = jsonObject.getJSONArray(R.string.item.toString())
            var i = 0
            var title: String? = null
            var authors: String? = null

            while (i < itemsArray.length() &&
                authors == null && title == null
            ) {
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject(R.string.volume_info.toString())

                title = volumeInfo.getString(R.string.title.toString())
                authors = volumeInfo.getString(R.string.authors.toString())

                i++
            }
            if (title != null && authors != null) {
                binding.textTitle.text = title
                binding.textAuthor.text = authors
            } else {
                binding.textTitle.setText(R.string.no_results)
                binding.textAuthor.text = ""
            }
        } catch (e: Exception) {
            binding.textTitle.setText(R.string.no_results)
            binding.textAuthor.text = ""
            e.printStackTrace()
        }

    }

    override fun onLoaderReset(loader: Loader<String>) {

    }
}
