package com.example.data.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "quote_requests")
data class QuoteRequestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val quoteNumber: String,
    val customerName: String,
    val phone: String,
    val email: String,
    val policyType: String,
    val vehicleYear: String = "2024",
    val vehicleMake: String = "Toyota",
    val vehicleModel: String = "RAV4 Hybrid (AWD)",
    val bodyStyle: String = "SUV",
    val vin: String = "4T3BWRFV0RU109283",
    val driverLicense: String = "D12345678",
    val licenseState: String = "PA",
    val liabilityLimit: String = "\$100k / \$300k",
    val propertyDamageLimit: String = "\$100,000",
    val compDeductible: String = "\$500 Deductible",
    val collDeductible: String = "\$500 Deductible",
    val hasRoadside: Boolean = true,
    val hasRental: Boolean = true,
    val hasGap: Boolean = false,
    val status: String = "Quote Being Prepared",
    val currentStep: Int = 4,
    val assignedUnderwriter: String = "Sarah Jenkins",
    val estimatedMonthly: String = "\$142.00",
    val submittedTime: String = "Today, 10:14 AM"
)

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val isUser: Boolean,
    val text: String,
    val timestamp: String,
    val hasVehicleCard: Boolean = false
)

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quote_requests ORDER BY id DESC")
    fun getAllQuotes(): Flow<List<QuoteRequestEntity>>

    @Query("SELECT * FROM quote_requests WHERE id = :id LIMIT 1")
    suspend fun getQuoteById(id: Long): QuoteRequestEntity?

    @Query("SELECT * FROM quote_requests WHERE quoteNumber = :quoteNumber LIMIT 1")
    suspend fun getQuoteByNumber(quoteNumber: String): QuoteRequestEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteRequestEntity): Long

    @Update
    suspend fun updateQuote(quote: QuoteRequestEntity)

    @Query("UPDATE quote_requests SET status = :newStatus WHERE quoteNumber = :quoteNumber")
    suspend fun updateStatus(quoteNumber: String, newStatus: String)
}

@Dao
interface ChatDao {
    @Query("SELECT * FROM chat_messages ORDER BY id ASC")
    fun getAllMessages(): Flow<List<ChatMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessageEntity): Long

    @Query("DELETE FROM chat_messages")
    suspend fun clearChat()
}

@Database(entities = [QuoteRequestEntity::class, ChatMessageEntity::class], version = 1, exportSchema = false)
abstract class QuoteQuickDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
    abstract fun chatDao(): ChatDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteQuickDatabase? = null

        fun getDatabase(context: Context): QuoteQuickDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuoteQuickDatabase::class.java,
                    "quote_quick_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
