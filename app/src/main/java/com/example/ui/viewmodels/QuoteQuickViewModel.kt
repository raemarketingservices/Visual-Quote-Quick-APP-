package com.example.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.ChatMessageEntity
import com.example.data.db.QuoteQuickDatabase
import com.example.data.db.QuoteRequestEntity
import com.example.data.models.ActivePolicyItem
import com.example.data.models.CarrierQuote
import com.example.data.models.CoveragePlanTier
import com.example.data.models.UnderwriterVerificationDoc
import com.example.data.repository.QuoteQuickRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen {
    object SignIn : Screen()
    data class CustomerTabs(val selectedTab: Int = 0) : Screen() // 0: Home, 1: Insurance, 2: Get Quote, 3: AI Assistant, 4: Account
    object QuoteCustomizer : Screen() // Step 2/3 limits adjuster & review
    object QuoteResults : Screen() // Step 3 results & tier selection
    object QuoteTracking : Screen() // My Quote Requests tracking
    object AdminPortal : Screen() // Staff & Broker Lead Management
    object UnderwriterWorkspace : Screen() // Underwriter Lead Workspace
}

data class QuoteFormState(
    val quoteType: String = "auto", // auto, home, renters, life, commercial, rv_boat
    val year: String = "2024",
    val make: String = "Toyota",
    val model: String = "RAV4",
    val bodyStyle: String = "SUV",
    val useVin: Boolean = false,
    val vin: String = "4T3BWRFV0RU109283",
    val roofType: String = "Architectural Shingle",
    val securitySystem: String = "Monitored Alarm & Smoke Detectors",
    val tobaccoUse: String = "No",
    val businessEin: String = "12-3456789",
    val watercraftLength: String = "24 ft",
    val streetAddress: String = "123 Liberty St",
    val city: String = "Langhorne",
    val zipCode: String = "19047",
    val firstName: String = "Marcus",
    val lastName: String = "Vance",
    val dob: String = "03/15/1990",
    val gender: String = "Male",
    val maritalStatus: String = "Single",
    val licenseNumber: String = "D12345678",
    val licenseState: String = "PA",
    val hasInsurance: Boolean = true,
    val insuranceCompany: String = "State Farm",
    val expirationDate: String = "12/15/2025",
    val liabilityLimit: String = "\$100,000 / \$300,000 / \$100,000",
    val propertyDamageLimit: String = "\$100,000",
    val compDeductible: String = "\$500 Deductible",
    val collDeductible: String = "\$500 Deductible",
    val hasUninsuredMotorist: Boolean = true,
    val hasPip: Boolean = true,
    val hasRoadside: Boolean = true,
    val hasRental: Boolean = true,
    val hasGap: Boolean = false,
    val primaryUse: String = "Personal",
    val ownOrLease: String = "Own",
    val annualMileage: String = "10,001 – 15,000",
    val isAgreementChecked: Boolean = true
)

class QuoteQuickViewModel(application: Application) : AndroidViewModel(application) {

    private val db = QuoteQuickDatabase.getDatabase(application)
    private val repository = QuoteQuickRepository(db.quoteDao(), db.chatDao())

    val quoteRequests: StateFlow<List<QuoteRequestEntity>> = repository.allQuotes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val chatMessages: StateFlow<List<ChatMessageEntity>> = repository.allChatMessages
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _currentScreen = MutableStateFlow<Screen>(Screen.CustomerTabs(0))
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _quoteForm = MutableStateFlow(QuoteFormState())
    val quoteForm: StateFlow<QuoteFormState> = _quoteForm.asStateFlow()

    private val _selectedTierId = MutableStateFlow("preferred")
    val selectedTierId: StateFlow<String> = _selectedTierId.asStateFlow()

    private val _selectedCarrier = MutableStateFlow("Progressive")
    val selectedCarrier: StateFlow<String> = _selectedCarrier.asStateFlow()

    private val _underwriterNotes = MutableStateFlow(
        "Customer has clean MVR. Applied 15% preferred safety discount for RAV4 Toyota Safety Sense 3.0. Prepared tiered binder ready for mobile dispatch."
    )
    val underwriterNotes: StateFlow<String> = _underwriterNotes.asStateFlow()

    private val _verificationDocs = MutableStateFlow(repository.getVerificationDocs())
    val verificationDocs: StateFlow<List<UnderwriterVerificationDoc>> = _verificationDocs.asStateFlow()

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    private val _isCardFlipped = MutableStateFlow(false)
    val isCardFlipped: StateFlow<Boolean> = _isCardFlipped.asStateFlow()

    val coverageTiers = repository.getCoverageTiers()
    val carrierQuotes = repository.getCarrierQuotes()
    val activePolicies: List<ActivePolicyItem> = repository.getActivePolicies()

