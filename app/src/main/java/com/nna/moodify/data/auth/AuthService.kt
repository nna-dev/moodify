package com.nna.moodify.data.auth

import com.nna.moodify.data.response.GetTokenResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("/api/token")
    fun getNewToken(
        @Header("Authorization") basicToken: String,
        @Field("grant_type") grantType: String = "client_credentials"
    ): Call<GetTokenResponse>
}
