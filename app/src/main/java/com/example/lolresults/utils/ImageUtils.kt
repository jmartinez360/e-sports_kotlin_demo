package com.example.lolresults.utils

import com.example.lolresults.BuildConfig


object ImageUtils {

    fun getImageUrl(id: Int) = "${BuildConfig.IMAGE_URL}${id}"

}