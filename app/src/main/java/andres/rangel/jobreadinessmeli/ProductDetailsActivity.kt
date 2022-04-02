package andres.rangel.jobreadinessmeli

import andres.rangel.jobreadinessmeli.databinding.ActivityProductDetailsBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        item = Gson().fromJson(intent.getStringExtra("item"), Item::class.java)

        

    }
}