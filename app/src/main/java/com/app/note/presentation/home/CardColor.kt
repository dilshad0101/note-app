package com.app.note.presentation.home

import androidx.compose.ui.graphics.Color

var colorList: List<Color> = emptyList()
//the values are filled once the app is started to make sure app get shuffled list of colors only on startups not while running

fun cardColor(n: Int): Color {
        val colors = colorList
        var lastColor = -1
        var colorIndex = 0

        for (i in 1..n) {
                colorIndex = (lastColor + 1) % colors.size

                if (i > 1 && colors[colorIndex] == colors[lastColor]) {
                        colorIndex = (lastColor + 2) % colors.size
                }

                lastColor = colorIndex
        }

        return colors[colorIndex]
}