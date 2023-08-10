package com.example.farmus_application.network

import com.example.farmus_application.model.farm.detail.DetailRes
import com.example.farmus_application.model.farm.list.ListRes
import com.example.farmus_application.model.farm.myfarm.MyFarmRes
import com.example.farmus_application.model.farm.phone.PhoneNumberRes
import com.example.farmus_application.model.farm.postings.PostingsRes
import com.example.farmus_application.model.farm.register.RegisterRes
import com.example.farmus_application.model.farm.search.SearchedRes
import com.example.farmus_application.model.favorite.FavoriteFarmRes
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface FarmApiClient {
    @GET("/farm/list/{email}")
    suspend fun getFarmList(@Path(value = "email") email: String): Response<ListRes>

    @Multipart
    @POST("/farm/postings")
    suspend fun postFarmPostings(
        @Part("name") name: RequestBody,
        @Part("owner") owner: RequestBody,
        @Part("startDate") startDate: RequestBody,
        @Part("endDate") endDate: RequestBody,
        @Part("price") price: RequestBody,
        @Part("squaredMeters") squaredMeters: RequestBody,
        @Part("locationBig") locationBig: RequestBody,
        @Part("locationMid") locationMid: RequestBody,
        @Part("locationSmall") locationSmall: RequestBody,
        @Part("description") description: RequestBody,
        @Part("category") category: RequestBody,
        @Part("tag") tag: RequestBody,
        @Part file: List<MultipartBody.Part> // 사진 파일
    ): Response<PostingsRes>

    @PATCH("/farm/register")
    suspend fun patchFarmRegister(): Response<RegisterRes>


    @GET("/farm/search")
    suspend fun getFarmSearchKeyword(@Query("keyword") keyword: String): Response<SearchedRes>

    @GET("/farm")
    suspend fun getFarmSearchByFilter(
        @Query("locationBig") locationBig: String,
        @Query("locationMid") locationMid: String
    ): Response<SearchedRes>

    @GET("/farm/detail/{farmid}")
    suspend fun getFarmDetail(@Path(value = "farmid") farmid: Int): Response<DetailRes>

    @GET("/farm/farmerPhoneNumber")
    suspend fun getFarmerPhoneNumber(@Query("farmID") farmID: Int): Response<PhoneNumberRes>

    @GET("/farm/likes")
    suspend fun getFavoriteFarmList(@Query("email") email: String): Response<FavoriteFarmRes>

    @GET("/farm/myfarm")
    suspend fun getMyFarm():Response<MyFarmRes>

}