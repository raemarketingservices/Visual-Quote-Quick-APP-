package com.example.ui.screens

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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DirectionsBoat
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Weekend
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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

data class CatalogItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val badges: List<Pair<String, Color>>,
    val bullets: List<String>,
    val imageUrl: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val ctaText: String,
    val isCtaBlue: Boolean = false
)

@Composable
fun InsuranceCatalogScreen(
    initialCategory: String? = null,
    onSelectQuote: (String) -> Unit,
    onOpenAiChat: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(initialCategory ?: "all") }

    val allCatalogItems = listOf(
        CatalogItem(
            id = "auto",
            title = "Auto Insurance",
            subtitle = "Drive with ultimate confidence.",
            category = "personal",
            badges = listOf("Most Popular" to QQRed, "Multi-Policy Discount" to QQBlue),
            bullets = listOf(
                "Complete, Collision & Liability",
                "24/7 Roadside Assistance",
                "Rental Reimbursement",
                "Accident Forgiveness"
            ),
            imageUrl = AppImages.AUTO_SPORT_DARK,
            icon = Icons.Default.DirectionsCar,
            ctaText = "Get an Auto Quote"
        ),
        CatalogItem(
            id = "home",
            title = "Homeowners Insurance",
            subtitle = "Protect what matters most.",
            category = "personal",
            badges = listOf("Essential" to QQBlue),
            bullets = listOf(
                "Covers your home, detached structures",
                "Personal property protection",
                "Liability coverage",
                "Peace of mind for your family"
            ),
            imageUrl = AppImages.HOME_MODERN,
            icon = Icons.Default.Home,
            ctaText = "Get a Home Quote"
        ),
        CatalogItem(
            id = "renters",
            title = "Renters Insurance",
            subtitle = "Affordable protection everywhere.",
            category = "personal",
            badges = listOf("Affordable from $14/mo" to QQRed),
            bullets = listOf(
                "Covers your personal belongings",
                "Protection for electronics, furniture & clothes",
                "Liability coverage",
                "Temporary housing costs"
            ),
            imageUrl = AppImages.RENTERS_COZY_ROOM,
            icon = Icons.Default.Weekend,
            ctaText = "Get a Renters Quote"
        ),
        CatalogItem(
            id = "rv_boat",
            title = "Recreational Vehicle (RV) & Boat",
            subtitle = "Adventure without hesitation.",
            category = "commercial",
            badges = listOf("Adventure" to QQBlue),
            bullets = listOf(
                "Motorhomes, campers, boats & ATVs",
                "Coverage for your adventures",
                "On the road or on the water",
                "Personalized protection options"
            ),
            imageUrl = AppImages.YACHT_RV,
            icon = Icons.Default.DirectionsBoat,
            ctaText = "Get an RV Quote",
            isCtaBlue = true
        ),
        CatalogItem(
            id = "life",
            title = "Life Insurance",
            subtitle = "Protection for today. A brighter tomorrow.",
            category = "life",
            badges = listOf("Family First" to QQRed),
            bullets = listOf(
                "Term, Whole, Universal Life",
                "Mortgage Protection",
                "Final Expense",
                "Coverage for every stage of life"
            ),
            imageUrl = AppImages.LIFE_FAMILY,
            icon = Icons.Default.Favorite,
            ctaText = "Get a Life Quote"
        )
    )

    val filteredItems = allCatalogItems.filter { item ->
        val matchesCategory = selectedCategory == "all" ||
                (selectedCategory == "personal" && item.category == "personal") ||
                (selectedCategory == "commercial" && item.category == "commercial") ||
                (selectedCategory == "life" && item.category == "life") ||
                (selectedCategory == "retirement" && (item.category == "life" || item.category == "retirement"))

        val matchesQuery = searchQuery.isBlank() ||
                item.title.contains(searchQuery, ignoreCase = true) ||
                item.subtitle.contains(searchQuery, ignoreCase = true) ||
                item.bullets.any { it.contains(searchQuery, ignoreCase = true) }

        matchesCategory && matchesQuery
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp)
            .testTag("insurance_catalog_screen")
    ) {
        // TOP HERO BANNER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 8.dp)
                .height(175.dp)
                .clip(RoundedCornerShape(18.dp))
        ) {
            AsyncImage(
                model = AppImages.WHITE_SUV,
                contentDescription = "Insurance Catalog Hero",
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
                                Color(0xFF0B213B).copy(alpha = 0.95f),
                                Color(0xFF0D2745).copy(alpha = 0.85f),
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "INSURANCE CATALOG",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFCBD5E1),
                        letterSpacing = 0.8.sp
                    )

                    // Live Rates Pill
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White.copy(alpha = 0.2f))
                            .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF10B981))
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Live Rates ↗",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Column {
                    Text(
                        text = "Coverage for Every\nStage of Life.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        lineHeight = 24.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Explore our insurance options and get a fast, personalized quote with real agents.",
                        fontSize = 11.sp,
                        color = Color(0xFFE2E8F0),
                        lineHeight = 14.sp
                    )
                }
            }
        }

        // SEARCH INPUT
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search insurance (Auto, Home, Renters, Life, Business...)", fontSize = 11.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = QQTextMuted
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear search")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp)
                .testTag("catalog_search_bar"),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = QQBlue,
                unfocusedBorderColor = Color(0xFFE2E8F0)
            ),
            singleLine = true
        )

        // CATEGORY CHIPS (Vertical Stacked Rows)
        val categories = listOf(
            "all" to "All",
            "personal" to "Personal",
            "commercial" to "Commercial",
            "life" to "Life",
            "retirement" to "Retirement"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.chunked(3).forEach { rowCats ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowCats.forEach { (catKey, catLabel) ->
                        val isSelected = selectedCategory == catKey
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (isSelected) QQBlue else Color.White)
                                .border(1.dp, if (isSelected) QQBlue else Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                                .clickable { selectedCategory = catKey }
                                .padding(vertical = 8.dp)
                                .testTag("catalog_chip_$catKey"),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = catLabel,
                                color = if (isSelected) Color.White else QQDarkBlue,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                                fontSize = 11.sp,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // PRODUCT CARDS (2 per row)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            filteredItems.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { item ->
                        CatalogItemCard(
                            modifier = Modifier.weight(1f),
                            item = item,
                            onClick = { onSelectQuote(item.id) }
                        )
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // AI Help Callout Banner
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFFD6E5FB), RoundedCornerShape(16.dp)),
            color = Color(0xFFE8F1FD)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(QQBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SmartToy,
                            contentDescription = "AI",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "Need help choosing the right coverage?",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = QQDarkBlue
                        )
                        Text(
                            text = "Ask Quote Quick AI or chat with a real agent.",
                            fontSize = 10.sp,
                            color = QQTextSecondary
                        )
                    }
                }

                Button(
                    onClick = onOpenAiChat,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = QQBlue),
                    modifier = Modifier.height(34.dp)
                ) {
                    Text("Chat Now →", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun CatalogItemCard(
    modifier: Modifier = Modifier,
    item: CatalogItem,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .testTag("catalog_item_${item.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Top icon badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(6.dp)
                        .size(26.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = QQBlue,
                        modifier = Modifier.size(14.dp)
                    )
                }

                // Badge top right
                if (item.badges.isNotEmpty()) {
                    val (badgeText, badgeColor) = item.badges.first()
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(6.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(badgeColor)
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    ) {
                        Text(text = badgeText, color = Color.White, fontSize = 7.sp, fontWeight = FontWeight.Black)
                    }
                }
            }

            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Black,
                    fontSize = 11.sp,
                    color = QQDarkBlue,
                    maxLines = 1
                )
                Text(
                    text = item.subtitle,
                    fontSize = 9.sp,
                    color = QQTextSecondary,
                    maxLines = 2,
                    lineHeight = 12.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = if (item.isCtaBlue) QQBlue else QQRed),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 4.dp)
                ) {
                    Text(item.ctaText, fontSize = 9.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                }
            }
        }
    }
}
