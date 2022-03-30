package andres.rangel.jobreadinessmeli

import andres.rangel.jobreadinessmeli.databinding.ItemProductBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(private val items: List<Item>, private val onClickListener: (Item) -> Unit):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount() = items.size

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemProductBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(item: Item, onClickListener: (Item) -> Unit){
            val brand = item.body?.details?.get(0)?.valueName
            val condition = item.body?.details?.get(1)?.valueName
            val country = item.body?.location?.country?.name
            val city = item.body?.location?.city?.name

            binding.tvTitle.text = item.body?.title ?: ""
            binding.tvPrice.text = "$ ${item.body?.price.toString()}"
            binding.tvDetails.text = "Brand: $brand - $condition"
            binding.tvUbication.text = "$country, $city"

            Glide.with(itemView.context).load(item.body?.pictures?.get(0)?.url).into(binding.ivPicture)

            itemView.setOnClickListener{
                onClickListener(item)
            }
        }
    }
}
