package com.portal.asciiproject.utilities.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

fun ImageView.loadImageWithUrl(url: String?) {
    url?.let {
        Glide.with(context).load(it).diskCacheStrategy(DiskCacheStrategy.ALL).into(this)
    }
}

fun ImageView.loadImageWithUrlAndPlaceHolder(url: String?, placeHolder: Int) {
    url?.let {
        Glide.with(context).load(it).placeholder(placeHolder).into(this)
    }
}
fun ImageView.loadImage(drawableRes: Int) {
    Glide.with(context).load(drawableRes).into(this)
}
fun ImageView.loadCircularImage(drawable: Int) {
    Glide.with(context).load(drawable).apply(RequestOptions.circleCropTransform()).into(this)
}

fun TextView.loadImgIntoTextView(url: String?){
    Glide.with(context).load(url).into(object : CustomTarget<Drawable>(70,70){
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            this@loadImgIntoTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(resource,null,null,null)
        }

        override fun onLoadCleared(placeholder: Drawable?) {
        }

    })


}

