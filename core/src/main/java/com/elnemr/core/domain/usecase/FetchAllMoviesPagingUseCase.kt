package com.elnemr.core.domain.usecase

import androidx.paging.PagingData
import com.elnemr.core.domain.model.Movie
import com.elnemr.core.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAllMoviesPagingUseCase @Inject constructor() :
    BaseUseCase<Flow<PagingData<Movie>>, HashMap<String, String>>() {

    override suspend fun execute(params: HashMap<String, String>?) {
        params?.let { stateFlow.emit(repo.fetchAllMovies(params)) }
    }

}