    init {
        viewModelScope.launch {
            repository.ensureInitialData()
        }
    }

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun navigateToTab(tabIndex: Int) {
        _currentScreen.value = Screen.CustomerTabs(tabIndex)
    }

    fun updateQuoteForm(transform: (QuoteFormState) -> QuoteFormState) {
        _quoteForm.value = transform(_quoteForm.value)
    }

    fun selectTier(tierId: String) {
        _selectedTierId.value = tierId
    }

    fun selectCarrier(carrier: String) {
        _selectedCarrier.value = carrier
    }

    fun updateUnderwriterNotes(notes: String) {
        _underwriterNotes.value = notes
    }

    fun toggleDocVerified(docId: String) {
        _verificationDocs.value = _verificationDocs.value.map {
            if (it.id == docId) it.copy(isVerified = !it.isVerified) else it
        }
    }

    fun toggleCardFlip() {
        _isCardFlipped.value = !_isCardFlipped.value
    }

    fun showToast(msg: String) {
        _toastMessage.value = msg
    }

    fun clearToast() {
        _toastMessage.value = null
    }

    fun submitQuoteRequest() {
        viewModelScope.launch {
            val randomNum = (1000..9999).random()
            val newQuote = QuoteRequestEntity(
                quoteNumber = "QQ-2026-00$randomNum",
                customerName = "${_quoteForm.value.firstName} ${_quoteForm.value.lastName}",
                phone = "(215) 555-0187",
                email = "marcus.vance@gmail.com",
                policyType = "Personal Auto Insurance",
                vehicleYear = _quoteForm.value.year,
                vehicleMake = _quoteForm.value.make,
                vehicleModel = "${_quoteForm.value.model} (${_quoteForm.value.bodyStyle})",
                bodyStyle = _quoteForm.value.bodyStyle,
                vin = _quoteForm.value.vin,
                driverLicense = _quoteForm.value.licenseNumber,
                licenseState = _quoteForm.value.licenseState,
                liabilityLimit = _quoteForm.value.liabilityLimit,
                propertyDamageLimit = _quoteForm.value.propertyDamageLimit,
                compDeductible = _quoteForm.value.compDeductible,
                collDeductible = _quoteForm.value.collDeductible,
                hasRoadside = _quoteForm.value.hasRoadside,
                hasRental = _quoteForm.value.hasRental,
                hasGap = _quoteForm.value.hasGap,
                status = "Quote Ready",
                currentStep = 5,
                assignedUnderwriter = "Sarah Jenkins",
                estimatedMonthly = "\$142.00",
                submittedTime = "Just now"
            )
            repository.saveNewQuote(newQuote)
            showToast("Quote dossier submitted to 14 top carriers!")
            _currentScreen.value = Screen.QuoteResults
        }
    }

    fun bindPolicy(tierName: String) {
        showToast("Policy '$tierName' bound successfully! Documents sent to your email.")
        _currentScreen.value = Screen.CustomerTabs(4) // Go to Account screen
    }

    fun dispatchQuoteToCustomer() {
        viewModelScope.launch {
            repository.updateQuoteStatus("QQ-2026-000412", "Quote Ready")
            showToast("Rate matrix dispatched to customer mobile!")
            _currentScreen.value = Screen.AdminPortal
        }
    }

    fun sendChatMessage(userText: String) {
        if (userText.isBlank()) return
        viewModelScope.launch {
            repository.sendChatMessage(userText, isUser = true)

            // Realistic insurance advisor response
            val lower = userText.lowercase()
            val reply = when {
                lower.contains("bundle") || lower.contains("car") || lower.contains("home") -> {
                    "Great choice! Bundling auto and home with Quote Quick typically unlocks an instant 15% to 22% rate reduction. For your 2024 Toyota RAV4, multi-policy discounts bring monthly rates down to as low as \$142/mo with Progressive."
                }
                lower.contains("commercial") || lower.contains("business") || lower.contains("llc") -> {
                    "Quote Quick specializes in Commercial General Liability, transport fleets, and commercial umbrella policies. We can write limits up to \$2,000,000 aggregate with expedited 2-hour underwriting."
                }
                lower.contains("deductible") || lower.contains("collision") -> {
                    "Choosing a \$500 deductible offers the ideal balance between low out-of-pocket expenses and affordable monthly premiums. We also offer diminishing deductibles for every claim-free renewal!"
                }
                else -> {
                    "Thank you for contacting Quote Quick! Our licensed underwriter Sarah Jenkins has verified your request. We search 14 top-rated regional and national carriers to lock in your lowest rate."
                }
            }
            kotlinx.coroutines.delay(600)
            repository.sendChatMessage(reply, isUser = false, hasVehicleCard = lower.contains("car") || lower.contains("rav4"))
        }
    }
}
