package dojo.supermarket.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import supermarket.model.*

class SupermarketTest {

    @Test
    fun shouldCreateReceiptForApples() {
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)
        val cart =
                ShoppingCart()
        cart.addItemQuantity(apples, 2.5)
        val teller = Teller(catalog)
        addSpecialOfferToTeller(teller, SpecialOfferType.PercentDiscount, toothbrush, 10.0, SpecialOfferTypeNew.PercentDiscount(10))
        val receipt: Receipt = teller.checksOutArticlesFrom(cart)
        val items: List<ReceiptItem> = receipt.getItems()
        Assertions.assertEquals(1, items.size)
        Assertions.assertEquals(listOf(ReceiptItem(apples, 2.5, 1.99, 4.975)), items)
        Assertions.assertEquals(emptyList<Discount>(), receipt.getDiscounts())
    }

    @Test
    fun shouldGenerateDiscountForToothBrush() {
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 1.0)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)
        val cart =
                ShoppingCart()
        cart.addItemQuantity(toothbrush, 3.0)
        val teller = Teller(catalog)
        addSpecialOfferToTeller(teller, SpecialOfferType.ThreeForTwo, toothbrush, 2 * 1.0, SpecialOfferTypeNew.ThreeForTwo)
        val receipt: Receipt = teller.checksOutArticlesFrom(cart)
        val items: List<ReceiptItem> = receipt.getItems()
        Assertions.assertEquals(1, items.size)
        Assertions.assertEquals(listOf(ReceiptItem(toothbrush, 3.0, 1.0, 3 * 1.0)), items)
        Assertions.assertEquals(listOf(Discount(toothbrush, "3 for 2", 1.0)), receipt.getDiscounts())
    }

    @Test
    fun shouldGenerateDiscountForAllDiscount() {
        val catalog = FakeCatalog()
        val product1 = Product("product1", ProductUnit.Each)
        val product2 = Product("product2", ProductUnit.Each)
        val product3 = Product("product3", ProductUnit.Each)
        val product4 = Product("product4", ProductUnit.Each)
        catalog.addProduct(product1, 1.0)
        catalog.addProduct(product2, 1.0)
        catalog.addProduct(product3, 1.0)
        catalog.addProduct(product4, 1.0)

        val cart = ShoppingCart()
        cart.addItemQuantity(product1, 3.0)
        cart.addItemQuantity(product2, 3.0)
        cart.addItemQuantity(product3, 2.0)
        cart.addItemQuantity(product4, 5.0)

        val teller = Teller(catalog)

        addSpecialOfferToTeller(teller, SpecialOfferType.ThreeForTwo, product1, 1.0, SpecialOfferTypeNew.ThreeForTwo)
        addSpecialOfferToTeller(teller, SpecialOfferType.PercentDiscount, product2, 10.0, SpecialOfferTypeNew.PercentDiscount(10))
        addSpecialOfferToTeller(teller, SpecialOfferType.TwoForAmount, product3, 1.5, SpecialOfferTypeNew.TwoForAmount)
        addSpecialOfferToTeller(teller, SpecialOfferType.FiveForAmount, product4, 3.0, SpecialOfferTypeNew.FiveForAmount)

        val receipt: Receipt = teller.checksOutArticlesFrom(cart)
        val items: List<ReceiptItem> = receipt.getItems()
        Assertions.assertEquals(4, items.size)
        Assertions.assertEquals(2.0 + 2.7 + 1.5 + 3.0, receipt.totalPrice)
    }

    fun addSpecialOfferToTeller(teller: Teller, offerType: SpecialOfferType, product: Product, argument: Double, offerTypeNew: SpecialOfferTypeNew) {
        teller.addSpecialOffer(offerType, product, argument, offerTypeNew)
    }

}
