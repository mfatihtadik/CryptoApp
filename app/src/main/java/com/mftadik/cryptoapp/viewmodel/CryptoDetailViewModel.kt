package com.mftadik.cryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.mftadik.cryptoapp.model.Crypto
import com.mftadik.cryptoapp.repository.CryptoRepository
import com.mftadik.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {
    suspend fun getCrypto() : Resource<Crypto> {
        return repository.getCrypto()
    }
}