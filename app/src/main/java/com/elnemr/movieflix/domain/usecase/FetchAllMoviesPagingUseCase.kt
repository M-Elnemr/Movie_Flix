package com.elnemr.movieflix.domain.usecase

import androidx.paging.PagingData
import com.elnemr.movieflix.domain.model.Results
import com.elnemr.movieflix.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAllMoviesPagingUseCase @Inject constructor() :
    BaseUseCase<Flow<PagingData<Results>>, HashMap<String, String>>() {

    override suspend fun execute(params: HashMap<String, String>?) {
        params?.let { stateFlow.emit(repo.fetchAllMovies(params)) }
    }

}