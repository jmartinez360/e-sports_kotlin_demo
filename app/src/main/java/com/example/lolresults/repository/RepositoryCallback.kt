package com.example.lolresults.repository

interface RepositoryCallback<R, E> {

  fun onSuccess(result: R)

  fun onError(error: E) {}
}