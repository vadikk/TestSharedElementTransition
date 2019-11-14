package com.example.testsharedelementtransition

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class ItemLayoutHolder(view: View): RecyclerView.ViewHolder(view) {

    companion object{
        fun create(parent: ViewGroup): ItemLayoutHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            return ItemLayoutHolder(view)
        }
    }

    fun bind(model: ItemModel, click: (view: View, secondView: View) -> Unit){
        itemView.holder.transitionName = model.title
        itemView.imageView.transitionName = model.id.toString()
        itemView.imageView.setImageDrawable(ContextCompat.getDrawable(itemView.context, model.image.toInt()))
        itemView.textView2.text = model.title
        itemView.textView3.text = model.name

        itemView.setOnClickListener {
            click( itemView.imageView, itemView.holder)
        }
    }
}