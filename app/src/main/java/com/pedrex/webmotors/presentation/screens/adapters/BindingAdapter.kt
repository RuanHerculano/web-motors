package com.pedrex.webmotors.presentation.screens.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pedrex.webmotors.extensions.toMoney

class BindingAdapter {
    companion object {

        @JvmStatic
        @BindingAdapter(value =["bindMoney"], requireAll = false)
        fun bindMoney(textView: TextView, value: Int?) {
            value?.let {
                textView.text = it.toMoney()
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["url"], requireAll = false )
        fun loadImage(view: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide
                    .with(view.context)
                    .load(it)
                    .placeholder(android.R.drawable.picture_frame)
                    .into(view)
            }
        }
    }

}