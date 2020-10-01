package supermarket.model

enum class SpecialOfferType {
    ThreeForTwo, PercentDiscount, TwoForAmount, FiveForAmount
}

sealed class SpecialOfferTypeNew {
    object ThreeForTwo: SpecialOfferTypeNew()

    data class PercentDiscount(
        val percent: Int
    ) : SpecialOfferTypeNew()

    object TwoForAmount: SpecialOfferTypeNew()

    object FiveForAmount: SpecialOfferTypeNew()
}



/*
sealed class DeliveryStatus {

    object Preparing : DeliveryStatus()

    data class Dispatching(
        val trackingNumber: String
    ) : DeliveryStatus()

    object Delivered : DeliveryStatus()

}


fun showDeliveryStatus(status: DeliveryStatus) {
    return when (status) {
        is Preparing -> showPreparing()
        is Dispatched -> showDispatched(it.trackingNumber) // note that no cast needed!
    }
}*/