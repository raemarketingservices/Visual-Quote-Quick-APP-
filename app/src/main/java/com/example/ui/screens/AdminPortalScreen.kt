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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.ui.theme.QQCyan
import com.example.ui.theme.QQDarkBlue
import com.example.ui.theme.QQNavy
import com.example.ui.theme.QQRed
import com.example.ui.theme.QQTextMuted
import com.example.ui.theme.QQTextSecondary
import com.example.ui.viewmodels.QuoteQuickViewModel

@Composable
fun AdminPortalScreen(
    viewModel: QuoteQuickViewModel,
    onOpenLeadWorkspace: (String) -> Unit,
    onReturnToCustomer: () -> Unit
) {
    val context = LocalContext.current
    var selectedFilter by remember { mutableStateOf("all") }
    var searchQuery by remember { mutableStateOf("") }
    val quotes by viewModel.quoteRequests.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .verticalScroll(rememberScrollState())
            .padding(14.dp)
            .testTag("admin_portal_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // TOP CONVEX CLOUD HERO BANNER
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            color = QQNavy,
            shadowElevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 36.dp, height = 4.dp)
                            .clip(CircleShape)
                            .background(QQRed)
                    )

                    // Cloud sync pill
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White.copy(alpha = 0.95f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CloudDone, "Cloud", tint = QQBlue, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Column {
                                Text("Secure Broker Cloud", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                                Text("• All data synchronized", fontSize = 7.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF059669))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Live Lead\nManagement",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    lineHeight = 26.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Real-time incoming customer policies, AI pre-underwriting evaluations, and broker assignment queue.",
                    fontSize = 11.sp,
                    color = Color(0xFFCBD5E1),
                    lineHeight = 15.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Switch back to Policyholder view button
                OutlinedButton(
                    onClick = onReturnToCustomer,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.4f))
                ) {
                    Text("← Return to Policyholder Mode", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // QUICK ACTION BUTTONS: View Settings & MANUAL QUOTE
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { viewModel.showToast("Opening lead workspace settings") },
                shape = RoundedCornerShape(10.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Settings, "Settings", tint = QQBlue, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("View Settings", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                }
            }

            Button(
                onClick = { viewModel.showToast("Creating new manual quote dossier...") },
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = QQRed)
            ) {
                Icon(Icons.Default.Add, "Add", tint = Color.White, modifier = Modifier.size(15.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("MANUAL QUOTE", fontSize = 10.sp, fontWeight = FontWeight.Black)
            }
        }

        // 4 KPI / METRICS GRID
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KpiCard(
                    modifier = Modifier.weight(1f),
                    label = "TOTAL CUSTOMERS",
                    value = "1,428",
                    subText = "+12% this month",
                    subTextColor = Color(0xFF059669),
                    icon = Icons.Default.DirectionsCar
                )
                KpiCard(
                    modifier = Modifier.weight(1f),
                    label = "NEW QUOTE REQUESTS",
                    value = "24",
                    subText = "8 requiring assignment",
                    subTextColor = QQRed,
                    icon = Icons.Default.Description
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KpiCard(
                    modifier = Modifier.weight(1f),
                    label = "IN PREPARATION",
                    value = "15",
                    subText = "Avg turnaround: 34m",
                    subTextColor = QQTextSecondary,
                    icon = Icons.Default.Schedule
                )
                KpiCard(
                    modifier = Modifier.weight(1f),
                    label = "AI CONVERSATIONS",
                    value = "189",
                    subText = "64% converted",
                    subTextColor = QQBlue,
                    icon = Icons.Default.SmartToy
                )
            }
        }

        // FILTER TABS CAROUSEL
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val tabs = listOf(
                "all" to "All Quotes (24)",
                "auto" to "Auto (12)",
                "home" to "Home (6)",
                "life" to "Life (4)",
                "business" to "Business (2)"
            )

            tabs.forEach { (key, label) ->
                val isSelected = selectedFilter == key
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) QQBlue else Color.White)
                        .border(1.dp, if (isSelected) QQBlue else Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                        .clickable { selectedFilter = key }
                        .padding(horizontal = 14.dp, vertical = 7.dp)
                ) {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else QQTextSecondary
                    )
                }
            }
        }

        // SEARCH BAR
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search by name, ID, or vehicle...", fontSize = 11.sp) },
            leadingIcon = { Icon(Icons.Default.Search, "Search", tint = QQTextMuted) },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("admin_lead_search"),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = QQBlue,
                unfocusedBorderColor = Color(0xFFE2E8F0)
            ),
            singleLine = true
        )

        // ACTIVE QUOTING QUEUE SECTION
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Active Quoting Queue", fontSize = 15.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Color(0xFF10B981)))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Real-time listener", fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF059669))
            }
        }

        // LEAD CARD 1: Marcus Vance (Auto)
        LeadQueueCard(
            quoteNumber = "QQ-2026-000412",
            timeAgo = "Submitted 22 mins ago",
            policyType = "Auto Insurance",
            location = "Langhorne, PA ZIP: 19047",
            customerName = "Marcus Vance",
            phone = "(215) 555-0187",
            vehicleAsset = "2024 Toyota RAV4 Hybrid (AWD)",
            coverageDesc = "Complete & Collision (\$500 Ded.)",
            imageUrl = AppImages.WHITE_SUV,
            status = "Quote Being Prepared",
            assignedAgent = "Sarah Jenkins",
            agentSubtitle = "Active • 4 Leads in prep",
            borderColor = QQBlue,
            onOpenLead = { onOpenLeadWorkspace("QQ-2026-000412") },
            onCall = {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:2155550187"))
                context.startActivity(intent)
            },
            onQuickNote = { viewModel.showToast("Note appended to Lead #QQ-2026-000412") }
        )

        // LEAD CARD 2: Elena Rostova (Commercial / High Value)
        LeadQueueCard(
            quoteNumber = "QQ-2026-000411",
            timeAgo = "Submitted 1 hour ago",
            policyType = "Commercial Line",
            location = "Apex Logistics LLC",
            customerName = "Elena Rostova",
            phone = "(267) 555-0234",
            vehicleAsset = "Commercial General Liability (\$2M)",
            coverageDesc = "Fleet: 4 Transport Sprinters",
            imageUrl = AppImages.SPRINTER_COMMERCIAL,
            status = "Under Review",
            assignedAgent = "Assign Agent",
            agentSubtitle = "",
            isUnassigned = true,
            isHighValue = true,
            borderColor = QQRed,
            onOpenLead = { onOpenLeadWorkspace("QQ-2026-000411") },
            onCall = {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:2675550234"))
                context.startActivity(intent)
            },
            onQuickNote = { viewModel.showToast("Note appended to Lead #QQ-2026-000411") }
        )

        // ACTIVE UNDERWRITER DISPATCH SECTION
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Active Underwriter Dispatch", fontSize = 14.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
            Text("Real-time availability of certified state agents for quote generation.", fontSize = 10.sp, color = QQTextMuted)

            Spacer(modifier = Modifier.height(8.dp))

            // Regional Hub pill
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFEEF5FF),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDBEAFE))
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(QQBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.LocationOn, "Hub", tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Langhorne, PA Regional Hub", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Text("668 Woodbourne Rd", fontSize = 9.sp, color = QQTextMuted)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 4 Agents Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AgentStatusCard(
                    modifier = Modifier.weight(1f),
                    name = "Sarah Jenkins",
                    status = "Active • 4 Leads in prep",
                    initials = "SJ",
                    avatarColor = QQNavy,
                    isOnline = true
                )
                AgentStatusCard(
                    modifier = Modifier.weight(1f),
                    name = "David Rivera",
                    status = "Active • 2 Leads in prep",
                    initials = "DR",
                    avatarColor = QQBlue,
                    isOnline = true
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AgentStatusCard(
                    modifier = Modifier.weight(1f),
                    name = "Maya Kapoor",
                    status = "In Customer Call",
                    initials = "MK",
                    avatarColor = Color(0xFF334155),
                    isBusy = true
                )
                AgentStatusCard(
                    modifier = Modifier.weight(1f),
                    name = "Tom Chen",
                    status = "Offline • Starts 1:00 PM",
                    initials = "TC",
                    avatarColor = Color(0xFF64748B),
                    isOffline = true
                )
            }
        }
    }
}

