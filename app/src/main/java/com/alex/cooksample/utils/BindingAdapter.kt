package com.alex.cooksample.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.alex.cooksample.R
import com.squareup.picasso.Picasso

@BindingAdapter("android:binding_urls")
fun setImageUrl(imageView: ImageView, urls: List<String>?) {

    if (urls?.isNotEmpty() == true) {
        urls.firstOrNull()?.let {
            Picasso.get().load(it)
                .into(imageView)
        }

    } else {
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.drawable.ic_menu_place_holder)
    }
}

@BindingAdapter("android:binding_url")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url?.isNotEmpty() == true) {
        Picasso.get().load(url)
            .into(imageView)
    } else {
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.drawable.ic_menu_place_holder)
    }
}
@BindingAdapter("android:intText")
fun setIntText(textView: TextView, intText: Int) {
    textView.text = "$intText"
}