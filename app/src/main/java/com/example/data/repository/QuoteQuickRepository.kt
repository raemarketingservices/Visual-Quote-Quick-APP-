package com.example.data.repository

import com.example.data.db.ChatMessageEntity
import com.example.data.db.QuoteDao
import com.example.data.db.ChatDao
import com.example.data.db.QuoteRequestEntity
import com.example.data.models.ActivePolicyItem
import com.example.data.models.CarrierQuote
import com.example.data.models.CoveragePlanTier
import com.example.data.models.InsuranceType
import com.example.data.models.LeadItem
import com.example.data.models.UnderwriterVerificationDoc
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class QuoteQuickRepository(
    private val quoteDao: QuoteDao,
    private val chatDao: ChatDao
) {
    val allQuotes: Flow<List<QuoteRequestEntity>> = quoteDao.getAllQuotes()
    val allChatMessages: Flow<List<ChatMessageEntity>> = chatDao.getAllMessages()

    suspend fun ensureInitialData() {
        val existingQuotes = quoteDao.getAllQuotes()
        // If empty, insert the primary realistic quote from screenshots
        if (existingQuotes.firstOrNull()?.isEmpty() == true) {
            quoteDao.insertQuote(
                QuoteRequestEntity(
                    quoteNumber = "QQ-2026-000412",
                    customerName = "Marcus Vance",
                    phone = "(215) 555-0187",
                    email = "marcus.vance@gmail.com",
                    policyType = "Auto Insurance",
                    vehicleYear = "2024",
                    vehicleMake = "Toyota",
                    vehicleModel = "RAV4 Hybrid (AWD)",
                    bodyStyle = "SUV",
                    vin = "4T3BWRFV0RU109283",
                    driverLicense = "D12345678",
                    licenseState = "PA",
                    liabilityLimit = "\$100k / \$300k",
                    propertyDamageLimit = "\$100,000",
                    compDeductible = "\$500 Deductible",
                    collDeductible = "\$500 Deductible",
                    hasRoadside = true,
                    hasRental = true,
                    hasGap = false,
                    status = "Quote Being Prepared",
                    currentStep = 4,
                    assignedUnderwriter = "Sarah Jenkins",
                    estimatedMonthly = "\$142.00",
                    submittedTime = "Oct 24, 2025 at 10:14 AM"
                )
            )

            // Also insert another lead for Elena Rostova (High value commercial)
            quoteDao.insertQuote(
                QuoteRequestEntity(
                    quoteNumber = "QQ-2026-000411",
                    customerName = "Elena Rostova",
                    phone = "(267) 555-0234",
                    email = "elena@apexlogistics.com",
                    policyType = "Commercial Line",
                    vehicleYear = "2024",
                    vehicleMake = "Mercedes-Benz",
                    vehicleModel = "Sprinter Transport Fleet (4)",
                    bodyStyle = "Commercial Fleet",
                    vin = "1CD4592039481729",
                    driverLicense = "E99823412",
                    licenseState = "PA",
                    liabilityLimit = "\$2,000,000 Aggregate",
                    propertyDamageLimit = "\$500,000",
                    compDeductible = "\$1,000 Deductible",
                    collDeductible = "\$1,000 Deductible",
                    hasRoadside = true,
                    hasRental = false,
                    hasGap = true,
                    status = "Under Review",
                    currentStep = 2,
                    assignedUnderwriter = "Unassigned",
                    estimatedMonthly = "\$495.00",
                    submittedTime = "1 hour ago"
                )
            )
        }

        // Seed initial chat messages if empty
        if (chatDao.getAllMessages().firstOrNull()?.isEmpty() == true) {
            chatDao.insertMessage(
                ChatMessageEntity(
                    isUser = false,
                    text = "Hi! I'm your Quote Guide Assistant. 👋\nI can help you understand your coverage options, compare policies, answer questions, and connect you with a real agent when you're ready.",
                    timestamp = "Today 10:24 AM"
                )
            )
            chatDao.insertMessage(
                ChatMessageEntity(
                    isUser = true,
                    text = "What does full coverage include for auto insurance? I'm looking for protection for a 2024 Toyota RAV4.",
                    timestamp = "Today 10:25 AM"
                )
            )
            chatDao.insertMessage(
                ChatMessageEntity(
                    isUser = false,
                    text = "Great question! Full coverage typically includes:\n\n• Liability: Covers injuries and property damage to others.\n• Complete: Covers non-collision events (theft, vandalism, weather, etc.).\n• Collision: Covers damage to your vehicle after an accident.\n• Uninsured/Underinsured Motorist: Protects you if the other driver doesn't have enough coverage.\n• Medical Payments / PIP: Helps with medical expenses.",
                    timestamp = "Today 10:25 AM",
                    hasVehicleCard = true
                )
            )
        }
    }

    suspend fun saveNewQuote(quote: QuoteRequestEntity): Long {
        return quoteDao.insertQuote(quote)
    }

    suspend fun updateQuoteStatus(quoteNumber: String, newStatus: String) {
        quoteDao.updateStatus(quoteNumber, newStatus)
    }

    suspend fun sendChatMessage(text: String, isUser: Boolean, hasVehicleCard: Boolean = false) {
        val timeString = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.US).format(java.util.Date())
        chatDao.insertMessage(
            ChatMessageEntity(
                isUser = isUser,
                text = text,
                timestamp = "Today $timeString",
                hasVehicleCard = hasVehicleCard
            )
        )
    }

    // Default static plans and policies for demo
    fun getCoverageTiers(): List<CoveragePlanTier> = listOf(
        CoveragePlanTier(
            id = "standard",
            name = "Standard Essential",
            subtitle = "State minimum + basic physical damage",
            monthlyRate = 128,
            sixMonthRate = 710,
            bodilyInjury = "Bodily Injury: \$50k/\$100k",
            propertyDamage = "Property Damage: \$50k",
            collisionDed = "Collision Ded: \$1,000",
            compDed = "Comp Ded: \$1,000",
            perks = listOf("State Compliance Guarantee", "Digital ID Card Access")
        ),
        CoveragePlanTier(
            id = "preferred",
            name = "Preferred Protection",
            subtitle = "Balanced limits & roadside assistance",
            monthlyRate = 142,
            sixMonthRate = 790,
            bodilyInjury = "Bodily Injury: \$100k/\$300k",
            propertyDamage = "Property Damage: \$100k",
            collisionDed = "Collision Ded: \$500",
            compDed = "Comp Ded: \$500",
            perks = listOf(
                "Roadside Assistance + Rental (\$50/day) Included",
                "Towing & Battery Jump Service",
                "Zero-hassle digital claims fast track"
            ),
            isRecommended = true,
            isBestValue = true
        ),
        CoveragePlanTier(
            id = "premium",
            name = "Premium Total Guard",
            subtitle = "Maximum liability, Gap & OEM parts",
            monthlyRate = 176,
            sixMonthRate = 980,
            bodilyInjury = "Bodily Injury: \$250k/\$500k",
            propertyDamage = "Property Damage: \$250k",
            collisionDed = "Collision Ded: \$250",
            compDed = "Comp Ded: \$250",
            perks = listOf(
                "OEM Parts, Gap Coverage & New Car Replacement",
                "Trip Interruption \$1,000 Coverage",
                "Accident Forgiveness Protection"
            )
        )
    )

    fun getCarrierQuotes(): List<CarrierQuote> = listOf(
        CarrierQuote(
            carrierName = "Progressive",
            monthlyRate = 142.00,
            savingsBadge = "BEST VALUE • -18%",
            isBest = true,
            perksSummary = "Applied: Paperless • Telematics • Multi-Product"
        ),
        CarrierQuote(
            carrierName = "Travelers",
            monthlyRate = 154.50,
            perksSummary = "Applied: Hybrid Vehicle Credit • Safe Driver"
        ),
        CarrierQuote(
            carrierName = "Nationwide",
            monthlyRate = 161.00,
            perksSummary = "Standard Complete Tier • \$500 Ded"
        ),
        CarrierQuote(
            carrierName = "Safeco",
            monthlyRate = 168.00,
            perksSummary = "Premier Protection Network • Roadside Active"
        )
    )

    fun getActivePolicies(): List<ActivePolicyItem> = listOf(
        ActivePolicyItem(
            id = "1",
            policyNumber = "QQ-AUT-2025-9941",
            title = "Personal Auto Insurance",
            subtitle = "2024 Toyota RAV4 Hybrid",
            monthlyRate = 142.00,
            annualPremium = 1704.00,
            autoPayEnabled = true,
            nextPaymentDate = "Nov 24, 2025",
            type = InsuranceType.AUTO
        ),
        ActivePolicyItem(
            id = "2",
            policyNumber = "QQ-HOM-4028",
            title = "Homeowners Insurance",
            subtitle = "668 Woodbourne Rd, Langhorne, PA",
            monthlyRate = 68.00,
            annualPremium = 816.00,
            autoPayEnabled = true,
            nextPaymentDate = "Nov 01, 2025",
            type = InsuranceType.HOME
        )
    )

    fun getVerificationDocs(): List<UnderwriterVerificationDoc> = listOf(
        UnderwriterVerificationDoc(
            id = "doc1",
            filename = "PA_License_MVance.jpg",
            description = "Driver's License • State Valid",
            isVerified = true
        ),
        UnderwriterVerificationDoc(
            id = "doc2",
            filename = "PA_Reg_RAV4_2024.pdf",
            description = "PA Dept of Transportation",
            isVerified = true
        ),
        UnderwriterVerificationDoc(
            id = "doc3",
            filename = "StateFarm_DecPage.pdf",
            description = "Prior Carrier Declaration",
            isVerified = false
        )
    )
}
