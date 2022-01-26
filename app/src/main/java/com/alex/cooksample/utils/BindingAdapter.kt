package com.alex.cooksample.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.alex.cooksample.R
import com.squareup.picasso.Picasso

@BindingAdapter("android:binding_urls")
fun setImageUrl(imageView: ImageView, url: List<String>) {
    if (url.isNotEmpty()) {
        url.firstOrNull()?.let {
            Picasso.get().load(it)
                .into(imageView)
        }

    } else {
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        imageView.setImageResource(R.drawable.ic_baseline_no_photography_24)
    }
}

@BindingAdapter("android:binding_url")
fun setImageUrl(imageView: ImageView, url: String) {
    if (url.isNotEmpty()) {
        Picasso.get().load(url)
            .into(imageView)
    } else {
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        imageView.setImageResource(R.drawable.ic_baseline_no_photography_24)
    }
}