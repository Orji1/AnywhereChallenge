package com.example.main_code.network

import com.example.main_code.network.model.RelatedTopic
import com.example.main_code.util.AppState
import com.example.main_code.util.ApplicationType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Repository {
    fun getCharacters(type: ApplicationType): Flow<AppState<List<RelatedTopic>>>
}

class RepositoryImpl(
    private val serviceApi: ServiceApi
) : Repository {

    override fun getCharacters(type: ApplicationType): Flow<AppState<List<RelatedTopic>>> = flow {
        try {
            val response = serviceApi.getCharacters(type.endPoint)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(AppState.Success(it.relatedTopics))
                } ?: throw Exception("response is null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(AppState.Error(e))
        }
    }

}