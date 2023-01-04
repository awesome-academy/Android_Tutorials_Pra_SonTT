package com.sun.android.internet_connect

import android.content.Context
import androidx.loader.content.AsyncTaskLoader

class BookLoader(context: Context, queryString: String) : AsyncTaskLoader<String>(context) {
    private val mQueryString = queryString

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): String? {
        return NetworkUtils.getBookInfo(mQueryString)

    }
}
