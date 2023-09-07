package com.nna.moodify.data.music

import com.nna.moodify.data.response.GetCategoriesResponse
import com.nna.moodify.data.response.GetPlaylistResponse
import com.nna.moodify.data.response.NewReleaseAlbumsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicService {
    @GET("/v1/browse/new-releases")
    suspend fun getNewReleaseAlbums(
        @Query("country") country: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<NewReleaseAlbumsResponse>

    @GET("/v1/browse/categories")
    suspend fun getCategoriesForCountry(
        @Query("country") country: String,
        @Query("locale") locale: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<GetCategoriesResponse>

    @GET("/v1/browse/categories/{category_id}/playlists")
    suspend fun getCategoryPlaylists(
        @Path("category_id") categoryId: String,
        @Query("country") country: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<GetPlaylistResponse>
}