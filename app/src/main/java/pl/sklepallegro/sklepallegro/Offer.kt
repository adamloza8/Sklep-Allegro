package pl.sklepallegro.sklepallegro

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class OffersList(val offers: List<Offer>)
@Parcelize
data class Price(val amount:String, val currency: String): Parcelable
@Parcelize
data class Offer(
    val name: String, val price: Price?, val thumbnailUrl: String, val description: String
) : Parcelable