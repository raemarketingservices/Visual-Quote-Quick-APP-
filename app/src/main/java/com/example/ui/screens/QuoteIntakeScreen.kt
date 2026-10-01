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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Umbrella
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteIntakeScreen(
    viewModel: QuoteQuickViewModel,
    onContinueToCoverage: () -> Unit
) {
    val formState by viewModel.quoteForm.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F6F9))
            .verticalScroll(rememberScrollState())
            .padding(14.dp)
            .testTag("quote_intake_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // STEPPER HEADER
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
                // Step 1: Active
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(QQBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("1", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Asset & Details", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue, maxLines = 1)
                    Text("Basic Info", fontSize = 9.sp, color = QQTextMuted)
                }

                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .weight(0.5f)
                        .background(Color(0xFFE2E8F0))
                )

                // Step 2: Coverage
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color(0xFFCBD5E1), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("2", color = QQTextSecondary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Coverage", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = QQTextSecondary, maxLines = 1)
                    Text("Customize", fontSize = 9.sp, color = QQTextMuted)
                }

                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .weight(0.5f)
                        .background(Color(0xFFE2E8F0))
                )

                // Step 3: Review
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color(0xFFCBD5E1), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("3", color = QQTextSecondary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Review", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = QQTextSecondary, maxLines = 1)
                    Text("Submit", fontSize = 9.sp, color = QQTextMuted)
                }
            }
        }

        // QUOTE TYPE SELECTOR
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text("SELECT QUOTE TYPE", fontSize = 11.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                Spacer(modifier = Modifier.height(8.dp))
                val quoteTypes = listOf(
                    "auto" to "Auto",
                    "home" to "Home",
                    "renters" to "Renters",
                    "life" to "Life",
                    "commercial" to "Business",
                    "rv_boat" to "RV & Boat"
                )
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    quoteTypes.chunked(3).forEach { rowTypes ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            rowTypes.forEach { (typeKey, typeLabel) ->
                                val isSelected = formState.quoteType == typeKey
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) QQBlue else Color(0xFFF1F5F9))
                                        .clickable {
                                            viewModel.updateQuoteForm { it.copy(quoteType = typeKey) }
                                        }
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = typeLabel,
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else QQTextSecondary,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // SECTION 1: DYNAMIC ASSET / PROPERTY / BUSINESS INFORMATION BASED ON QUOTE TYPE
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
                        Icon(
                            imageVector = when (formState.quoteType) {
                                "home" -> Icons.Default.Shield
                                "renters" -> Icons.Default.Umbrella
                                "life" -> Icons.Default.Policy
                                "commercial" -> Icons.Default.DirectionsCar
                                else -> Icons.Default.DirectionsCar
                            },
                            contentDescription = "Asset",
                            tint = QQBlue,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text(
                                text = when (formState.quoteType) {
                                    "home", "renters" -> "PROPERTY & DWELLING DETAILS"
                                    "life" -> "LIFE COVERAGE SPECIFICATIONS"
                                    "commercial" -> "COMMERCIAL & BUSINESS DETAILS"
                                    else -> "VEHICLE INFORMATION"
                                },
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = QQDarkBlue
                            )
                            Text(
                                text = "Provide complete details for accurate carrier underwriting.",
                                fontSize = 10.sp,
                                color = QQTextMuted
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                when (formState.quoteType) {
                    "home" -> {
                        // Homeowners Comprehensive Fields
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.year,
                                onValueChange = { yr -> viewModel.updateQuoteForm { it.copy(year = yr) } },
                                label = { Text("Year Built", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.make,
                                onValueChange = { mk -> viewModel.updateQuoteForm { it.copy(make = mk) } },
                                label = { Text("Property Type", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.model,
                                onValueChange = { mdl -> viewModel.updateQuoteForm { it.copy(model = mdl) } },
                                label = { Text("Square Footage (sq ft)", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.bodyStyle,
                                onValueChange = { bs -> viewModel.updateQuoteForm { it.copy(bodyStyle = bs) } },
                                label = { Text("Est. Replacement Value", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.roofType,
                                onValueChange = { rt -> viewModel.updateQuoteForm { it.copy(roofType = rt) } },
                                label = { Text("Roof Type & Age", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.securitySystem,
                                onValueChange = { ss -> viewModel.updateQuoteForm { it.copy(securitySystem = ss) } },
                                label = { Text("Security / Fire Alarms", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                    }
                    "renters" -> {
                        // Renters Comprehensive Fields
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.bodyStyle,
                                onValueChange = { bs -> viewModel.updateQuoteForm { it.copy(bodyStyle = bs) } },
                                label = { Text("Personal Property Value", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.securitySystem,
                                onValueChange = { ss -> viewModel.updateQuoteForm { it.copy(securitySystem = ss) } },
                                label = { Text("Building Security Features", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                    }
                    "life" -> {
                        // Life Insurance Comprehensive Fields
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.year,
                                onValueChange = { yr -> viewModel.updateQuoteForm { it.copy(year = yr) } },
                                label = { Text("Coverage Amount (\$)", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.make,
                                onValueChange = { mk -> viewModel.updateQuoteForm { it.copy(make = mk) } },
                                label = { Text("Term (Years) or Whole", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.tobaccoUse,
                                onValueChange = { tu -> viewModel.updateQuoteForm { it.copy(tobaccoUse = tu) } },
                                label = { Text("Tobacco / Nicotine Use", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.bodyStyle,
                                onValueChange = { bs -> viewModel.updateQuoteForm { it.copy(bodyStyle = bs) } },
                                label = { Text("Health Profile / Class", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                    }
                    "commercial" -> {
                        // Commercial / Business Comprehensive Fields
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.make,
                                onValueChange = { mk -> viewModel.updateQuoteForm { it.copy(make = mk) } },
                                label = { Text("Business Legal Name", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.model,
                                onValueChange = { mdl -> viewModel.updateQuoteForm { it.copy(model = mdl) } },
                                label = { Text("Industry / NAICS Trade", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.bodyStyle,
                                onValueChange = { bs -> viewModel.updateQuoteForm { it.copy(bodyStyle = bs) } },
                                label = { Text("Annual Gross Revenue", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.year,
                                onValueChange = { yr -> viewModel.updateQuoteForm { it.copy(year = yr) } },
                                label = { Text("Number of Employees", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = formState.businessEin,
                            onValueChange = { ein -> viewModel.updateQuoteForm { it.copy(businessEin = ein) } },
                            label = { Text("Federal Tax ID (EIN)", fontSize = 10.sp) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true
                        )
                    }
                    "rv_boat" -> {
                        // RV & Boat Comprehensive Fields
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.year,
                                onValueChange = { yr -> viewModel.updateQuoteForm { it.copy(year = yr) } },
                                label = { Text("Year", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.make,
                                onValueChange = { mk -> viewModel.updateQuoteForm { it.copy(make = mk) } },
                                label = { Text("Make / Builder", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.model,
                                onValueChange = { mdl -> viewModel.updateQuoteForm { it.copy(model = mdl) } },
                                label = { Text("Model", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.watercraftLength,
                                onValueChange = { wl -> viewModel.updateQuoteForm { it.copy(watercraftLength = wl) } },
                                label = { Text("Length (Feet)", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.bodyStyle,
                                onValueChange = { bs -> viewModel.updateQuoteForm { it.copy(bodyStyle = bs) } },
                                label = { Text("Storage Location", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                    }
                    else -> {
                        // Auto Comprehensive Fields
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.year,
                                onValueChange = { yr -> viewModel.updateQuoteForm { it.copy(year = yr) } },
                                label = { Text("Year", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.make,
                                onValueChange = { mk -> viewModel.updateQuoteForm { it.copy(make = mk) } },
                                label = { Text("Make", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.model,
                                onValueChange = { mdl -> viewModel.updateQuoteForm { it.copy(model = mdl) } },
                                label = { Text("Model", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formState.bodyStyle,
                                onValueChange = { bs -> viewModel.updateQuoteForm { it.copy(bodyStyle = bs) } },
                                label = { Text("Body Style / Trim", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = formState.annualMileage,
                                onValueChange = { am -> viewModel.updateQuoteForm { it.copy(annualMileage = am) } },
                                label = { Text("Est. Annual Mileage", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = formState.primaryUse,
                                onValueChange = { pu -> viewModel.updateQuoteForm { it.copy(primaryUse = pu) } },
                                label = { Text("Primary Use (Commute/Pleasure)", fontSize = 10.sp) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )

                            Text(
                                text = "Use VIN?",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = QQBlue,
                                modifier = Modifier.clickable {
                                    viewModel.updateQuoteForm { it.copy(useVin = !it.useVin) }
                                }
                            )
                        }

                        if (formState.useVin) {
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = formState.vin,
                                onValueChange = { v -> viewModel.updateQuoteForm { it.copy(vin = v) } },
                                label = { Text("VIN Number", fontSize = 10.sp) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                        }
                    }
                }
            }
        }

        // SECTION 2: DRIVER INFORMATION
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
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Driver",
                            tint = QQBlue,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text("DRIVER INFORMATION", fontSize = 11.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                            Text("Provide the primary driver's details.", fontSize = 10.sp, color = QQTextMuted)
                        }
                    }
                    Text("Help?", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Names & DOB
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = formState.firstName,
                        onValueChange = { fn -> viewModel.updateQuoteForm { it.copy(firstName = fn) } },
                        label = { Text("First Name", fontSize = 10.sp) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = formState.lastName,
                        onValueChange = { ln -> viewModel.updateQuoteForm { it.copy(lastName = ln) } },
                        label = { Text("Last Name", fontSize = 10.sp) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = formState.dob,
                        onValueChange = { d -> viewModel.updateQuoteForm { it.copy(dob = d) } },
                        label = { Text("DOB", fontSize = 10.sp) },
                        trailingIcon = { Icon(Icons.Default.CalendarMonth, "Date", tint = QQTextMuted, modifier = Modifier.size(16.dp)) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Gender & Marital Status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = formState.gender,
                        onValueChange = { g -> viewModel.updateQuoteForm { it.copy(gender = g) } },
                        label = { Text("Gender", fontSize = 10.sp) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = formState.maritalStatus,
                        onValueChange = { ms -> viewModel.updateQuoteForm { it.copy(maritalStatus = ms) } },
                        label = { Text("Marital Status", fontSize = 10.sp) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Driver License & State
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = formState.licenseNumber,
                        onValueChange = { lic -> viewModel.updateQuoteForm { it.copy(licenseNumber = lic) } },
                        label = { Text("Driver's License Number", fontSize = 10.sp) },
                        modifier = Modifier.weight(2f),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = formState.licenseState,
                        onValueChange = { st -> viewModel.updateQuoteForm { it.copy(licenseState = st) } },
                        label = { Text("State", fontSize = 10.sp) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }
            }
        }

        // SECTION 3: CURRENT INSURANCE
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = "Shield",
                        tint = QQBlue,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text("CURRENT INSURANCE", fontSize = 11.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                        Text("Do you currently have auto insurance?", fontSize = 10.sp, color = QQTextMuted)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Option Yes
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (formState.hasInsurance) Color(0xFFF0F7FF) else Color.White)
                            .border(
                                width = if (formState.hasInsurance) 2.dp else 1.dp,
                                color = if (formState.hasInsurance) QQBlue else Color(0xFFE2E8F0),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable { viewModel.updateQuoteForm { it.copy(hasInsurance = true) } }
                            .padding(10.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(14.dp)
                                        .clip(CircleShape)
                                        .background(if (formState.hasInsurance) QQBlue else Color.Transparent)
                                        .border(1.dp, QQBlue, CircleShape)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Yes, I have insurance", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            }
                            Text("Get more accurate quotes", fontSize = 9.sp, color = QQTextMuted, modifier = Modifier.padding(start = 20.dp, top = 2.dp))
                        }
                    }

                    // Option No
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (!formState.hasInsurance) Color(0xFFF0F7FF) else Color.White)
                            .border(
                                width = if (!formState.hasInsurance) 2.dp else 1.dp,
                                color = if (!formState.hasInsurance) QQBlue else Color(0xFFE2E8F0),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable { viewModel.updateQuoteForm { it.copy(hasInsurance = false) } }
                            .padding(10.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(14.dp)
                                        .clip(CircleShape)
                                        .background(if (!formState.hasInsurance) QQBlue else Color.Transparent)
                                        .border(1.dp, Color(0xFFCBD5E1), CircleShape)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("No, I need new", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                            }
                            Text("We'll find the best options", fontSize = 9.sp, color = QQTextMuted, modifier = Modifier.padding(start = 20.dp, top = 2.dp))
                        }
                    }
                }

                if (formState.hasInsurance) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = formState.insuranceCompany,
                            onValueChange = { comp -> viewModel.updateQuoteForm { it.copy(insuranceCompany = comp) } },
                            label = { Text("Current Carrier", fontSize = 10.sp) },
                            modifier = Modifier.weight(1.2f),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = formState.expirationDate,
                            onValueChange = { exp -> viewModel.updateQuoteForm { it.copy(expirationDate = exp) } },
                            label = { Text("Expiration Date", fontSize = 10.sp) },
                            trailingIcon = { Icon(Icons.Default.CalendarMonth, "Date", tint = QQTextMuted, modifier = Modifier.size(16.dp)) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true
                        )
                    }
                }
            }
        }

        // SECTION 4: COVERAGE PREFERENCES
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
                        Icon(
                            imageVector = Icons.Default.Umbrella,
                            contentDescription = "Coverage",
                            tint = QQBlue,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text("COVERAGE PREFERENCES", fontSize = 11.sp, fontWeight = FontWeight.Black, color = QQDarkBlue)
                            Text("Select your desired coverage levels.", fontSize = 10.sp, color = QQTextMuted)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFFEECEC))
                            .border(1.dp, Color(0xFFFECACA), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("Recommended", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQRed)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = formState.liabilityLimit,
                    onValueChange = { lim -> viewModel.updateQuoteForm { it.copy(liabilityLimit = lim) } },
                    label = { Text("Liability Limits", fontSize = 10.sp) },
                    trailingIcon = { Icon(Icons.Default.Info, "Info", tint = QQTextMuted, modifier = Modifier.size(16.dp)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = formState.compDeductible,
                        onValueChange = { cd -> viewModel.updateQuoteForm { it.copy(compDeductible = cd) } },
                        label = { Text("Comp Deductible", fontSize = 10.sp) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = formState.collDeductible,
                        onValueChange = { cd -> viewModel.updateQuoteForm { it.copy(collDeductible = cd) } },
                        label = { Text("Collision Deductible", fontSize = 10.sp) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Endorsement Switches
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Uninsured/Underinsured Motorist", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = QQDarkBlue)
                        Switch(
                            checked = formState.hasUninsuredMotorist,
                            onCheckedChange = { chk -> viewModel.updateQuoteForm { it.copy(hasUninsuredMotorist = chk) } },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = QQBlue)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Medical Payments (PIP)", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = QQDarkBlue)
                        Switch(
                            checked = formState.hasPip,
                            onCheckedChange = { chk -> viewModel.updateQuoteForm { it.copy(hasPip = chk) } },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = QQBlue)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Rental Reimbursement", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = QQDarkBlue)
                        Switch(
                            checked = formState.hasRental,
                            onCheckedChange = { chk -> viewModel.updateQuoteForm { it.copy(hasRental = chk) } },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = QQBlue)
                        )
                    }
                }
            }
        }

        // CONTINUE TO COVERAGE BUTTON
        Button(
            onClick = onContinueToCoverage,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("continue_to_coverage_button"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = QQRed)
        ) {
            Text("CONTINUE TO COVERAGE", fontWeight = FontWeight.Black, fontSize = 13.sp, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Continue", modifier = Modifier.size(16.dp))
        }
    }
}
