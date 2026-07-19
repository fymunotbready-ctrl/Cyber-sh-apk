package com.example.data.repositories

import com.example.shared.widgets.Finding
import com.example.shared.widgets.GenerationJob
import com.example.features.generate.GenerationMode
import com.example.shared.widgets.Mode
import com.example.shared.widgets.RailEvent
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun watchToday(): Flow<RailEvent>
    fun watchSession(sessionId: String, mode: Mode): Flow<RailEvent>
    suspend fun sendMessage(sessionId: String, text: String, mode: Mode)
}

interface SecRepository {
    suspend fun scan(target: String): List<Finding>
}

interface GenerateRepository {
    suspend fun create(prompt: String, mode: GenerationMode): GenerationJob
    fun watch(jobId: String): Flow<GenerationJob>
}

data class UsageSummary(val runwayDays: Int, val percentageUsed: Float)

interface UsageRepository {
    suspend fun getUsage(): UsageSummary
}

data class BillingInfo(val nextChargeAmount: Double, val nextChargeDate: String)

interface BillingRepository {
    suspend fun getBillingInfo(): BillingInfo
    suspend fun cancelPlan()
}
