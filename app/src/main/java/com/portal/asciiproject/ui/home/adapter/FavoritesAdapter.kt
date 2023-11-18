import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portal.asciiproject.R
import com.portal.asciiproject.data.ProductItem
import com.portal.asciiproject.databinding.ItemFavoriteBinding
import com.portal.asciiproject.utilities.helper.Util.Companion.MAGIC_KEY

class FavoritesAdapter(
    private val context: Context,
    private val productItems: MutableList<ProductItem?>?
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    inner class FavoritesViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productItem: ProductItem?) {
            productItem?.let { item ->
                binding.apply {
                    tvHeader.setText(item.productName)
                    tvWith.setText(item.productWith?.let {
                        context.getString(R.string.with).replace(
                            MAGIC_KEY, it
                        )
                    }
                    )
                    item.productImage?.let { ivFavorite.setImageResource(it) }
                    btnFavorite.setOnClickListener {
                       showConfirmationDialog(bindingAdapterPosition)
                    }
                }
            }
        }
    }

    fun removeItem(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            productItems?.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): FavoritesViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FavoritesViewHolder, position: Int
    ) {
        val productItem = productItems?.get(position)
        productItem?.let { holder.bind(it) }
    }
    private fun showConfirmationDialog(position: Int) {
        val builder = AlertDialog.Builder(context,R.style.AlertDialogCustom)
        builder.setTitle(context.getString(R.string.confirmation))
            .setMessage(context.getString(R.string.remove_fav))
            .setPositiveButton(context.getString(R.string.approve)) { dialog, _ ->
                if (position != RecyclerView.NO_POSITION) {
                    removeItem(position)
                }
                dialog.dismiss()
            }
            .setNegativeButton(context.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    override fun getItemCount(): Int {
        return productItems?.size ?: 0
    }
}