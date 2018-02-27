package br.com.aron.upcomingmovie.utils

import android.content.Context
import android.content.DialogInterface
import android.widget.ImageView
import br.com.aron.upcomingmovie.R
import com.bumptech.glide.Glide

/**
 * Created by Aron on 25/02/2018.
 */
class Utils {

    fun showDialogWithoutCancel(context: Context, title: String, message: String, listener: DialogInterface.OnClickListener?) {
        android.support.v7.app.AlertDialog.Builder(context)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(R.string.dlg_positive_ok, listener)
                .show()
    }

    fun glideLoadCircleImage(context: Context, imageURL: String?, imageView: ImageView) {

        if (imageURL != null && !imageURL.isEmpty()) {

            Glide.with(context).load(imageURL).into(imageView)
        }

    }
}