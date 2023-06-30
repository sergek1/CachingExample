package com.example.simplecachinhexample.util

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResourse(
    crossinline query: () -> Flow<ResultType>,//from db
    crossinline fetch: suspend () -> RequestType,  //fetch new data from api
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
)= flow {
    val data = query().first()
    val flow=if (shouldFetch(data)){
        emit(Resourse.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resourse.Success(it) }
        }catch (throwable:Throwable){
            query().map { Resourse.Error(throwable,it) }
        }
    }else{
        query().map { Resourse.Success(it) }
    }
    emitAll(flow)
}