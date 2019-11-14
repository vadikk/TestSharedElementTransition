package com.example.testsharedelementtransition

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val clickBlock: (view: View, model: ItemModel, secondView: View) -> Unit
): RecyclerView.Adapter<ItemLayoutHolder>() {

    private var itemList = mutableListOf<ItemModel>()

    fun addAll(list: List<ItemModel>){
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemLayoutHolder = ItemLayoutHolder.create(parent)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemLayoutHolder, position: Int) {
        val model = itemList[holder.adapterPosition]

        holder.bind(model){view, secondView ->  clickBlock(view, model, secondView)}
    }
}