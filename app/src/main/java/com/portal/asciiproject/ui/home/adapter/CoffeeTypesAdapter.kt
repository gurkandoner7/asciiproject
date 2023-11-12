package com.portal.asciiproject.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import com.portal.asciiproject.R
import com.portal.asciiproject.data.ProductItem
import com.portal.asciiproject.databinding.ItemProductBinding
import com.portal.asciiproject.utilities.helper.Util

@SuppressLint("NotifyDataSetChanged")
class CoffeeTypesAdapter(
    private val context: Context,
    private val productItems: MutableList<ProductItem>
) : RecyclerView.Adapter<CoffeeTypesAdapter.CoffeeTypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeTypeViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoffeeTypeViewHolder, position: Int) {
        val productItem = productItems[position]
        holder.bind(productItem)
    }

    override fun getItemCount(): Int {
        return productItems.size
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
                ivCoffee.setImageResource(productItem.productImage ?: R.drawable.profilecat)
                tvWith.setText(productItem.productWith?.let {
                    context.getString(R.string.with).replace(Util.MAGIC_KEY,
                        it
                    )
                })
                btnAdd.setButtonText("+")
                btnAdd.setOnClickListener {
                    Log.d("Tıkladın", "Evet")
                }
            }
        }
    }
    fun addItem(items: List<ProductItem>) {
        productItems.addAll(items)
        notifyDataSetChanged()
    }
}