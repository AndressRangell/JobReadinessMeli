package andres.rangel.jobreadinessmeli

import andres.rangel.jobreadinessmeli.databinding.ItemProductBinding
import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun bind(item: Item, onClickListener: (Item) -> Unit){
        val brand = item.body?.attributes?.get(0)?.valueName
        val condition = item.body?.attributes?.get(1)?.valueName
        val country = item.body?.location?.country?.name
        val city = item.body?.location?.city?.name

        binding.tvTitle.text = item.body?.title ?: ""
        binding.tvPrice.text = "$ ${item.body?.price.toString()}"
        binding.tvDetails.text = "Brand: $brand - $condition"
        binding.tvUbication.text = "$country, $city"

        Picasso.get().load(item.body?.pictures?.get(0)?.url).into(binding.ivPicture)

        itemView.setOnClickListener{
            onClickListener(item)
        }
    }
}