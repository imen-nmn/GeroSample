package com.imen.gero

import android.support.annotation.DrawableRes

/**
 * Created by imen_nmn on 05/01/18.
 */

class Movie (var title: String?, var description: String?, @DrawableRes var drawableId: Int){
    init {
        this.title = title

    }
}