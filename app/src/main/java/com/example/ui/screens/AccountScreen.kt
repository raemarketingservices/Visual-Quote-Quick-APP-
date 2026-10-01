package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import com.example.ui.components.QuoteQuickEmblem
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
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.QQBlue
import com.example.ui.theme.QQCyan
import com.example.ui.theme.QQDarkBlue
import com.example.ui.theme.QQNavy
import com.example.ui.theme.QQRed
import com.example.ui.theme.QQTextMuted
import com.example.ui.theme.QQTextSecondary
import com.example.ui.viewmodels.QuoteQuickViewModel

@Composable
fun AccountScreen(
    viewModel: QuoteQuickViewModel,
    onOpenQuoteTracking: () -> Unit,
    onOpenAdminPortal: () -> Unit,
    onContactAgent: () -> Unit
) {
    val context = LocalContext.current
    val isCardFlipped by viewModel.isCardFlipped.collectAsState()
    var biometricEnabled by remember { mutableStateOf(true) }

    // Card flip rotation animation
    val rotation by animateFloatAsState(
        targetValue = if (isCardFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FB))
            .verticalScroll(rememberScrollState())
            .padding(14.dp)
            .testTag("account_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. CUSTOMER PROFILE HEADER CARD
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(52.dp)) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .background(
                                        Brush.linearGradient(
                                            listOf(QQBlue, QQCyan)
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "MV",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .size(14.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF10B981))
                                    .border(2.dp, Color.White, CircleShape)
                                    .align(Alignment.BottomEnd),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Check, "Active", tint = Color.White, modifier = Modifier.size(9.dp))
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Marcus Vance",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = QQDarkBlue
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFFFFBEB))
                                        .border(1.dp, Color(0xFFFDE68A), RoundedCornerShape(12.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Verified,
                                            contentDescription = "VIP",
                                            tint = Color(0xFFD97706),
                                            modifier = Modifier.size(11.dp)
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Text("VIP", color = Color(0xFFB45309), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, "Location", tint = QQTextMuted, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(2.dp))
                                Text("Langhorne, PA • Member since 2023", fontSize = 11.sp, color = QQTextMuted)
                            }
                        }
                    }

                    IconButton(onClick = { viewModel.showToast("Profile settings") }) {
                        Icon(Icons.Default.EditNote, "Edit", tint = QQTextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color(0xFFF1F5F9))
                Spacer(modifier = Modifier.height(10.dp))

                // Stats: Active Coverage (2 Policies) & Policy Status (All In Good Order)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFF8FAFC))
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("ACTIVE COVERAGE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
                            Spacer(modifier = Modifier.height(3.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Shield, "Shield", tint = QQBlue, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("2 Policies", fontSize = 13.sp, fontWeight = FontWeight.Black, color = QQBlue)
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFF8FAFC))
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("POLICY STATUS", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
                            Spacer(modifier = Modifier.height(3.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF10B981))
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text("All In Good Order", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                            }
                        }
                    }
                }
            }
        }

        // 2. DIGITAL INSURANCE ID CARD (PA OFFICIAL AUTO CARD)
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Badge, "Badge", tint = QQRed, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Digital Insurance ID", fontSize = 14.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                }

                Text("PA Official Auto Card", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQBlue)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 3D Flipping Card Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(205.dp)
                    .graphicsLayer {
                        rotationY = rotation
                        cameraDistance = 12f * density
                    }
                    .clickable { viewModel.toggleCardFlip() }
                    .testTag("digital_insurance_id_card")
            ) {
                if (rotation <= 90f) {
                    // FRONT OF ID CARD
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0xFF0F172A),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155)),
                        shadowElevation = 6.dp
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(Color(0x330052CC), Color.Transparent),
                                        radius = 350f
                                    )
                                )
                                .padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                // Header
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            QuoteQuickEmblem(size = 18.dp)
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "QUOTE QUICK INSURANCE",
                                                color = Color.White,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Black,
                                                letterSpacing = 0.5.sp
                                            )
                                        }
                                        Text(
                                            text = "COMMONWEALTH OF PENNSYLVANIA FINANCIAL RESPONSIBILITY",
                                            color = Color(0xFF94A3B8),
                                            fontSize = 7.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 0.5.sp
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(QQRed.copy(alpha = 0.2f))
                                            .border(1.dp, QQRed.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "NAIC #38291",
                                            color = Color(0xFFFFDAD6),
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                                // Details
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column {
                                            Text("POLICY NUMBER", fontSize = 8.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
                                            Text("QQ-AUT-2025-9941", fontSize = 12.sp, fontWeight = FontWeight.Black, color = Color.White)
                                        }

                                        Column(horizontalAlignment = Alignment.End) {
                                            Text("EFFECTIVE DATES", fontSize = 8.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
                                            Text("10/24/25 – 04/24/26", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE2E8F0))
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("NAMED INSURED", fontSize = 8.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
                                    Text("Marcus Vance", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)

                                    Spacer(modifier = Modifier.height(6.dp))

                                    // Vehicle box
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0xFF1E293B).copy(alpha = 0.8f))
                                            .border(1.dp, Color(0xFF334155), RoundedCornerShape(8.dp))
                                            .padding(horizontal = 10.dp, vertical = 6.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column {
                                                Text("2024 Toyota RAV4 Hybrid", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                                Text("VIN: 4T3BWRFV0RU109283", fontSize = 9.sp, fontFamily = FontFamily.Monospace, color = Color(0xFFCBD5E1))
                                            }
                                            Icon(Icons.Default.DirectionsCar, "Car", tint = Color(0xFF94A3B8), modifier = Modifier.size(20.dp))
                                        }
                                    }
                                }

                                // Footer
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.VerifiedUser, "Valid", tint = Color(0xFF34D399), modifier = Modifier.size(12.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Verified Valid in PA", fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF34D399))
                                    }

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Tap to flip", fontSize = 9.sp, color = Color(0xFF94A3B8))
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Icon(Icons.Default.Sync, "Flip", tint = Color(0xFF94A3B8), modifier = Modifier.size(11.dp))
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // BACK OF ID CARD (Flipped)
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer { rotationY = 180f },
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0xFF0F172A),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155)),
                        shadowElevation = 6.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Claims & Roadside Assistance", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                Text("24/7 Services", fontSize = 9.sp, color = Color(0xFF7DD3FC), fontWeight = FontWeight.Bold)
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Emergency Towing:", fontSize = 10.sp, color = Color(0xFF94A3B8))
                                    Text("1-800-555-QQIC", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Claims Reporting:", fontSize = 10.sp, color = Color(0xFF94A3B8))
                                    Text("claims@quotequick.com", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Underwriting Agency:", fontSize = 10.sp, color = Color(0xFF94A3B8))
                                    Text("Quote Quick PA LLC #1094", fontSize = 10.sp, color = Color(0xFFCBD5E1))
                                }
                                Text(
                                    text = "Keep this proof of insurance in vehicle. Electronic verification is accepted pursuant to PA Vehicle Code § 1786.",
                                    fontSize = 8.sp,
                                    color = Color(0xFF64748B),
                                    lineHeight = 11.sp
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("PA Bureau of Motor Vehicles Approved", fontSize = 8.sp, color = Color(0xFF94A3B8))
                                Text("Tap to flip back", fontSize = 9.sp, color = Color(0xFFCBD5E1), fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 3 Card Action Buttons: Apple Wallet, PDF ID Card, Flip Card
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { viewModel.showToast("Pass added to Wallet") },
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    shadowElevation = 1.dp
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.AccountBalanceWallet, "Wallet", tint = QQDarkBlue, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.height(3.dp))
                        Text("Apple Wallet", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { viewModel.showToast("Downloading Official PA Insurance Card PDF...") },
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    shadowElevation = 1.dp
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.Download, "Download", tint = QQRed, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.height(3.dp))
                        Text("PDF ID Card", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { viewModel.toggleCardFlip() },
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    shadowElevation = 1.dp
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.Sync, "Flip", tint = QQBlue, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.height(3.dp))
                        Text("Flip Card", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    }
                }
            }
        }

        // 3. TRACK QUOTES BUTTON BANNER
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOpenQuoteTracking() },
            shape = RoundedCornerShape(14.dp),
            color = Color(0xFFEEF5FF),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDBEAFE))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(QQBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Description, "Quotes", tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("My Quote Requests", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Text("Track 6-step progress, underwriter & quotes", fontSize = 10.sp, color = QQTextSecondary)
                    }
                }

                Icon(Icons.Default.ChevronRight, "Track", tint = QQBlue, modifier = Modifier.size(18.dp))
            }
        }

        // 4. ACTIVE INSURANCE POLICIES
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Policy, "Policy", tint = QQRed, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Active Insurance Policies", fontSize = 14.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                }

                Text("2 Total", fontSize = 11.sp, color = QQTextMuted)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Policy 1: Auto
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFEEF5FF)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.DirectionsCar, "Car", tint = QQBlue, modifier = Modifier.size(20.dp))
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Personal Auto Insurance", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                Text("2024 Toyota RAV4 Hybrid", fontSize = 10.sp, color = QQTextMuted)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFECFDF5))
                                .border(1.dp, Color(0xFFA7F3D0), RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Color(0xFF10B981)))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Active", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = Color(0xFFF1F5F9))
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("AUTO-PAY SCHEDULE", fontSize = 8.sp, color = QQTextMuted, fontWeight = FontWeight.Bold)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CheckCircle, "Enabled", tint = Color(0xFF059669), modifier = Modifier.size(11.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text("Enabled", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            }
                            Text("Nov 24, 2025", fontSize = 9.sp, color = QQTextMuted)
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("NEXT PAYMENT", fontSize = 8.sp, color = QQTextMuted, fontWeight = FontWeight.Bold)
                            Text("\$142.00", fontSize = 14.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                            Text("Monthly Rate", fontSize = 9.sp, color = QQTextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "View Coverage & Claims ›",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = QQBlue,
                            modifier = Modifier.clickable { viewModel.showToast("Viewing policy coverage #QQ-AUT-9941") }
                        )
                        Text("#QQ-AUT-9941", fontSize = 9.sp, color = QQTextMuted)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Policy 2: Homeowners
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(QQRed.copy(alpha = 0.1f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Home, "Home", tint = QQRed, modifier = Modifier.size(20.dp))
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Homeowners Insurance", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                Text("668 Woodbourne Rd, Langhorne, PA", fontSize = 10.sp, color = QQTextMuted)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFECFDF5))
                                .border(1.dp, Color(0xFFA7F3D0), RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Color(0xFF10B981)))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Active", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = Color(0xFFF1F5F9))
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("DWELLING PROTECTION", fontSize = 8.sp, color = QQTextMuted, fontWeight = FontWeight.Bold)
                            Text("\$485,000", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            Text("\$1,000 Deductible", fontSize = 9.sp, color = QQTextMuted)
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("ANNUAL PREMIUM", fontSize = 8.sp, color = QQTextMuted, fontWeight = FontWeight.Bold)
                            Text("\$816/yr", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            Text("\$68/mo (Escrowed)", fontSize = 9.sp, color = Color(0xFF059669), fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Manage Policy & Documents ›",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = QQBlue,
                            modifier = Modifier.clickable { viewModel.showToast("Managing Homeowners #QQ-HOM-4028") }
                        )
                        Text("#QQ-HOM-4028", fontSize = 9.sp, color = QQTextMuted)
                    }
                }
            }
        }

        // 5. QUICK SERVICES & SUPPORT (4 TILES)
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Quick Services & Support", fontSize = 14.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // File a Claim
                ServiceTile(
                    modifier = Modifier.weight(1f),
                    title = "File a Claim",
                    subtitle = "Fast 2-minute incident submission",
                    icon = Icons.Default.Bolt,
                    iconBg = Color(0xFFFFDAD6),
                    iconColor = QQRed,
                    onClick = { viewModel.showToast("Starting Claim Incident Form...") }
                )

                // Roadside 24/7
                ServiceTile(
                    modifier = Modifier.weight(1f),
                    title = "Roadside 24/7",
                    subtitle = "1-800-555-QQIC (Tow / Tire)",
                    icon = Icons.Default.CarRepair,
                    iconBg = Color(0xFFDBE1FF),
                    iconColor = QQBlue,
                    isLive = true,
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:18005557742"))
                        context.startActivity(intent)
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Billing & Pay
                ServiceTile(
                    modifier = Modifier.weight(1f),
                    title = "Billing & Pay",
                    subtitle = "Invoices & payment records",
                    icon = Icons.Default.ReceiptLong,
                    iconBg = Color(0xFFF1F5F9),
                    iconColor = QQDarkBlue,
                    onClick = { viewModel.showToast("Opening Invoices & Auto-Pay settings") }
                )

                // Sarah Jenkins
                ServiceTile(
                    modifier = Modifier.weight(1f),
                    title = "Sarah Jenkins",
                    subtitle = "Your PA Licensed Agent",
                    icon = Icons.Default.Person,
                    iconBg = Color(0xFFFEECEC),
                    iconColor = QQRed,
                    onClick = onContactAgent
                )
            }
        }

        // 6. ACCOUNT & SECURITY LIST
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                // Personal Information
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.showToast("Opening Personal Information") }
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Person, "Personal", tint = QQTextSecondary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Personal Information", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            Text("Address, phone numbers, verified drivers", fontSize = 10.sp, color = QQTextMuted)
                        }
                    }
                    Icon(Icons.Default.ChevronRight, "Go", tint = QQTextMuted, modifier = Modifier.size(18.dp))
                }

                HorizontalDivider(color = Color(0xFFF1F5F9))

                // Payment Methods
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.showToast("Opening Payment Methods") }
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CreditCard, "Card", tint = QQTextSecondary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Payment Methods", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            Text("Visa ending in •••• 4829 (Auto-Pay Active)", fontSize = 10.sp, color = QQTextMuted)
                        }
                    }
                    Icon(Icons.Default.ChevronRight, "Go", tint = QQTextMuted, modifier = Modifier.size(18.dp))
                }

                HorizontalDivider(color = Color(0xFFF1F5F9))

                // Paperless Documents
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.showToast("Paperless documents are active") }
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Description, "Docs", tint = QQTextSecondary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Paperless Documents", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            Text("Electronic delivery & policy dec sheets", fontSize = 10.sp, color = QQTextMuted)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFECFDF5))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("Active", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                    }
                }

                HorizontalDivider(color = Color(0xFFF1F5F9))

                // Biometric Login (FaceID)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Face, "Biometrics", tint = QQTextSecondary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Biometric Login (FaceID)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            Text("Instant authenticated mobile access", fontSize = 10.sp, color = QQTextMuted)
                        }
                    }
                    Switch(
                        checked = biometricEnabled,
                        onCheckedChange = { biometricEnabled = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = QQBlue)
                    )
                }
            }
        }

        // 7. BROKER / ADMIN PORTAL ENTRY BANNER
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOpenAdminPortal() },
            shape = RoundedCornerShape(14.dp),
            color = QQNavy
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0x33F59E0B)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Lock, "Lock", tint = Color(0xFFFBBF24), modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("Staff & Broker Admin Portal", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text("Switch to live underwriting queue", fontSize = 10.sp, color = Color(0xFF94A3B8))
                    }
                }

                Button(
                    onClick = onOpenAdminPortal,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Admin →", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQNavy)
                }
            }
        }

        // 8. AGENT DIRECT REACH FOOTER BANNER
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color(0xFFF1F5F9),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(QQRed.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.HeadsetMic, "Agent", tint = QQRed, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("Quote Quick Insurance Group", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Text("Langhorne Agency • (215) 555-0199", fontSize = 10.sp, color = QQTextMuted)
                    }
                }

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:2155550199"))
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = QQDarkBlue),
                    modifier = Modifier.height(34.dp)
                ) {
                    Text("Call Agency", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ServiceTile(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconBg: Color,
    iconColor: Color,
    isLive: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(iconBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = icon, contentDescription = title, tint = iconColor, modifier = Modifier.size(20.dp))
                }

                if (isLive) {
                    Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Color(0xFF10B981)))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column {
                Text(title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                Text(subtitle, fontSize = 9.sp, color = QQTextMuted, lineHeight = 12.sp, maxLines = 2)
            }
        }
    }
}
