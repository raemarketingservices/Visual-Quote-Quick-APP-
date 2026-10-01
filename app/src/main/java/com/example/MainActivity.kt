package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.BottomNavBar
import com.example.ui.components.TopNavBar
import com.example.ui.screens.AccountScreen
import com.example.ui.screens.AdminPortalScreen
import com.example.ui.screens.AiAssistantScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.InsuranceCatalogScreen
import com.example.ui.screens.QuoteCustomizerReviewScreen
import com.example.ui.screens.QuoteIntakeScreen
import com.example.ui.screens.QuoteRequestsTrackingScreen
import com.example.ui.screens.QuoteResultsScreen
import com.example.ui.screens.SignInScreen
import com.example.ui.screens.UnderwriterWorkspaceScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.QQBlue
import com.example.ui.theme.QQCyan
import com.example.ui.theme.QQDarkBlue
import com.example.ui.theme.QQNavy
import com.example.ui.theme.QQRed
import com.example.ui.theme.QQTextMuted
import com.example.ui.theme.QQTextSecondary
import com.example.ui.viewmodels.QuoteQuickViewModel
import com.example.ui.viewmodels.Screen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                QuoteQuickApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteQuickApp(
    viewModel: QuoteQuickViewModel = viewModel()
) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var showNotificationsDialog by remember { mutableStateOf(false) }
    var showQuickQuoteModal by remember { mutableStateOf(false) }
    var quickQuoteType by remember { mutableStateOf("auto") }
    var aiChatPrefillPrompt by remember { mutableStateOf<String?>(null) }
    var catalogCategoryFilter by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearToast()
        }
    }

    // Android Hardware Back button handling
    BackHandler(enabled = currentScreen !is Screen.CustomerTabs || (currentScreen as Screen.CustomerTabs).selectedTab != 0) {
        when (currentScreen) {
            is Screen.QuoteCustomizer -> viewModel.navigateTo(Screen.CustomerTabs(2)) // Back to Intake
            is Screen.QuoteResults -> viewModel.navigateTo(Screen.QuoteCustomizer)
            is Screen.QuoteTracking -> viewModel.navigateTo(Screen.CustomerTabs(4)) // Back to Account
            is Screen.UnderwriterWorkspace -> viewModel.navigateTo(Screen.AdminPortal)
            is Screen.AdminPortal -> viewModel.navigateTo(Screen.CustomerTabs(0))
            is Screen.SignIn -> viewModel.navigateTo(Screen.CustomerTabs(0))
            is Screen.CustomerTabs -> {
                val tab = (currentScreen as Screen.CustomerTabs).selectedTab
                if (tab != 0) {
                    viewModel.navigateToTab(0) // Return to Home tab
                }
            }
        }
    }

    val isTopLevelCustomer = currentScreen is Screen.CustomerTabs
    val selectedTab = if (currentScreen is Screen.CustomerTabs) {
        (currentScreen as Screen.CustomerTabs).selectedTab
    } else 0

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            if (currentScreen !is Screen.SignIn) {
                TopNavBar(
                    showBackButton = currentScreen is Screen.QuoteCustomizer ||
                            currentScreen is Screen.QuoteResults ||
                            currentScreen is Screen.QuoteTracking ||
                            currentScreen is Screen.UnderwriterWorkspace,
                    onBackClick = {
                        when (currentScreen) {
                            is Screen.QuoteCustomizer -> viewModel.navigateTo(Screen.CustomerTabs(2))
                            is Screen.QuoteResults -> viewModel.navigateTo(Screen.QuoteCustomizer)
                            is Screen.QuoteTracking -> viewModel.navigateTo(Screen.CustomerTabs(4))
                            is Screen.UnderwriterWorkspace -> viewModel.navigateTo(Screen.AdminPortal)
                            else -> viewModel.navigateTo(Screen.CustomerTabs(0))
                        }
                    },
                    onNotificationClick = { showNotificationsDialog = true },
                    onAvatarClick = {
                        if (currentScreen is Screen.AdminPortal || currentScreen is Screen.UnderwriterWorkspace) {
                            viewModel.navigateTo(Screen.CustomerTabs(4))
                        } else {
                            viewModel.navigateToTab(4)
                        }
                    },
                    onMenuClick = {
                        // Toggle between Customer Mode and Broker Admin Portal
                        if (currentScreen is Screen.AdminPortal || currentScreen is Screen.UnderwriterWorkspace) {
                            viewModel.navigateTo(Screen.CustomerTabs(0))
                        } else {
                            viewModel.navigateTo(Screen.AdminPortal)
                        }
                    },
                    isAdminMode = currentScreen is Screen.AdminPortal || currentScreen is Screen.UnderwriterWorkspace,
                    userInitials = if (currentScreen is Screen.AdminPortal || currentScreen is Screen.UnderwriterWorkspace) "SJ" else "SA"
                )
            }
        },
        bottomBar = {
            if (isTopLevelCustomer) {
                BottomNavBar(
                    selectedTab = selectedTab,
                    onTabSelected = { tabIdx ->
                        if (tabIdx == 3) aiChatPrefillPrompt = null
                        if (tabIdx == 1) catalogCategoryFilter = null
                        viewModel.navigateToTab(tabIdx)
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val screen = currentScreen) {
                is Screen.SignIn -> {
                    SignInScreen(
                        onSignInSuccess = {
                            viewModel.showToast("Welcome back, Marcus! Connecting securely...")
                            viewModel.navigateTo(Screen.CustomerTabs(0))
                        },
                        onBrowseAsGuest = {
                            viewModel.showToast("Guest Mode: Instant quoting and AI assistant unlocked")
                            viewModel.navigateTo(Screen.CustomerTabs(0))
                        },
                        onOpenAdminPortal = {
                            viewModel.showToast("Entering Staff & Broker Admin Portal...")
                            viewModel.navigateTo(Screen.AdminPortal)
                        },
                        onCategoryClick = { cat ->
                            catalogCategoryFilter = cat
                            viewModel.navigateToTab(1)
                        }
                    )
                }

                is Screen.CustomerTabs -> {
                    when (screen.selectedTab) {
                        0 -> HomeScreen(
                            onStartQuote = { type ->
                                viewModel.updateQuoteForm { it.copy(quoteType = type) }
                                viewModel.navigateToTab(2)
                            },
                            onOpenAiChat = { prompt ->
                                aiChatPrefillPrompt = prompt
                                viewModel.navigateToTab(3)
                            },
                            onExploreCatalog = { cat ->
                                catalogCategoryFilter = cat
                                viewModel.navigateToTab(1)
                            }
                        )

                        1 -> InsuranceCatalogScreen(
                            initialCategory = catalogCategoryFilter,
                            onSelectQuote = { type ->
                                viewModel.updateQuoteForm { it.copy(quoteType = type) }
                                viewModel.navigateToTab(2)
                            },
                            onOpenAiChat = {
                                aiChatPrefillPrompt = null
                                viewModel.navigateToTab(3)
                            }
                        )

                        2 -> QuoteIntakeScreen(
                            viewModel = viewModel,
                            onContinueToCoverage = {
                                viewModel.navigateTo(Screen.QuoteCustomizer)
                            }
                        )

                        3 -> AiAssistantScreen(
                            viewModel = viewModel,
                            prefilledPrompt = aiChatPrefillPrompt,
                            onStartQuote = {
                                viewModel.navigateTo(Screen.CustomerTabs(2))
                            }
                        )

                        4 -> AccountScreen(
                            viewModel = viewModel,
                            onOpenQuoteTracking = {
                                viewModel.navigateTo(Screen.QuoteTracking)
                            },
                            onOpenAdminPortal = {
                                viewModel.navigateTo(Screen.AdminPortal)
                            },
                            onContactAgent = {
                                aiChatPrefillPrompt = "Hello Sarah, I have questions about my active auto policy rate."
                                viewModel.navigateToTab(3)
                            }
                        )
                    }
                }

                is Screen.QuoteCustomizer -> {
                    QuoteCustomizerReviewScreen(
                        viewModel = viewModel,
                        onSubmitToUnderwriter = {
                            viewModel.submitQuoteRequest()
                        },
                        onSaveDraft = {
                            viewModel.showToast("Draft quote saved to your account.")
                            viewModel.navigateTo(Screen.CustomerTabs(0))
                        }
                    )
                }

                is Screen.QuoteResults -> {
                    QuoteResultsScreen(
                        viewModel = viewModel,
                        onBindPolicy = { tierName ->
                            viewModel.bindPolicy(tierName)
                        },
                        onChatWithSarah = {
                            aiChatPrefillPrompt = "Hi Sarah, can you help me compare the Preferred Protection plan against the Standard option?"
                            viewModel.navigateToTab(3)
                        }
                    )
                }

                is Screen.QuoteTracking -> {
                    QuoteRequestsTrackingScreen(
                        viewModel = viewModel,
                        onChatWithUnderwriter = {
                            aiChatPrefillPrompt = "Hi Sarah, I would like to check on my RAV4 quote being prepared (QQ-2026-000412)."
                            viewModel.navigateToTab(3)
                        },
                        onViewQuoteResults = {
                            viewModel.navigateTo(Screen.QuoteResults)
                        }
                    )
                }

                is Screen.AdminPortal -> {
                    AdminPortalScreen(
                        viewModel = viewModel,
                        onOpenLeadWorkspace = {
                            viewModel.navigateTo(Screen.UnderwriterWorkspace)
                        },
                        onReturnToCustomer = {
                            viewModel.navigateTo(Screen.CustomerTabs(0))
                        }
                    )
                }

                is Screen.UnderwriterWorkspace -> {
                    UnderwriterWorkspaceScreen(
                        viewModel = viewModel,
                        quoteNumber = "QQ-2026-000412",
                        onBack = { viewModel.navigateTo(Screen.AdminPortal) },
                        onDispatchQuote = {
                            viewModel.dispatchQuoteToCustomer()
                        }
                    )
                }
            }
        }
    }

    // QUICK QUOTE MODAL (From HTML screen logic)
    if (showQuickQuoteModal) {
        Dialog(onDismissRequest = { showQuickQuoteModal = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .testTag("quick_quote_modal"),
                color = Color.White,
                shadowElevation = 10.dp
            ) {
                var zipCode by remember { mutableStateOf("19047") }
                var contact by remember { mutableStateOf("marcus.vance@gmail.com") }

                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(QQRed))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "FAST INSURANCE QUOTE",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = QQDarkBlue
                            )
                        }

                        IconButton(onClick = { showQuickQuoteModal = false }) {
                            Icon(Icons.Default.Close, "Close", tint = QQTextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("COVERAGE TYPE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQTextSecondary)
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF1F5F9))
                            .padding(horizontal = 12.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = when (quickQuoteType) {
                                "home" -> "Homeowners Insurance"
                                "life" -> "Term Life Insurance"
                                "commercial" -> "Commercial Business"
                                else -> "Auto Insurance"
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = QQDarkBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text("ZIP CODE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQTextSecondary)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = zipCode,
                        onValueChange = { zipCode = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text("PHONE OR EMAIL", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QQTextSecondary)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = contact,
                        onValueChange = { contact = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            showQuickQuoteModal = false
                            viewModel.showToast("Quote submitted! Loading rates for $zipCode...")
                            viewModel.navigateTo(Screen.CustomerTabs(2)) // Move to intake / flow
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = QQRed)
                    ) {
                        Text("SUBMIT & VIEW RATES", fontWeight = FontWeight.Black, fontSize = 11.sp, letterSpacing = 0.5.sp)
                    }
                }
            }
        }
    }

    // NOTIFICATIONS DIALOG
    if (showNotificationsDialog) {
        Dialog(onDismissRequest = { showNotificationsDialog = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .testTag("notifications_dialog"),
                color = Color.White,
                shadowElevation = 10.dp
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Notifications, "Notifications", tint = QQBlue, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Notifications & Alerts", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = QQDarkBlue)
                        }

                        IconButton(onClick = { showNotificationsDialog = false }) {
                            Icon(Icons.Default.Close, "Close", tint = QQTextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Notification 1
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFFF0FDF4),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBBF7D0))
                    ) {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Icon(Icons.Default.CheckCircle, "Active", tint = Color(0xFF059669), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text("All Policies In Good Order", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                                Text("Personal Auto #QQ-AUT-9941 is verified and active until April 2026.", fontSize = 10.sp, color = Color(0xFF047857))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Notification 2
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFFEFF6FF),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBFDBFE))
                    ) {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Icon(Icons.Default.Policy, "Quote", tint = QQBlue, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text("Quote #QQ-2026-000412 Being Prepared", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = QQBlue)
                                Text("Underwriter Sarah Jenkins is comparing top 8 carriers for you.", fontSize = 10.sp, color = QQDarkBlue)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { showNotificationsDialog = false },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = QQBlue)
                    ) {
                        Text("Dismiss", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
