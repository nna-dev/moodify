package com.nna.moodify.ui.binding

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import timber.log.Timber

@BindingAdapter(value = ["imageUri", "placeholder"], requireAll = false)
fun bindLoadImage(imageView: ImageView, imageUri: Uri?, placeholder: Drawable?) {
    when (imageUri) {
        null -> {
            Timber.e("Unsetting image url")
            Glide.with(imageView)
                .load(placeholder)
                .into(imageView)
        }
        else -> {
            Glide.with(imageView)
                .load(imageUri)
                .placeholder(placeholder)
                .into(imageView)
        }
    }
}
