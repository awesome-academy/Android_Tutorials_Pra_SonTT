package com.sun.android.async

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.SystemClock
import android.widget.ProgressBar
import android.widget.TextView
import com.sun.android.R

import java.lang.ref.WeakReference
import kotlin.random.Random


class MyAsyncTask(textView: TextView, progressBar: ProgressBar) : AsyncTask<Void, Int, String>() {
    private var mTextView: WeakReference<TextView>? = null
    @SuppressLint("StaticFieldLeak")
    private var mProgressBar: ProgressBar
    init {
        mTextView = WeakReference(textView)
        mProgressBar = progressBar

    }

    override fun doInBackground(vararg p0: Void?): String {
        val r = Random
        val n: Int = r.nextInt(11)

        val s = n * 200
        try {
            Thread.sleep(s.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        for (i in 0..100) {
            SystemClock.sleep((s/100).toLong())
            publishProgress(i)
        }
        return "Awake at last after sleeping for $s milliseconds!"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        val number = values[0]
        if (number != null) {
            mProgressBar.progress = number
        }

    }

    override fun onPostExecute(result: String) {
        mTextView?.get()?.text = result
    }
}
