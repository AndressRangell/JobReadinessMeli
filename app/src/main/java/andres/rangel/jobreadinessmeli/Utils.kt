package andres.rangel.jobreadinessmeli

import andres.rangel.jobreadinessmeli.Favorites.favoriteList
import andres.rangel.jobreadinessmeli.databinding.ActivityProductDetailsBinding
import android.app.Activity
import android.content.Context

fun String.aplyPreferences(activity: Activity) {
    val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
    sharedPref.edit().putString(this, this).apply()
}

fun String.changeStateFavorite(binding: ActivityProductDetailsBinding){
    if(favoriteList.contains(this)) {
        binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
    }
}

fun String.addFavorite(binding: ActivityProductDetailsBinding) {
    if(favoriteList.contains(this)) {
        favoriteList.remove(this)
        binding.ivFavorite.setImageResource(R.drawable.ic_favorite_border)
    }else{
        favoriteList.add(this)
        binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
    }
}

object Favorites {
    val favoriteList: ArrayList<String> = arrayListOf()
}