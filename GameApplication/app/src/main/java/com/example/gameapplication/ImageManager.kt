package com.example.gameapplication

class ImageManager {
    companion object {
        val EasyImageList = arrayListOf(
            R.drawable.zero,
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven,
            R.drawable.eight,
            R.drawable.nine
        )
        val UsuallyImageList = arrayListOf(
            R.drawable.zero,
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven
        )
        val HardImageList = arrayListOf(
            R.drawable.zero,
            R.drawable.one,
            R.drawable.two,
            R.drawable.four,
            R.drawable.six,
        )

        fun getImagesByType(type: Int): List<Int> {
            return when (type) {
                1 -> EasyImageList
                2 -> UsuallyImageList
                3 -> HardImageList
                else -> emptyList() // 유효하지 않은 유형일 경우 빈 리스트 반환
            }
        }
    }
}
