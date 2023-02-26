package com.erdiansyah.resutonest.core.data

sealed class State<R> (
    val data: R? = null ,
    val message: String? = null
){
    class Success<R>(data: R): State<R>(data)
    class Error<R>(error: String, data: R? = null): State<R>(message = error, data =  data)
    class Loading<R>: State<R>()
    class NoState<R>: State<R>()
}
