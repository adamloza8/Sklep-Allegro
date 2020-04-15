package pl.sklepallegro.sklepallegro

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("allegro/offers")
    fun listOffers(): Call<OffersList>
}