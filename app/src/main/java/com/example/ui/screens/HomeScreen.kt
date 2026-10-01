package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Weekend
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.models.AppImages
import com.example.ui.theme.QQBlue
import com.example.ui.theme.QQCyan
import com.example.ui.theme.QQDarkBlue
import com.example.ui.theme.QQNavy
import com.example.ui.theme.QQRed
import com.example.ui.theme.QQTextMuted
import com.example.ui.theme.QQTextSecondary

@Composable
fun HomeScreen(
    onStartQuote: (String) -> Unit,
    onOpenAiChat: (String?) -> Unit,
    onExploreCatalog: (String?) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp)
            .testTag("home_screen")
    ) {
        // 1. HERO BANNER SECTION (Optimized for mobile viewport)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .height(280.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            AsyncImage(
                model = AppImages.HERO_SUNSET_FAMILY,
                contentDescription = "Family watching sunset",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.90f),
                                Color.Black.copy(alpha = 0.55f),
                                Color.Transparent
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Tag
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = "Shield",
                            tint = Color(0xFF7DD3FC),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Fast Quotes. Real Agents. Real Coverage.",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                // Middle Text
                Column {
                    Box(
                        modifier = Modifier
                            .size(width = 30.dp, height = 3.dp)
                            .clip(CircleShape)
                            .background(QQRed)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "QUOTE QUICK INSURANCE",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFFE2E8F0),
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "MORE THAN INSURANCE.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        lineHeight = 22.sp
                    )
                    Text(
                        text = "A BRIGHTER TOMORROW.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF38BDF8),
                        lineHeight = 22.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Protection engineered around what matters most to your life and business.",
                        fontSize = 10.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        lineHeight = 13.sp,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = { onStartQuote("auto") },
                        colors = ButtonDefaults.buttonColors(containerColor = QQRed),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .height(38.dp)
                            .testTag("hero_get_quote_button")
                    ) {
                        Text(
                            text = "GET A FREE QUOTE",
                            fontWeight = FontWeight.Black,
                            fontSize = 10.sp,
                            letterSpacing = 0.6.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Arrow",
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }
        }

        // 2. THREE KEY PILLARS METRIC CARD
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .clip(RoundedCornerShape(14.dp))
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp)),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Metric 1: 2 Min Fast Quotes
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFEEF5FF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bolt,
                            contentDescription = "Bolt",
                            tint = QQBlue,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text("2 Min", fontSize = 10.sp, fontWeight = FontWeight.Black, color = QQDarkBlue, maxLines = 1)
                        Text("Fast Quotes", fontSize = 8.sp, color = QQTextMuted, maxLines = 1)
                    }
                }

                Box(modifier = Modifier.width(1.dp).height(24.dp).background(Color(0xFFE2E8F0)))

                // Metric 2: 100% Licensed Agents
                Row(
                    modifier = Modifier.weight(1.1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFEEF5FF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = "Shield",
                            tint = QQBlue,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text("100%", fontSize = 10.sp, fontWeight = FontWeight.Black, color = QQDarkBlue, maxLines = 1)
                        Text("Licensed Agents", fontSize = 8.sp, color = QQTextMuted, maxLines = 1)
                    }
                }

                Box(modifier = Modifier.width(1.dp).height(24.dp).background(Color(0xFFE2E8F0)))

                // Metric 3: 24/7 Claims Care
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFEEF5FF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Group,
                            contentDescription = "Group",
                            tint = QQBlue,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text("24/7", fontSize = 10.sp, fontWeight = FontWeight.Black, color = QQDarkBlue, maxLines = 1)
                        Text("Claims Care", fontSize = 8.sp, color = QQTextMuted, maxLines = 1)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 3. COMPLETE PORTFOLIOS SECTION
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "COMPLETE PORTFOLIOS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = QQRed,
                        letterSpacing = 0.8.sp
                    )
                    Text(
                        text = "WHAT CAN WE HELP YOU PROTECT?",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = QQDarkBlue
                    )
                }

                Text(
                    text = "View All ›",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = QQBlue,
                    modifier = Modifier.clickable { onExploreCatalog(null) }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 2 Per Row Grid Stack
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val portfolioList = listOf(
                    Triple(Triple("PERSONAL", "Auto, Home, Renters & Umbrella protection.", AppImages.WHITE_SUV), Icons.Default.DirectionsCar to QQBlue, "personal"),
                    Triple(Triple("COMMERCIAL", "Fleet, General Liability & Property bounds.", AppImages.COMMERCIAL_BUILDING), Icons.Default.Apartment to QQBlue, "commercial"),
                    Triple(Triple("LIFE", "Term, Whole Life & Final Expense.", AppImages.LIFE_FAMILY), Icons.Default.Favorite to QQRed, "life"),
                    Triple(Triple("RETIREMENT", "Annuities, 401(k) rollovers, & Long-Term Care.", AppImages.RETIREMENT_BOAT), Icons.Default.TrendingUp to QQBlue, "retirement")
                )
                portfolioList.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        rowItems.forEach { (first, second) ->
                            val (title, desc, img) = first
                            val (icon, color) = second
                            PortfolioCard(
                                modifier = Modifier.weight(1f),
                                title = title,
                                description = desc,
                                imageUrl = img,
                                icon = icon,
                                iconColor = color,
                                onClick = { onExploreCatalog(keyForPortfolio(title)) }
                            )
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 4. FEATURED INSURANCE SECTION
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "FAST RATES",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = QQRed,
                        letterSpacing = 0.8.sp
                    )
                    Text(
                        text = "FEATURED INSURANCE",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = QQDarkBlue
                    )
                }

                Text(
                    text = "View All ›",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = QQBlue,
                    modifier = Modifier.clickable { onExploreCatalog(null) }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val featuredList = listOf(
                    FeaturedData("Auto Insurance", "Full protection with roadside assistance, collision coverage & safe driver cash-back discounts.", "$49", "SAVE 15% BUNDLE", QQRed, AppImages.AUTO_SPORT_DARK, Icons.Default.DirectionsCar, "auto"),
                    FeaturedData("Homeowners Insurance", "Safeguard your dwelling, family belongings, and structural property from unexpected hazards.", "$68", "TOP RATED", QQBlue, AppImages.HOME_MODERN, Icons.Default.Home, "home"),
                    FeaturedData("Term Life Insurance", "Affordable fixed-rate peace of mind providing complete financial continuity for your family's future.", "$21", "BEST VALUE", QQRed, AppImages.LIFE_FATHER_KID, Icons.Default.Favorite, "life"),
                    FeaturedData("Renters Insurance", "Affordable protection for your belongings, electronics, and personal liability starting from $14/mo.", "$14", "AFFORDABLE", QQBlue, AppImages.RENTERS_COZY_ROOM, Icons.Default.Weekend, "renters")
                )
                featuredList.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowItems.forEach { item ->
                            FeaturedRateCard(
                                modifier = Modifier.weight(1f),
                                title = item.title,
                                description = item.description,
                                price = item.price,
                                badge = item.badge,
                                badgeColor = item.badgeColor,
                                imageUrl = item.imageUrl,
                                icon = item.icon,
                                onGetQuote = { onStartQuote(item.key) }
                            )
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 5. ASK QUOTE QUICK AI CARD
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFF0055D4), Color(0xFF0066E2), Color(0xFF0284C7))
                    )
                )
                .padding(16.dp)
                .testTag("ai_assistant_home_banner")
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White.copy(alpha = 0.2f))
                            .border(1.dp, Color.White.copy(alpha = 0.25f), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SmartToy,
                            contentDescription = "Robot",
                            tint = Color(0xFFBAE6FD),
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "INTELLIGENT POLICY MATCH",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFBAE6FD),
                            letterSpacing = 0.6.sp
                        )
                        Text(
                            text = "ASK QUOTE QUICK AI",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Unsure which policy is right? Chat with our instant AI assistant for real-time recommendations and coverage comparisons.",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.9f),
                            lineHeight = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Suggestion chips
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White.copy(alpha = 0.2f))
                            .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                            .clickable { onOpenAiChat("Bundle home & 2 cars") }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "\"Bundle home & 2 cars\"",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White.copy(alpha = 0.2f))
                            .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                            .clickable { onOpenAiChat("Commercial liability for LLC") }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "\"Commercial liability for LLC\"",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Start AI Chat Button
                Button(
                    onClick = { onOpenAiChat(null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.SmartToy,
                        contentDescription = "Chat",
                        tint = QQBlue,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "START AI CHAT",
                        color = QQBlue,
                        fontWeight = FontWeight.Black,
                        fontSize = 12.sp,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Go",
                        tint = QQBlue,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 6. WHY QUOTE QUICK: 5 PILLARS
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "OUR FUNDAMENTAL CORE",
                fontSize = 10.sp,
                fontWeight = FontWeight.ExtraBold,
                color = QQRed,
                letterSpacing = 0.8.sp
            )
            Text(
                text = "WHY QUOTE QUICK",
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                color = QQDarkBlue
            )
            Text(
                text = "Built upon five pillars of lasting client trust.",
                fontSize = 11.sp,
                color = QQTextMuted
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 5 Pillars row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                PillarItem(
                    modifier = Modifier.weight(1f),
                    title = "DRIVE",
                    subtitle = "Confidence",
                    icon = Icons.Default.DirectionsCar,
                    color = QQBlue
                )
                PillarItem(
                    modifier = Modifier.weight(1f),
                    title = "LOVE",
                    subtitle = "What Matters",
                    icon = Icons.Default.Favorite,
                    color = QQRed
                )
                PillarItem(
                    modifier = Modifier.weight(1f),
                    title = "GROW",
                    subtitle = "Stronger Future",
                    icon = Icons.Default.TrendingUp,
                    color = QQBlue
                )
                PillarItem(
                    modifier = Modifier.weight(1f),
                    title = "PROTECT",
                    subtitle = "Always",
                    icon = Icons.Default.Shield,
                    color = QQBlue
                )
                PillarItem(
                    modifier = Modifier.weight(1f),
                    title = "BELONG",
                    subtitle = "Together",
                    icon = Icons.Default.Group,
                    color = QQBlue
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 7. PHYSICAL LOCAL AGENCY CONTACT CARD
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .clip(RoundedCornerShape(18.dp))
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(18.dp)),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                // Storefront photo with badge
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    AsyncImage(
                        model = AppImages.AGENCY_STOREFRONT,
                        contentDescription = "Quote Quick Storefront",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.White.copy(alpha = 0.95f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Apartment,
                                contentDescription = "HQ",
                                tint = QQBlue,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "HQ Langhorne Office",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = QQDarkBlue
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "LOCAL PENNSYLVANIA AGENCY",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = QQRed,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "CONTACT A LICENSED AGENT",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = QQDarkBlue
                )
                Text(
                    text = "Real people ready to assist with custom quotes, claims, and policy questions.",
                    fontSize = 11.sp,
                    color = QQTextSecondary,
                    lineHeight = 15.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Address & Hours pills
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF8FAFC))
                            .padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Address",
                            tint = QQBlue,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "668 Woodbourne Rd STE: 110, Langhorne, PA",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = QQDarkBlue
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF8FAFC))
                            .padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Hours",
                            tint = QQBlue,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mon – Fri: 8:30 AM – 6:00 PM EST",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = QQDarkBlue
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Call Agent & Directions Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:2155550199"))
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(42.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = QQBlue)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Call",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("CALL AGENT", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    OutlinedButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:40.1748,-74.9213?q=668+Woodbourne+Rd+Langhorne+PA"))
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(42.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, QQBlue)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Directions,
                            contentDescription = "Directions",
                            tint = QQBlue,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("DIRECTIONS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                    }
                }
            }
        }
    }
}