@Composable
fun KpiCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    subText: String,
    subTextColor: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        shadowElevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFEEF5FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, label, tint = QQBlue, modifier = Modifier.size(16.dp))
                }
                Icon(Icons.Default.ChevronRight, "More", tint = QQTextMuted, modifier = Modifier.size(14.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(label, fontSize = 8.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
            Text(subText, fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = subTextColor)
        }
    }
}

@Composable
fun LeadQueueCard(
    quoteNumber: String,
    timeAgo: String,
    policyType: String,
    location: String,
    customerName: String,
    phone: String,
    vehicleAsset: String,
    coverageDesc: String,
    imageUrl: String,
    status: String,
    assignedAgent: String,
    agentSubtitle: String,
    isUnassigned: Boolean = false,
    isHighValue: Boolean = false,
    borderColor: Color,
    onOpenLead: () -> Unit,
    onCall: () -> Unit,
    onQuickNote: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("lead_card_$quoteNumber"),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        shadowElevation = 2.dp
    ) {
        Box {
            // Left Accent border
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(90.dp)
                    .background(borderColor)
            )

            Column(modifier = Modifier.padding(14.dp)) {
                // Top Request ID & Time
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isHighValue) Color(0xFFFEECEC) else Color(0xFFEEF5FF))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "Quote #$quoteNumber",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = if (isHighValue) QQRed else QQBlue
                        )
                    }

                    Text(timeAgo, fontSize = 9.sp, color = QQTextMuted)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Badges
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFEEF5FF))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(policyType, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                    }

                    if (isHighValue) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFFFDAD6))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text("💎 High Value Lead", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQRed)
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFF1F5F9))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(location, fontSize = 9.sp, color = QQTextSecondary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Customer Info Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(if (isHighValue) Color(0xFFFFDAD6) else Color(0xFFDBEAFE)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = customerName.split(" ").mapNotNull { it.firstOrNull()?.toString() }.joinToString(""),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isHighValue) QQRed else QQBlue
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(customerName, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Call, "Phone", tint = QQTextMuted, modifier = Modifier.size(11.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(phone, fontSize = 10.sp, color = QQTextMuted)
                            if (isHighValue) {
                                Text(" • Apex Logistics LLC", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = QQDarkBlue)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Asset details box
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
                            .size(width = 54.dp, height = 38.dp)
                            .clip(RoundedCornerShape(6.dp))
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = vehicleAsset,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(vehicleAsset, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Text(coverageDesc, fontSize = 9.sp, color = QQTextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Status & Assigned Agent
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("STATUS (SYNCED)", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFF1F5F9))
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        ) {
                            Text(status, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text("ASSIGNED AGENT", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
                        Spacer(modifier = Modifier.height(2.dp))
                        if (isUnassigned) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(1.dp, QQBlue, RoundedCornerShape(8.dp))
                                    .background(Color(0xFFEEF5FF))
                                    .padding(horizontal = 8.dp, vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.PersonAdd, "Assign", tint = QQBlue, modifier = Modifier.size(12.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Assign Agent", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFF1F5F9))
                                    .padding(horizontal = 8.dp, vertical = 6.dp)
                            ) {
                                Text(assignedAgent, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Actions: Open Full Lead, Call Customer, Quick Note
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Button(
                        onClick = onOpenLead,
                        modifier = Modifier
                            .weight(1.4f)
                            .height(36.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = if (isHighValue) QQRed else QQBlue)
                    ) {
                        Icon(Icons.Default.FolderOpen, "Open", tint = Color.White, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Open Full Lead", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }

                    OutlinedButton(
                        onClick = onCall,
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Default.Call, "Call", tint = QQDarkBlue, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("Call", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    }

                    OutlinedButton(
                        onClick = onQuickNote,
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Default.EditNote, "Note", tint = QQDarkBlue, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("Note", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    }
                }
            }
        }
    }
}

@Composable
fun AgentStatusCard(
    modifier: Modifier = Modifier,
    name: String,
    status: String,
    initials: String,
    avatarColor: Color,
    isOnline: Boolean = false,
    isBusy: Boolean = false,
    isOffline: Boolean = false
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(32.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(avatarColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(initials, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }

                val dotColor = when {
                    isOnline -> Color(0xFF10B981)
                    isBusy -> Color(0xFFF59E0B)
                    else -> Color(0xFF94A3B8)
                }
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(dotColor)
                        .border(1.dp, Color.White, CircleShape)
                        .align(Alignment.BottomEnd)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(name, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                Text(status, fontSize = 8.sp, color = QQTextMuted, lineHeight = 10.sp)
            }
        }
    }
}
