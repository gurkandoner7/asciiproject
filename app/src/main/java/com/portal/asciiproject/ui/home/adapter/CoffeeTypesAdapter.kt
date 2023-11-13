package com.portal.asciiproject.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.portal.asciiproject.R
import com.portal.asciiproject.data.ProductItem
import com.portal.asciiproject.databinding.ItemProductBinding
import com.portal.asciiproject.utilities.helper.Util
import java.util.Locale

@SuppressLint("NotifyDataSetChanged")
class CoffeeTypesAdapter(
    private val context: Context,
    private val productItems: MutableList<ProductItem>,
    private val onItemClicked: (ProductItem) -> Unit,
) : RecyclerView.Adapter<CoffeeTypesAdapter.CoffeeTypeViewHolder>() {
    private var filteredItems: MutableList<ProductItem> = mutableListOf()

    init {
        filteredItems.addAll(productItems)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeTypeViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoffeeTypeViewHolder, position: Int) {
        val productItem = filteredItems[position]
        holder.bind(productItem)
    }

    override fun getItemCount(): Int {
        return filteredItems.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
    }

    inner class CoffeeTypeViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productItem: ProductItem) {
            binding.apply {
                tvHeader.setText(productItem.productName)
                tvPrice.setText(productItem.productPrice)
                clItem.setOnClickListener {
                    onItemClicked(productItem)
                }
                ivCoffee.setImageResource(productItem.productImage ?: R.drawable.profilecat)
                tvWith.setText(productItem.productWith?.let {
                    context.getString(R.string.with).replace(
                        Util.MAGIC_KEY,
                        it
                    )
                })
                btnAdd.setOnClickListener {
                    Log.d("Tıkladın", "Evet")
                }
            }
        }
    }

    fun filter(query: String) {
        filteredItems.clear()

        if (query.isEmpty()) {
            filteredItems.addAll(productItems)
        } else {
            val lowercaseQuery = query.toLowerCase(Locale.getDefault())
            for (item in productItems) {
                if (item.productName?.toLowerCase(Locale.getDefault())?.contains(lowercaseQuery) == true) {
                    filteredItems.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }
    fun getFilteredItems(): List<ProductItem> {
        return filteredItems
    }

    fun clearFilter(){
        filteredItems.clear()
        filteredItems.addAll(productItems)
    }


    fun addItem(items: List<ProductItem>) {
        productItems.addAll(items)
        filteredItems.clear()
        filteredItems.addAll(productItems)
        notifyDataSetChanged()
    }
}
