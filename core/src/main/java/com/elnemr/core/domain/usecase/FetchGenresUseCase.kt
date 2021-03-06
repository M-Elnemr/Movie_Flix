package com.elnemr.core.domain.usecase

import com.elnemr.core.domain.model.GenresResponse
import com.elnemr.core.domain.result.NetworkResult
import com.elnemr.core.domain.usecase.base.BaseUseCase
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchGenresUseCase @Inject constructor() :
    BaseUseCase<NetworkResult<GenresResponse>, Boolean>() {

    override suspend fun execute(params: Boolean?) {
        stateFlow.emit(NetworkResult.Loading())
        try {
            stateFlow.emit(NetworkResult.Success(repo.fetchGenres()))
        } catch (e: HttpException) {
            stateFlow.emit(NetworkResult.ServerError(e.message))
        } catch (e: IOException) {
            stateFlow.emit(NetworkResult.NetworkError(e.message))
        }
    }

}