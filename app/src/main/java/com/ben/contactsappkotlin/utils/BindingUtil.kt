package com.ben.contactsappkotlin.utils

import android.databinding.BindingAdapter
import android.databinding.BindingConversion
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("url")
fun loadImage(imageView: ImageView?, url: String?) {
    if (url != null && imageView != null) {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }
}

@BindingConversion
fun convertItemId(itemId: Long): String {
    return itemId.toString()
}

@BindingConversion
fun convertTime(time: Long): String {
    return DateUtil.convertDate(time)
}