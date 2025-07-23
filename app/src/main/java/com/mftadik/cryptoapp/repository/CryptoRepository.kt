package com.mftadik.cryptoapp.repository

import com.mftadik.cryptoapp.model.Crypto
import com.mftadik.cryptoapp.model.CryptoList
import com.mftadik.cryptoapp.service.CryptoAPI
import com.mftadik.cryptoapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api : CryptoAPI
) {

    suspend fun getCryptoList() : Resource<CryptoList> {
        val response = try {
            api.getCryptoList()
        } catch (e : Exception) {
            return Resource.Error("Error.")
        }
        return Resource.Success(response)
    }

    suspend fun getCrypto() : Resource<Crypto> {
        val response = try {
            api.getCrypto()
        } catch (e: Exception) {
            return Resource.Error("Error.")
        }
        return Resource.Success(response)
    }
}