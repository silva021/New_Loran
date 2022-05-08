package com.silva021.toolkit.extension;

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.silva021.toolkit.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun TextView.setValueOrDefault(value: String?, defaultText: String): TextView {
    text = value ?: defaultText
    return this
}

fun ImageView.load(url: String?) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_abstract_user)
        .error(R.drawable.ic_abstract_user)
        .into(this)
}

fun ImageView.load(url: String?, onSuccess: () -> Unit, onError: () -> Unit) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_abstract_user)
        .error(R.drawable.ic_abstract_user)
        .into(
            this,
            object : Callback {
                override fun onSuccess() {
                    onSuccess()
                }

                override fun onError(e: Exception?) {
                    onError()
                }

            }
        )
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.load(url: String?, block: (String) -> Unit) {
    this.load(url)
    this.setOnClickListener {
        url?.let {
            block(url)
        }
    }
}
