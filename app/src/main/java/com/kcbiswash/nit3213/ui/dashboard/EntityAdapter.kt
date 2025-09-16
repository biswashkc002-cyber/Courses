package com.kcbiswash.nit3213.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.kcbiswash.nit3213.databinding.ItemEntityBinding

class EntityAdapter(
    private val items: MutableList<JsonObject>,
    private val onClick: (JsonObject) -> Unit
) : RecyclerView.Adapter<EntityAdapter.VH>() {

    inner class VH(val binding: ItemEntityBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEntityBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.binding.tvSummary.text = DashboardViewModel.summaryFor(item)
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = items.size

    fun submit(list: List<JsonObject>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}
