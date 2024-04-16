package com.dicoding.ezkart.model

import com.dicoding.ezkart.R

data class Menu(
    val image: Int,
    val title: String,
    val price: String,
    var isFavorite: Boolean = false
)

val listMenu = listOf(
    Menu(R.drawable.img_iphone_15_pro, "iPhone 15 Pro", "Rp20.999.000"),
    Menu(R.drawable.img_iphone_15, "iPhone 15", "Rp16.499.000"),
    Menu(R.drawable.img_iphone_14_pro, "iPhone 14 Pro", "Rp17.499.000"),
    Menu(R.drawable.img_samsung_z_fold_5, "Samsung Galaxy Z Fold 5", "Rp17.999.000"),
    Menu(R.drawable.img_samsung_z_flip_5, "Samsung Galaxy Z Flip 5", "Rp24.999.000"),
    Menu(R.drawable.img_samsung_s23_ultra,"Samsung Galaxy S23 Ultra", "Rp.19.999.000"),
    Menu(R.drawable.img_xiaomi_12_pro, "Xiaomi 12 Pro", "Rp.12.999.000"),
    Menu(R.drawable.img_poco_f5, "Poco F5", "Rp4.999.000"),
    Menu(R.drawable.img_realme_gt2_pro, "Realme GT2 Pro", "Rp9.999.000"),
    Menu(R.drawable.img_realme_11_proplus_5g, "Realme 11 Pro+ 5G", "Rp6.999.000")
)