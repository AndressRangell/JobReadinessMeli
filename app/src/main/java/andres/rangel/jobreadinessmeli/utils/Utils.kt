package andres.rangel.jobreadinessmeli.utils

import andres.rangel.jobreadinessmeli.R
import andres.rangel.jobreadinessmeli.databinding.ActivityProductDetailsBinding
import android.content.Context
import android.content.SharedPreferences

fun String.changeStateFavorite(binding: ActivityProductDetailsBinding, context: Context) {
    val preferences: SharedPreferences = context.getSharedPreferences("IDS", Context.MODE_PRIVATE) ?: return
    if (preferences.getString(this, "") != "") {
        binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
    }
}

fun String.addFavorite(binding: ActivityProductDetailsBinding, context: Context) {
    val preferences: SharedPreferences = context.getSharedPreferences("IDS", Context.MODE_PRIVATE) ?: return
    val editor: SharedPreferences.Editor = preferences.edit()
    if (preferences.getString(this, "") != "") {
        editor.remove(this)
        binding.ivFavorite.setImageResource(R.drawable.ic_favorite_border)
    }else{
        editor.putString(this, this)
        binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
    }
    editor.apply()
}