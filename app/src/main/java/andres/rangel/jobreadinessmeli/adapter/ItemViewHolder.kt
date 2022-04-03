package andres.rangel.jobreadinessmeli.adapter

import andres.rangel.jobreadinessmeli.data.model.Item
import andres.rangel.jobreadinessmeli.databinding.ItemProductBinding
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)
    private val className = "ItemViewHolder"

    @SuppressLint("SetTextI18n")
    fun bind(item: Item, onClickListener: (Item) -> Unit) {
        val brand = item.body?.attributes?.get(0)?.valueName
        val condition = item.body?.attributes?.get(1)?.valueName
        val country = item.body?.location?.country?.name
        val city = item.body?.location?.city?.name

        binding.apply {
            tvTitle.text = item.body?.title ?: ""
            tvPrice.text = "$ ${item.body?.price.toString()}"
            tvDetails.text = "Brand: $brand - $condition"
            tvUbication.text = "$country, $city"
        }

        try {
            Picasso.get().load(item.body?.pictures?.get(0)?.url).into(binding.ivPicture)
        } catch (e: IllegalArgumentException) {
            Log.e(className, e.message.toString())
        }

        itemView.setOnClickListener {
            onClickListener(item)
        }
    }
}