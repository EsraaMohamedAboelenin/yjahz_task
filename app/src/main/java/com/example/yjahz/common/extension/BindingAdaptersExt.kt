package com.example.yjahz.common.extension

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.yjahz.R


@BindingAdapter(value = ["loadImageFromUrl"])
fun ImageView.loadImageFromUrl(url: String?) {
    this.run {

        if (!url.isNullOrEmpty()) {
            this.load(url)
        }else {
            this.setImageDrawable(
                ContextCompat.getDrawable(context, R.drawable.logo)
            )
        }
    }
}




