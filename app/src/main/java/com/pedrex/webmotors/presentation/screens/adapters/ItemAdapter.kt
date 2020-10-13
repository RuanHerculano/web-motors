package com.pedrex.webmotors.presentation.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pedrex.webmotors.R
import com.pedrex.webmotors.databinding.ItemsBinding
import com.pedrex.webmotors.presentation.model.ItemModel
import com.pedrex.webmotors.presentation.screens.viewmodel.CartViewModel

class ItemAdapter(
    val items: ArrayList<ItemModel>,
    private val viewModel: CartViewModel
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.items,
                    parent,
                    false
                )
        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding?.let {
            val car = items[position]
            it.car = car
            it.viewModel = viewModel
            it.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemsBinding? = DataBindingUtil.bind(view)
    }
}