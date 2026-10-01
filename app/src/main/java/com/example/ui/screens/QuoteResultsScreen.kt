package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.models.AppImages
import com.example.data.models.CoveragePlanTier
import com.example.ui.theme.QQBlue
import com.example.ui.theme.QQDarkBlue
import com.example.ui.theme.QQRed
import com.example.ui.theme.QQTextMuted
import com.example.ui.theme.QQTextSecondary
import com.example.ui.viewmodels.QuoteQuickViewModel

@Composable
fun QuoteResultsScreen(
    viewModel: QuoteQuickViewModel,
    onBindPolicy: (String) -> Unit,
    onChatWithSarah: () -> Unit
) {
    val selectedTierId by viewModel.selectedTierId.collectAsState()
    val coverageTiers = viewModel.coverageTiers
    val carrierQuotes = viewModel.carrierQuotes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FB))
            .verticalScroll(rememberScrollState())
            .padding(14.dp)
            .testTag("quote_results_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // UNDERWRITING NOTIFICATION BANNER
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
            shadowElevation = 1.dp
        ) {
            Box {
                // Red left edge accent
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(80.dp)
                        .background(QQRed)
                )

                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(QQRed)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "UNDERWRITING COMPLETE",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = QQTextSecondary,
                            letterSpacing = 0.8.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Your Custom Quotes Are Ready!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        color = QQDarkBlue
                    )
                    Text(
                        text = "QQ-2026-000412 • 2024 Toyota RAV4 Hybrid (AWD)",
                        fontSize = 11.sp,
                        color = QQTextSecondary
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = Color(0xFFF1F5F9))
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f, fill = false)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFFF1F5F9))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(20.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(QQBlue)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Verified: Sarah Jenkins (Underwriter)",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = QQDarkBlue,
                                    maxLines = 1
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LockClock, "Lock", tint = QQRed, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text("Locked 48h", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQRed)
                        }
                    }
                }
            }
        }

        // CARRIER COMPARISON SUMMARY BAR
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Analytics, "Carriers", tint = QQBlue, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("8 Top Carriers Queried", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFDAE2FD))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.TrendingDown, "Savings", tint = QQBlue, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text("Save up to \$480/yr", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Carrier Pills Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    carrierQuotes.forEach { quote ->
                        val isBest = quote.isBest
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isBest) Color(0xFFEEF5FF) else Color(0xFFF8FAFC))
                                .border(
                                    width = if (isBest) 1.5.dp else 1.dp,
                                    color = if (isBest) QQBlue else Color(0xFFE2E8F0),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(vertical = 6.dp, horizontal = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                if (isBest) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(QQRed)
                                            .padding(horizontal = 4.dp, vertical = 1.dp)
                                    ) {
                                        Text("BEST", color = Color.White, fontSize = 7.sp, fontWeight = FontWeight.Black)
                                    }
                                }
                                Text(quote.carrierName, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                Row(verticalAlignment = Alignment.Bottom) {
                                    Text(
                                        "\$${quote.monthlyRate.toInt()}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Black,
                                        color = if (isBest) QQBlue else QQDarkBlue
                                    )
                                    Text("/mo", fontSize = 8.sp, color = QQTextMuted)
                                }
                            }
                        }
                    }
                }
            }
        }

        // TIER SELECTION HEADING
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Select Coverage Tier", fontSize = 15.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
            Text("3 Tailored Plans", fontSize = 11.sp, color = QQTextMuted)
        }

        // TIER CARDS
        coverageTiers.forEach { tier ->
            val isSelected = selectedTierId == tier.id

            TierCard(
                tier = tier,
                isSelected = isSelected,
                onSelect = { viewModel.selectTier(tier.id) },
                onBind = { onBindPolicy(tier.name) }
            )
        }

        // DISCOUNTS APPLIED MODULE
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocalOffer, "Offer", tint = QQBlue, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Discounts Applied", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    }
                    Text("Total: -\$52/mo saved", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    DiscountPill(icon = Icons.Default.VerifiedUser, text = "Good Driver (-15%)")
                    DiscountPill(icon = Icons.Default.DirectionsCar, text = "Multi-Car (-10%)")
                    DiscountPill(icon = Icons.Default.Security, text = "Anti-Theft (-5%)")
                }
            }
        }

        // FLOATING UNDERWRITER AGENT CHAT BOX
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
            shadowElevation = 3.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(44.dp)) {
                        AsyncImage(
                            model = AppImages.SARAH_JENKINS,
                            contentDescription = "Sarah Jenkins",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .border(1.5.dp, Color.White, CircleShape)
                        )
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF10B981))
                                .border(1.5.dp, Color.White, CircleShape)
                                .align(Alignment.BottomEnd)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text("Need help picking?", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Text("Sarah is available to advise.", fontSize = 10.sp, color = QQTextSecondary)
                    }
                }

                Button(
                    onClick = onChatWithSarah,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F5F9)),
                    modifier = Modifier.height(34.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.QuestionAnswer,
                        contentDescription = "Chat",
                        tint = QQRed,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Chat Now", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                }
            }
        }
    }
}

