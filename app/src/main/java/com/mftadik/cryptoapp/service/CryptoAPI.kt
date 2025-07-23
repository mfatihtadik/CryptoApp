package com.mftadik.cryptoapp.service

import com.mftadik.cryptoapp.model.Crypto
import com.mftadik.cryptoapp.model.CryptoList
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/IA32-CryptoComposeData/refs/heads/main/cryptolist.json

    @GET("atilsamancioglu/IA32-CryptoComposeData/refs/heads/main/cryptolist.json")
    suspend fun getCryptoList() : CryptoList


    //https://raw.githubusercontent.com/atilsamancioglu/IA32-CryptoComposeData/refs/heads/main/crypto.json

    @GET("atilsamancioglu/IA32-CryptoComposeData/refs/heads/main/crypto.json")
    suspend fun getCrypto() : Crypto


    /*
    Get Request Example:

    //prices?key=ff6c23123234234124anmbs435
    @GET("prices")
    suspend fun getCryptoListA(
        @Query("key") key : String
    ) : Unit


    //currencies?key=ff6c23123234234124anmbs435&ids=BTC&attributes=id,name,logo_url

    @GET("currencies")
    suspend fun getCrypto(
        @Query("key") key : String,
        @Query("ids") id : String,
        @Query("attributes") attributes : String
    ) : Unit


     */


}