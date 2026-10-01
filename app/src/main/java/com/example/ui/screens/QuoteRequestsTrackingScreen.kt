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
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
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
import com.example.ui.theme.QQRed
import com.example.ui.theme.QQTextMuted
import com.example.ui.theme.QQTextSecondary
import com.example.ui.viewmodels.QuoteQuickViewModel

@Composable
fun QuoteRequestsTrackingScreen(
    viewModel: QuoteQuickViewModel,
    onChatWithUnderwriter: () -> Unit,
    onViewQuoteResults: () -> Unit
) {
    val context = LocalContext.current
    var selectedFilter by remember { mutableStateOf("all") }
    val quotes by viewModel.quoteRequests.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .verticalScroll(rememberScrollState())
            .padding(14.dp)
            .testTag("quote_tracking_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // SCREEN TITLE & LIVE PILL
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "My Quote Requests",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = QQDarkBlue
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFF10B981)))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Real-time underwriter synchronization active",
                        fontSize = 10.sp,
                        color = QQTextMuted
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFEEF5FF))
                    .border(1.dp, Color(0xFFDBEAFE), RoundedCornerShape(20.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text("⚡ Live", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQBlue)
            }
        }

        // FILTER TABS (All (3), In Progress (2), Completed (1))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val tabs = listOf("all" to "All (3)", "progress" to "In Progress (2)", "completed" to "Completed (1)")
            tabs.forEach { (key, label) ->
                val isSelected = selectedFilter == key
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) QQBlue else Color(0xFFE2E8F0).copy(alpha = 0.6f))
                        .clickable { selectedFilter = key }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                        color = if (isSelected) Color.White else QQTextSecondary
                    )
                }
            }
        }

        // ACTIVE QUOTE CARD
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
            shadowElevation = 2.dp
        ) {
            Box {
                // Blue Accent line left
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(110.dp)
                        .background(QQBlue)
                )

                Column(modifier = Modifier.padding(14.dp)) {
                    // Top: Request ID & Status Badge
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Text("REQUEST ID", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("QQ-2026-000412", fontSize = 14.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.Default.ContentCopy, "Copy", tint = QQTextMuted, modifier = Modifier.size(12.dp))
                            }
                            Text("Submitted Oct 24, 2025 at 10:14 AM", fontSize = 9.sp, color = QQTextMuted)
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFFEEF5FF))
                                .border(1.dp, Color(0xFFDBEAFE), RoundedCornerShape(20.dp))
                                .clickable { onViewQuoteResults() }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(QQBlue))
                                Spacer(modifier = Modifier.width(5.dp))
                                Column {
                                    Text("Quote Being Prepared ›", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                                    Text("Active", fontSize = 8.sp, color = QQTextMuted)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = Color(0xFFF1F5F9))
                    Spacer(modifier = Modifier.height(8.dp))

                    // Vehicle Details Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onViewQuoteResults() },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(width = 60.dp, height = 44.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFF8FAFC))
                                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
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
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Personal Auto Insurance", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(Color(0xFFF1F5F9))
                                            .padding(horizontal = 4.dp, vertical = 1.dp)
                                    ) {
                                        Text("AUTO", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = QQTextSecondary)
                                    }
                                }
                                Text("2024 Toyota RAV4 Hybrid (AWD)", fontSize = 11.sp, color = QQTextSecondary)
                                Text("Langhorne, PA 19047", fontSize = 9.sp, color = QQTextMuted)
                            }
                        }

                        Icon(Icons.Default.ChevronRight, "View", tint = QQTextMuted, modifier = Modifier.size(18.dp))
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = Color(0xFFF1F5F9))
                    Spacer(modifier = Modifier.height(8.dp))

                    // Assigned Agent Section (Sarah Jenkins)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(40.dp)) {
                                AsyncImage(
                                    model = AppImages.SARAH_JENKINS,
                                    contentDescription = "Sarah Jenkins",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                        .border(1.dp, Color.White, CircleShape)
                                )
                                Box(
                                    modifier = Modifier
                                        .size(9.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF10B981))
                                        .border(1.dp, Color.White, CircleShape)
                                        .align(Alignment.BottomEnd)
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Column {
                                Text("Sarah Jenkins", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                Text("Senior Underwriter & Rate Specialist", fontSize = 9.sp, color = QQTextMuted)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(Color(0xFF10B981)))
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text("Active", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = Color(0xFF059669))
                                }
                            }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            IconButton(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:2155550187"))
                                    context.startActivity(intent)
                                },
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFEEF5FF))
                            ) {
                                Icon(Icons.Default.Call, "Call", tint = QQBlue, modifier = Modifier.size(15.dp))
                            }

                            IconButton(
                                onClick = onChatWithUnderwriter,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFEEF5FF))
                            ) {
                                Icon(Icons.Default.QuestionAnswer, "Chat", tint = QQBlue, modifier = Modifier.size(15.dp))
                            }

                            IconButton(
                                onClick = { viewModel.showToast("Emailing underwriter Sarah Jenkins...") },
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFEEF5FF))
                            ) {
                                Icon(Icons.Default.Email, "Mail", tint = QQBlue, modifier = Modifier.size(15.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // APPLICATION PROGRESS (Step 4 of 6)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Application Progress", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Text("Step 4 of 6", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Stepper timeline
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // 1. Submitted
                        TimelineStep(
                            number = 1,
                            title = "Submitted",
                            date = "Oct 24, 10:14 AM",
                            subtitle = "Validated intake questionnaire and driver verification",
                            isCompleted = true
                        )

                        // 2. Under Review
                        TimelineStep(
                            number = 2,
                            title = "Under Review",
                            date = "Oct 24, 10:25 AM",
                            subtitle = "Loss history & driving record automatically verified",
                            isCompleted = true
                        )

                        // 3. Agent Assigned
                        TimelineStep(
                            number = 3,
                            title = "Agent Assigned: Sarah Jenkins",
                            date = "Oct 24, 10:30 AM",
                            subtitle = "Assigned dedicated underwriter for carrier negotiation",
                            isCompleted = true
                        )

                        // 4. Quote Being Prepared (Active)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFEEF5FF))
                                .border(1.dp, Color(0xFFDBEAFE), RoundedCornerShape(10.dp))
                                .padding(10.dp)
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .clip(CircleShape)
                                                .background(QQBlue),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color.White))
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Quote Being Prepared", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(QQBlue.copy(alpha = 0.15f))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text("Active", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                                    }
                                }

                                Text(
                                    text = "Comparing top 8 carriers for optimal multi-policy discounts and lowest premium rates.",
                                    fontSize = 10.sp,
                                    color = QQTextSecondary,
                                    modifier = Modifier.padding(start = 28.dp, top = 4.dp),
                                    lineHeight = 13.sp
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(start = 28.dp, top = 6.dp)
                                ) {
                                    Icon(Icons.Default.Schedule, "Time", tint = QQBlue, modifier = Modifier.size(12.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Estimated completion: ~15 mins", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = QQBlue)
                                }
                            }
                        }

                        // 5. Quote Ready
                        TimelineStep(
                            number = 5,
                            title = "Quote Ready",
                            date = "",
                            subtitle = "Tiered price options ready for binder selection",
                            isCompleted = false
                        )

                        // 6. Customer Contacted
                        TimelineStep(
                            number = 6,
                            title = "Customer Contacted / Completed",
                            date = "",
                            subtitle = "Final policy binding and digital issuance",
                            isCompleted = false
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Buttons: Chat with Sarah & View Answers / Upload
                    Button(
                        onClick = onChatWithUnderwriter,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = QQRed)
                    ) {
                        Icon(Icons.Default.QuestionAnswer, "Chat", tint = Color.White, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Chat with Sarah regarding Quote", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = onViewQuoteResults,
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.Description, "Answers", tint = QQDarkBlue, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("View Answers", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }

                        OutlinedButton(
                            onClick = { viewModel.showToast("Opening document uploader...") },
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.FileUpload, "Upload", tint = QQDarkBlue, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Upload ID / Reg", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }
                    }
                }
            }
        }

        // 8 CARRIERS QUERIED CARD
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color(0xFFF0F7FF),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDBEAFE))
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
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .border(1.dp, Color(0xFFDBEAFE), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Analytics, "Stats", tint = QQBlue, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("8 Carriers Queried", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Text("Travelers, Progressive, Nationwide, Safeco & more.", fontSize = 10.sp, color = QQTextMuted)
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .border(1.dp, Color(0xFFDBEAFE), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.TrendingDown, "Savings", tint = QQBlue, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("Saving ~22%", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                    }
                }
            }
        }

        // CLOSED & PRIOR POLICIES SECTION
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Closed & Prior Policies", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                Text("1 Archived", fontSize = 10.sp, color = QQTextMuted)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                shadowElevation = 1.dp
            ) {
                Box {
                    // Green left indicator
                    Box(
                        modifier = Modifier
                            .width(4.dp)
                            .height(60.dp)
                            .background(Color(0xFF10B981))
                    )

                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("REQUEST ID", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
                                Text("QQ-2025-009831", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFECFDF5))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Check, "Done", tint = Color(0xFF059669), modifier = Modifier.size(11.dp))
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text("Completed ›", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(QQBlue),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Home, "Home", tint = Color.White, modifier = Modifier.size(18.dp))
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Homeowners Insurance", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                Text("668 Woodbourne Rd, Langhorne, PA", fontSize = 10.sp, color = QQTextMuted)
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = Color(0xFFF1F5F9))
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Bound on: Sep 14, 2025", fontSize = 10.sp, color = QQTextMuted)
                            Text(
                                text = "Policy Binder PDF ⤓",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = QQBlue,
                                modifier = Modifier.clickable { viewModel.showToast("Downloading Homeowners Policy Binder PDF...") }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimelineStep(
    number: Int,
    title: String,
    date: String,
    subtitle: String,
    isCompleted: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(if (isCompleted) QQBlue else Color.White)
                .border(1.dp, if (isCompleted) QQBlue else Color(0xFFCBD5E1), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Icon(Icons.Default.Check, "Done", tint = Color.White, modifier = Modifier.size(12.dp))
            } else {
                Text(number.toString(), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isCompleted) QQDarkBlue else QQTextSecondary
                )
                if (date.isNotBlank()) {
                    Text(text = date, fontSize = 9.sp, color = QQTextMuted)
                }
            }
            Text(text = subtitle, fontSize = 10.sp, color = QQTextMuted, lineHeight = 13.sp)
        }
    }
}