@Composable
fun TierCard(
    tier: CoveragePlanTier,
    isSelected: Boolean,
    onSelect: () -> Unit,
    onBind: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .testTag("tier_card_${tier.id}"),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) QQBlue else Color(0xFFE2E8F0)
        ),
        shadowElevation = if (isSelected) 4.dp else 1.dp
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            if (tier.isRecommended) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(QQRed)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, "Star", tint = Color.White, modifier = Modifier.size(10.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                "MOST POPULAR & RECOMMENDED",
                                color = Color.White,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }

                    Text("Best Value", color = QQRed, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Header Row: radio, title, price
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) QQBlue else Color.Transparent)
                            .border(2.dp, if (isSelected) QQBlue else Color(0xFFCBD5E1), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color.White))
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(tier.name, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Text(tier.subtitle, fontSize = 10.sp, color = QQTextMuted)
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            "\$${tier.monthlyRate}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = if (isSelected) QQBlue else QQDarkBlue
                        )
                        Text("/mo", fontSize = 10.sp, color = QQTextSecondary)
                    }
                    Text("\$${tier.sixMonthRate} / 6-mo pre-pay", fontSize = 9.sp, color = QQTextMuted)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = Color(0xFFF1F5F9))
            Spacer(modifier = Modifier.height(8.dp))

            // Limits 2-column grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Check, "Check", tint = if (isSelected) QQBlue else QQTextSecondary, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(tier.bodilyInjury, fontSize = 10.sp, color = QQDarkBlue)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Check, "Check", tint = if (isSelected) QQBlue else QQTextSecondary, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(tier.collisionDed, fontSize = 10.sp, color = QQDarkBlue)
                    }
                }

                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Check, "Check", tint = if (isSelected) QQBlue else QQTextSecondary, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(tier.propertyDamage, fontSize = 10.sp, color = QQDarkBlue)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Check, "Check", tint = if (isSelected) QQBlue else QQTextSecondary, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(tier.compDed, fontSize = 10.sp, color = QQDarkBlue)
                    }
                }
            }

            if (tier.perks.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                tier.perks.forEach { perk ->
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 2.dp)) {
                        Icon(Icons.Default.Verified, "Perk", tint = QQBlue, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(perk, fontSize = 10.sp, color = QQDarkBlue, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (isSelected) {
                Button(
                    onClick = onBind,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .testTag("bind_policy_button"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = QQRed)
                ) {
                    Text("Select & Bind Policy", fontWeight = FontWeight.Black, fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, "Bind", modifier = Modifier.size(14.dp))
                }
            } else {
                OutlinedButton(
                    onClick = onSelect,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Select ${tier.name}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                }
            }
        }
    }
}

@Composable
fun DiscountPill(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFFF1F5F9))
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(6.dp))
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, "Discount", tint = QQBlue, modifier = Modifier.size(12.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text, fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = QQDarkBlue)
        }
    }
}
