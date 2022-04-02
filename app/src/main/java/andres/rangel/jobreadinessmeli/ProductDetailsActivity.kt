package andres.rangel.jobreadinessmeli

import andres.rangel.jobreadinessmeli.databinding.ActivityProductDetailsBinding
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.gson.Gson


class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
    private val pictureList: ArrayList<Bitmap> = arrayListOf()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = Gson().fromJson(intent.getStringExtra("item"), Item::class.java)

        val list = item.body?.pictures?.map { picture ->
            SlideModel(picture.url)
        }

        binding.apply {
            list?.let { imageSlider.setImageList(it, ScaleTypes.FIT) }
            tvTitleDetail.text = item.body?.title ?: ""
            tvPriceDetail.text = item.body?.price.toString()
            tvLocation.text = "${item.body?.location?.country?.name} - ${item.body?.location?.city?.name}"
        }

        viewModel.getDetail(item?.body?.id ?: "")

        viewModel.description.observe(this) {
            binding.tvDescription.text = it
        }

    }
}