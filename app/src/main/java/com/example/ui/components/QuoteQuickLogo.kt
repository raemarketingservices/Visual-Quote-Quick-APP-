package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.R

/**
 * Official Quote Quick Insurance Group Logo
 * Renders the authentic brand identity asset.
 */
@Composable
fun QuoteQuickLogo(
    modifier: Modifier = Modifier,
    height: Dp = 80.dp,
    lightText: Boolean = false
) {
    val resId = if (lightText) {
        R.drawable.quote_quick_logo_light
    } else {
        R.drawable.quote_quick_logo_transparent
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = "Quote Quick Insurance Group Official Logo",
            modifier = Modifier
                .height(height)
                .widthIn(max = 280.dp),
            contentScale = ContentScale.Fit
        )
    }
}

/**
 * Compact horizontal lockup for Top App Bars, Bottom Sheets, and headers.
 * Uses the authentic horizontal brand identity asset (Emblem on left + Quote Quick Insurance Group).
 */
@Composable
fun QuoteQuickLogoCompact(
    modifier: Modifier = Modifier,
    height: Dp = 30.dp,
    lightText: Boolean = false
) {
    val resId = if (lightText) {
        R.drawable.quote_quick_logo_horizontal_light
    } else {
        R.drawable.quote_quick_logo_horizontal
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = "Quote Quick Insurance Group Logo",
            modifier = Modifier
                .height(height)
                .width(height * 6.2f),
            contentScale = ContentScale.Fit
        )
    }
}

/**
 * QQ Emblem with speed lines only (Square / circular icons)
 */
@Composable
fun QuoteQuickEmblem(
    modifier: Modifier = Modifier,
    size: Dp = 36.dp
) {
    Image(
        painter = painterResource(id = R.drawable.quote_quick_emblem),
        contentDescription = "Quote Quick Emblem",
        modifier = modifier.size(size),
        contentScale = ContentScale.Fit
    )
}