@Composable
fun PortfolioCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    imageUrl: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color = QQBlue,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 10.dp, bottom = 4.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(iconColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    fontSize = 10.sp,
                    color = QQTextSecondary,
                    lineHeight = 13.sp,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Explore Plans", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Explore",
                        tint = QQBlue,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FeaturedRateCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    price: String,
    badge: String,
    badgeColor: Color,
    imageUrl: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onGetQuote: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(115.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Top badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(badgeColor)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = badge,
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                // Bottom pill
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.Black.copy(alpha = 0.65f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = icon,
                            contentDescription = title,
                            tint = Color(0xFF38BDF8),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = description,
                    fontSize = 10.sp,
                    color = QQTextSecondary,
                    lineHeight = 14.sp,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("STARTING AT", fontSize = 8.sp, color = QQTextMuted, fontWeight = FontWeight.Bold)
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(price, fontSize = 16.sp, fontWeight = FontWeight.Black, color = QQBlue)
                            Text("/mo", fontSize = 10.sp, color = QQTextSecondary)
                        }
                    }

                    Button(
                        onClick = onGetQuote,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = QQRed),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text("GET QUOTE", fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Quote",
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
            }
        }
    }
}

data class FeaturedData(
    val title: String,
    val description: String,
    val price: String,
    val badge: String,
    val badgeColor: Color,
    val imageUrl: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val key: String
)

fun keyForPortfolio(title: String): String {
    return when (title) {
        "PERSONAL" -> "personal"
        "COMMERCIAL" -> "commercial"
        "LIFE" -> "life"
        "RETIREMENT" -> "retirement"
        else -> "personal"
    }
}

@Composable
fun PillarItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontSize = 9.sp,
                fontWeight = FontWeight.Black,
                color = QQDarkBlue
            )
            Text(
                text = subtitle,
                fontSize = 7.sp,
                color = QQTextMuted,
                lineHeight = 9.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}
