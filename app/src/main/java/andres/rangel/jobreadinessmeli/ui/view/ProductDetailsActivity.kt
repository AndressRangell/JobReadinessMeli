package andres.rangel.jobreadinessmeli.ui.view

import andres.rangel.jobreadinessmeli.data.model.Item
import andres.rangel.jobreadinessmeli.databinding.ActivityProductDetailsBinding
import andres.rangel.jobreadinessmeli.ui.viewmodel.DetailsViewModel
import andres.rangel.jobreadinessmeli.core.addFavorite
import andres.rangel.jobreadinessmeli.core.changeStateFavorite
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.gson.Gson


class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
    private val className = "MainActivity"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get the json and convert to Item
        val item = Gson().fromJson(intent.getStringExtra("item"), Item::class.java)

        item.body?.id?.changeStateFavorite(binding, this)

        val list = item.body?.pictures?.map { picture ->
            SlideModel(picture.url)
        }

        binding.apply {
            list?.let { imageSlider.setImageList(it, ScaleTypes.CENTER_INSIDE) }
            tvTitleDetail.text = item.body?.title ?: ""
            tvPriceDetail.text = "$ ${item.body?.price.toString()}"
            tvLocationDetail.text =
                "${item.body?.location?.country?.name} - ${item.body?.location?.city?.name}"

            ivFavorite.setOnClickListener {
                item.body?.id?.addFavorite(binding, applicationContext)
                Log.i(className, "product added or removed to the favorites list")
            }
            ivBack.setOnClickListener { finish() }
        }

        viewModel.getDetail(item?.body?.id ?: "")

        viewModel.description.observe(this) {
            binding.tvDescription.text = it
        }

    }
}