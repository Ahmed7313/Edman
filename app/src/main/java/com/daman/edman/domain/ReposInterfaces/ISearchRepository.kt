package com.daman.edman.domain.ReposInterfaces

import com.daman.edman.data.remote.DTO.SearchDTO.SearchDTO

interface ISearchRepository {
    suspend fun searchUser(phone: String): SearchDTO
} 