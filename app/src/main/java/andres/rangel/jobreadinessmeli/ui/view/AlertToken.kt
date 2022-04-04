package andres.rangel.jobreadinessmeli.ui.view

import andres.rangel.jobreadinessmeli.R
import andres.rangel.jobreadinessmeli.data.network.TokenInterceptor
import andres.rangel.jobreadinessmeli.databinding.AlertTokenBinding
import android.app.Activity
import android.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class AlertToken {

    fun showAlertToken(activity: Activity) {

        val view = activity.layoutInflater.inflate(R.layout.alert_token, null)
        val binding = AlertTokenBinding.bind(view)

        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()

        binding.tvAccept.setOnClickListener {
            val token = binding.etToken.text.toString()
            if (token.length == 37 && token.contains("TG-"))
                TokenInterceptor().getToken(token) {
                    if(it){
                        dialog.dismiss()
                    }else{
                        Snackbar.make(
                            binding.root,
                            binding.root.context.getString(R.string.error_getting_token),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            else
                Snackbar.make(
                    binding.root,
                    binding.root.context.getString(R.string.invalid_token),
                    Snackbar.LENGTH_SHORT
                ).show()
        }

        binding.tvCancel.setOnClickListener {
            dialog.dismiss()
        }

    }
}