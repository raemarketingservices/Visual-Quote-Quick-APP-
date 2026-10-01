package com.example.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.FolderSpecial
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.SendToMobile
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.models.AppImages
import com.example.ui.theme.QQBlue
import com.example.ui.theme.QQDarkBlue
import com.example.ui.theme.QQNavy
import com.example.ui.theme.QQRed
import com.example.ui.theme.QQTextMuted
import com.example.ui.theme.QQTextSecondary
import com.example.ui.viewmodels.QuoteQuickViewModel

@Composable
fun UnderwriterWorkspaceScreen(
    viewModel: QuoteQuickViewModel,
    quoteNumber: String = "QQ-2026-000412",
    onBack: () -> Unit,
    onDispatchQuote: () -> Unit
) {
    val context = LocalContext.current
    val selectedCarrier by viewModel.selectedCarrier.collectAsState()
    val notes by viewModel.underwriterNotes.collectAsState()
    val verificationDocs by viewModel.verificationDocs.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FB))
            .testTag("underwriter_workspace_screen")
    ) {
        // TOP SUBHEADER WORKSPACE BANNER
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF0F172A),
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    IconButton(onClick = onBack, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.Tune, "Workspace", tint = Color(0xFF93C5FD), modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text("Underwriter Workspace", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White, maxLines = 1)
                        Text("Bind eligibility & rate matrix", fontSize = 8.5.sp, color = Color(0xFFCBD5E1), maxLines = 1)
                    }
                }

                Spacer(modifier = Modifier.width(6.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF1E293B))
                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Color(0xFF34D399)))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Synced", fontSize = 9.sp, color = Color(0xFFCBD5E1))
                    }
                }
            }
        }

        // SCROLLABLE CONTENT BODY
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // STATUS RIBBON CARD
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Quote #$quoteNumber", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFFECFDF5))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("Auto Active", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                            }
                        }
                        Text("Submitted 24 mins ago • Langhorne, PA 19047", fontSize = 9.sp, color = QQTextMuted)
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFFFFBEB))
                            .border(1.dp, Color(0xFFFDE68A), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.End) {
                            Text("STATUS", fontSize = 7.sp, fontWeight = FontWeight.Bold, color = Color(0xFFB45309))
                            Text("Quote In Prep", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                        }
                    }
                }
            }

            // CUSTOMER PROFILE & RISK ASSESSMENT
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
                                    .size(42.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFFDBEAFE)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("MV", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Marcus Vance", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(Icons.Default.CheckCircle, "Verified", tint = Color(0xFF059669), modifier = Modifier.size(14.dp))
                                }
                                Text("Individual Policyholder • Age 37", fontSize = 10.sp, color = QQTextMuted)
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFFECFDF5))
                                    .border(1.dp, Color(0xFFA7F3D0), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("Tier 1 (840/900)", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                            }
                            Text("Low Risk Score", fontSize = 8.sp, color = QQTextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFF8FAFC))
                                .padding(8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Call, "Phone", tint = QQTextMuted, modifier = Modifier.size(13.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("(215) 555-0187", fontSize = 10.sp, color = QQDarkBlue, fontWeight = FontWeight.SemiBold)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFF8FAFC))
                                .padding(8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Mail, "Email", tint = QQTextMuted, modifier = Modifier.size(13.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("marcus.vance@gmail.com", fontSize = 10.sp, color = QQDarkBlue, fontWeight = FontWeight.SemiBold, maxLines = 1)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF8FAFC))
                            .padding(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Home, "Address", tint = QQTextMuted, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text("142 Elmwood Ave, Langhorne, PA 19047", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = QQDarkBlue)
                                Text("Bucks County • Suburban Garage Parked", fontSize = 9.sp, color = QQTextMuted)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // LexisNexis MVR Clearance
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFEFF6FF))
                            .border(1.dp, Color(0xFFBFDBFE), RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.FactCheck, "MVR", tint = QQBlue, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text("LexisNexis MVR Clearance", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                    Text("0 at-fault accidents • 0 moving violations (60 mos)", fontSize = 9.sp, color = QQTextSecondary)
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color.White)
                                    .border(1.dp, Color(0xFFBFDBFE), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("PASS", fontSize = 10.sp, fontWeight = FontWeight.Black, color = QQBlue)
                            }
                        }
                    }
                }
            }

            // VEHICLE & POLICY SCOPE
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.DirectionsCar, "Scope", tint = QQBlue, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Vehicle & Policy Scope", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }
                        Text("VIN: 4T3B...8109", fontSize = 9.sp, color = QQTextMuted)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF8FAFC))
                            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = 60.dp, height = 44.dp)
                                .clip(RoundedCornerShape(6.dp))
                        ) {
                            AsyncImage(
                                model = AppImages.WHITE_SUV,
                                contentDescription = "RAV4",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text("2024 Toyota RAV4 Hybrid", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            Text("AWD Limited • 12,500 mi/yr • Personal Commute", fontSize = 10.sp, color = QQTextSecondary)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            Column {
                                Text("PRIOR INSURER", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
                                Text("State Farm", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                Text("Exp: 12/15/2025", fontSize = 9.sp, color = QQTextMuted)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFF0FDF4))
                                .border(1.dp, Color(0xFFBBF7D0), RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            Column {
                                Text("SAVINGS TARGET", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = Color(0xFF15803D))
                                Text("> 15% Reduction", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF15803D))
                                Text("Target ≤ \$145/mo", fontSize = 9.sp, color = Color(0xFF166534))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF1F5F9))
                            .padding(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Selected Structure:", fontSize = 10.sp, color = QQTextSecondary)
                            Text("Complete & Collision", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Collision Deductible:", fontSize = 10.sp, color = QQTextSecondary)
                            Text("\$500.00", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                        }
                    }
                }
            }

            // UNDERWRITING VERIFICATION CHECKLIST
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    val verifiedCount = verificationDocs.count { it.isVerified }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.FolderSpecial, "Docs", tint = QQBlue, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Underwriting Verification", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }
                        Text("$verifiedCount of 3 Verified", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF059669))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    verificationDocs.forEach { doc ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (doc.isVerified) Color(0xFFF8FAFC) else Color(0xFFFFFDF7))
                                .border(1.dp, if (doc.isVerified) Color(0xFFE2E8F0) else Color(0xFFFDE68A), RoundedCornerShape(8.dp))
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                Icon(
                                    imageVector = if (doc.filename.contains("License")) Icons.Default.Badge else Icons.Default.Description,
                                    contentDescription = "File",
                                    tint = if (doc.isVerified) QQTextSecondary else Color(0xFFD97706),
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(doc.filename, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                    Text(doc.description, fontSize = 9.sp, color = QQTextMuted)
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(if (doc.isVerified) Color(0xFFECFDF5) else Color(0xFFFEF3C7))
                                        .clickable { viewModel.toggleDocVerified(doc.id) }
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = if (doc.isVerified) "✓ Verified" else "↻ Under Review",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (doc.isVerified) Color(0xFF065F46) else Color(0xFF92400E)
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = "Preview",
                                    tint = QQTextMuted,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clickable { viewModel.showToast("Previewing ${doc.filename}") }
                                )
                            }
                        }
                    }
                }
            }

            // CARRIER RATE MATRIX
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CurrencyExchange, "Rates", tint = QQBlue, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Carrier Rate Matrix", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }
                        Text("Auto-Quoted", fontSize = 9.sp, color = QQTextMuted)
                    }

                    Text("Select the winning underwritten package for real-time customer dispatch:", fontSize = 10.sp, color = QQTextSecondary)

                    Spacer(modifier = Modifier.height(10.dp))

                    val rates = listOf(
                        Triple("Progressive", 142.00, "BEST VALUE • -18%"),
                        Triple("Travelers", 154.50, null),
                        Triple("Nationwide", 161.00, null)
                    )

                    rates.forEach { (carrier, rate, badge) ->
                        val isSelected = selectedCarrier == carrier
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { viewModel.selectCarrier(carrier) },
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) Color(0xFFF8FAFF) else Color.White,
                            border = androidx.compose.foundation.BorderStroke(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) QQBlue else Color(0xFFE2E8F0)
                            )
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        RadioButton(
                                            selected = isSelected,
                                            onClick = { viewModel.selectCarrier(carrier) },
                                            colors = RadioButtonDefaults.colors(selectedColor = QQBlue)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Column {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(carrier, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                                if (badge != null) {
                                                    Spacer(modifier = Modifier.width(6.dp))
                                                    Box(
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(4.dp))
                                                            .background(Color(0xFFECFDF5))
                                                            .padding(horizontal = 4.dp, vertical = 1.dp)
                                                    ) {
                                                        Text(badge, fontSize = 7.sp, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                                                    }
                                                }
                                            }
                                            Text("Applied: Paperless • Telematics • Multi-Product", fontSize = 9.sp, color = QQTextMuted)
                                        }
                                    }

                                    Column(horizontalAlignment = Alignment.End) {
                                        Text("\$${rate.toInt()}", fontSize = 14.sp, fontWeight = FontWeight.Black, color = if (isSelected) QQBlue else QQDarkBlue)
                                        Text("/month", fontSize = 8.sp, color = QQTextMuted)
                                    }
                                }

                                if (isSelected) {
                                    Spacer(modifier = Modifier.height(6.dp))
                                    HorizontalDivider(color = Color(0xFFE0E7FF))
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.CheckCircle, "Match", tint = QQBlue, modifier = Modifier.size(11.dp))
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text("Matches Customer Savings Goal", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                                        }
                                        Text("6-Month Bound Term", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // INTERNAL UNDERWRITER NOTES & AUDIT LOG
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.EditNote, "Notes", tint = QQTextSecondary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Underwriter Binder Notes", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }
                        Text("Saved 2m ago", fontSize = 9.sp, color = QQTextMuted)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = notes,
                        onValueChange = { viewModel.updateUnderwriterNotes(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("underwriter_notes_input"),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF8FAFC),
                            unfocusedContainerColor = Color(0xFFF8FAFC),
                            focusedBorderColor = QQBlue,
                            unfocusedBorderColor = Color(0xFFE2E8F0)
                        ),
                        maxLines = 4
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Lock, "Encrypted", tint = Color(0xFF059669), modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Encrypted Internal Audit Log", fontSize = 9.sp, color = QQTextMuted)
                        }

                        Text(
                            text = "Append Log Entry",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = QQBlue,
                            modifier = Modifier.clickable { viewModel.showToast("Audit log entry timestamped and recorded") }
                        )
                    }
                }
            }
        }

        // STICKY BOTTOM ACTION DOCK
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("ACTIVE DISPATCH TARGET", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
                        Text("$selectedCarrier • \$142.00/mo", fontSize = 13.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFECFDF5))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("Ready to Bind", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                    }
                }

                Button(
                    onClick = onDispatchQuote,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("dispatch_quote_to_mobile_button"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = QQRed)
                ) {
                    Icon(Icons.Default.SendToMobile, "Send", tint = Color.White, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Dispatch Final Quote to Customer Mobile", fontWeight = FontWeight.Black, fontSize = 11.sp)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:2155550187"))
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Default.Call, "Call", tint = QQBlue, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Call Marcus", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    }

                    Button(
                        onClick = { viewModel.showToast("Reassigning broker queue...") },
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F172A))
                    ) {
                        Icon(Icons.Default.SwapHoriz, "Reassign", tint = Color.White, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Reassign Broker", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
