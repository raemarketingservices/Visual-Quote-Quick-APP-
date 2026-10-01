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
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.QQBlue
import com.example.ui.theme.QQDarkBlue
import com.example.ui.theme.QQRed
import com.example.ui.theme.QQTextMuted
import com.example.ui.theme.QQTextSecondary
import com.example.ui.viewmodels.QuoteQuickViewModel

@Composable
fun QuoteCustomizerReviewScreen(
    viewModel: QuoteQuickViewModel,
    onSubmitToUnderwriter: () -> Unit,
    onSaveDraft: () -> Unit
) {
    val formState by viewModel.quoteForm.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FB))
            .verticalScroll(rememberScrollState())
            .padding(14.dp)
            .testTag("quote_customizer_review_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // STEPPER HEADER: 3-step progress bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Step 1 Complete
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(QQBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Check, "Done", tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Text("1. Vehicle & Drivers", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    Text("Basic Info (Done)", fontSize = 9.sp, color = QQTextMuted)
                }

                Box(modifier = Modifier.height(2.dp).weight(0.4f).background(QQBlue))

                // Step 2 Complete
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(QQBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Check, "Done", tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Text("2. Coverage", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    Text("Customized", fontSize = 9.sp, color = QQBlue, fontWeight = FontWeight.Bold)
                }

                Box(modifier = Modifier.height(2.dp).weight(0.4f).background(QQRed))

                // Step 3 Active Review
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(QQRed),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("3", color = Color.White, fontWeight = FontWeight.Black, fontSize = 13.sp)
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Text("3. Review", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQRed)
                    Text("Ready to Submit", fontSize = 9.sp, color = QQRed, fontWeight = FontWeight.Medium)
                }
            }
        }

        // SUB-HEADLINE NOTICE BANNER
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFF2F4F6),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFDBE1FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Policy,
                        contentDescription = "Policy",
                        tint = QQBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text("Fine-tune & Final Review", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    Text(
                        "Confirm your limits below before submitting your secure underwriting dossier to 14 top-tier regional carriers.",
                        fontSize = 11.sp,
                        color = QQTextSecondary,
                        lineHeight = 14.sp
                    )
                }
            }
        }

        // SECTION 1: COVERAGE LIMITS ADJUSTER
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Shield, "Shield", tint = QQBlue, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("COVERAGE LIMITS ADJUSTER", fontSize = 11.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFFFDAD6))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("RECOMMENDED", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQRed)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Bodily Injury
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Bodily Injury Liability", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    Text("\$100k / \$300k", fontSize = 11.sp, fontWeight = FontWeight.Black, color = QQBlue)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val injuryOptions = listOf("\$50k / \$100k", "\$100k / \$300k", "\$250k / \$500k")
                    injuryOptions.forEach { opt ->
                        val isSelected = opt == "\$100k / \$300k"
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) Color(0xFFDBE1FF).copy(alpha = 0.5f) else Color(0xFFF8FAFC))
                                .border(
                                    width = if (isSelected) 2.dp else 1.dp,
                                    color = if (isSelected) QQBlue else Color(0xFFE2E8F0),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = opt,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) QQBlue else QQTextSecondary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Property Damage
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Property Damage", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                    Text("\$100,000", fontSize = 11.sp, fontWeight = FontWeight.Black, color = QQBlue)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val propOptions = listOf("\$50,000", "\$100,000", "\$250,000")
                    propOptions.forEach { opt ->
                        val isSelected = opt == "\$100,000"
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) Color(0xFFDBE1FF).copy(alpha = 0.5f) else Color(0xFFF8FAFC))
                                .border(
                                    width = if (isSelected) 2.dp else 1.dp,
                                    color = if (isSelected) QQBlue else Color(0xFFE2E8F0),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = opt,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) QQBlue else QQTextSecondary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Deductibles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Complete", fontSize = 10.sp, color = QQTextMuted)
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
                                .background(Color(0xFFF8FAFC))
                                .padding(horizontal = 10.dp, vertical = 10.dp)
                        ) {
                            Text("\$500 Deductible", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = QQDarkBlue)
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text("Collision", fontSize = 10.sp, color = QQTextMuted)
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
                                .background(Color(0xFFF8FAFC))
                                .padding(horizontal = 10.dp, vertical = 10.dp)
                        ) {
                            Text("\$500 Deductible", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = QQDarkBlue)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Optional Endorsements
                Text("OPTIONAL ENDORSEMENTS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
                Spacer(modifier = Modifier.height(8.dp))

                // Roadside
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CarRepair, "Roadside", tint = QQTextSecondary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Roadside Assistance", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            Text("24/7 towing & battery jump", fontSize = 10.sp, color = QQTextMuted)
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("+\$4/mo", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Spacer(modifier = Modifier.width(6.dp))
                        Switch(
                            checked = formState.hasRoadside,
                            onCheckedChange = { chk -> viewModel.updateQuoteForm { it.copy(hasRoadside = chk) } },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = QQBlue)
                        )
                    }
                }

                // Rental
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CarRental, "Rental", tint = QQTextSecondary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Rental Reimbursement", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            Text("\$50/day during covered repairs", fontSize = 10.sp, color = QQTextMuted)
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("+\$6/mo", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        Spacer(modifier = Modifier.width(6.dp))
                        Switch(
                            checked = formState.hasRental,
                            onCheckedChange = { chk -> viewModel.updateQuoteForm { it.copy(hasRental = chk) } },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = QQBlue)
                        )
                    }
                }

                // Gap
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CurrencyExchange, "Gap", tint = QQTextSecondary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Gap Coverage Protection", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            Text("Covers lease & loan shortfall", fontSize = 10.sp, color = QQTextMuted)
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("+\$9/mo", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = QQTextMuted)
                        Spacer(modifier = Modifier.width(6.dp))
                        Switch(
                            checked = formState.hasGap,
                            onCheckedChange = { chk -> viewModel.updateQuoteForm { it.copy(hasGap = chk) } },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = QQBlue)
                        )
                    }
                }
            }
        }

        // SECTION 2: VERIFIED RECORDS SUMMARY
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CheckCircle, "Verified", tint = QQBlue, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("VERIFIED RECORDS SUMMARY", fontSize = 11.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                    }
                    Icon(Icons.Default.Edit, "Edit", tint = QQTextMuted, modifier = Modifier.size(16.dp))
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Vehicle Summary Box
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF8FAFC))
                        .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.DirectionsCar, "Car", tint = QQTextSecondary, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("2024 Toyota RAV4 Hybrid", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }
                        Text("SUV (AWD) • 10,001 - 15,000 mi/yr", fontSize = 10.sp, color = QQTextMuted, modifier = Modifier.padding(start = 20.dp))
                        Text("VIN: 4T3B...7290 Verified", fontSize = 9.sp, color = QQBlue, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 20.dp, top = 2.dp))
                    }
                    Icon(Icons.Default.CheckCircle, "Verified", tint = QQBlue, modifier = Modifier.size(18.dp))
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Driver Summary Box
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF8FAFC))
                        .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Person, "Person", tint = QQTextSecondary, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Marcus Vance (Primary)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }
                        Text("DOB: 03/15/1990 • Single Male", fontSize = 10.sp, color = QQTextMuted, modifier = Modifier.padding(start = 20.dp))
                        Text("PA Driver's License: D12345678", fontSize = 10.sp, color = QQTextMuted, modifier = Modifier.padding(start = 20.dp))
                    }
                    Icon(Icons.Default.CheckCircle, "Verified", tint = QQBlue, modifier = Modifier.size(18.dp))
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Estimated Monthly Premium Card
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFEEF5FF))
                        .border(1.dp, Color(0xFFDBE1FF), RoundedCornerShape(10.dp))
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("ESTIMATED MONTHLY PREMIUM", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text("\$135 – \$152", fontSize = 20.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                            Text(" / month", fontSize = 11.sp, color = QQTextMuted)
                        }
                        Text("Locked rate valid for 72 hours across 14 carriers", fontSize = 9.sp, color = QQTextMuted)
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Lock, "Lock", tint = QQRed, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }

        // SECTION 3: BINDING AGREEMENT CHECKBOX
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.updateQuoteForm { it.copy(isAgreementChecked = !it.isAgreementChecked) } }
                    .padding(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Checkbox(
                    checked = formState.isAgreementChecked,
                    onCheckedChange = { chk -> viewModel.updateQuoteForm { it.copy(isAgreementChecked = chk) } },
                    colors = CheckboxDefaults.colors(checkedColor = QQBlue)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "I certify all driver and vehicle records are accurate to the best of my knowledge and authorize Quote Quick to run actuarial reports.",
                    fontSize = 11.sp,
                    color = QQTextSecondary,
                    lineHeight = 15.sp
                )
            }
        }

        // SUBMIT AND DRAFT BUTTONS
        Button(
            onClick = onSubmitToUnderwriter,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("submit_quote_to_underwriter_button"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = QQRed)
        ) {
            Text("Submit Quote Request to Underwriter", fontWeight = FontWeight.Black, fontSize = 12.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Submit", modifier = Modifier.size(16.dp))
        }

        OutlinedButton(
            onClick = onSaveDraft,
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Save Draft & Exit", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = QQTextSecondary)
        }
    }
}
