package com.delivery.query

import com.delivery.model.Delivery
import com.delivery.service.impl.DeliveryServiceImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono

/**
 * Delivery query test
 *
 * @constructor Create empty Delivery query test
 */
@SpringBootTest
class DeliveryQueryTest {

    private lateinit var deliveryQuery: DeliveryQuery

    /**
     * Verify delivery with delivery id test.
     *
     */
    @Test
    fun verifyDeliveryWithDeliveryIDTest() {
        runBlocking {
            val service = Mockito.mock(DeliveryServiceImpl::class.java)
            Mockito.`when`(service.getDeliveries()).thenReturn(Mono.empty())
            deliveryQuery = DeliveryQuery(service)
            val delivery = deliveryQuery.getDeliveries()
            Assertions.assertNotNull(delivery)
            Assertions.assertEquals(0, delivery.size)
        }

    }

    /**
     * Verify delivery test
     *
     */
    @Test
    fun verifyDeliveryTest() {
        runBlocking {
            val service = Mockito.mock(DeliveryServiceImpl::class.java)
            Mockito.`when`(service.getDeliveries()).thenReturn(getDelivery())
            deliveryQuery = DeliveryQuery(service)
            val delivery = deliveryQuery.getDeliveries()
            Assertions.assertNotNull(delivery)
            Assertions.assertEquals(1, delivery.size)
            Assertions.assertEquals(101, delivery.get(0).deliveryId)
            Assertions.assertEquals("Bananas", delivery.get(0).product)
            Assertions.assertEquals("JungleInc", delivery.get(0).supplier)
            Assertions.assertEquals(1000000, delivery.get(0).quantity)
            Assertions.assertEquals("2027-01-08T07:17:48.237Z", delivery.get(0).expectedDate)
            Assertions.assertEquals("TheMoon", delivery.get(0).expectedWarehouse)
            Assertions.assertEquals("RECEIVED", delivery.get(0).status)
        }

    }

    private fun getDelivery(): Mono<List<Delivery>> {
        val delivery = Delivery(101, "Bananas", "JungleInc", 1000000, "2027-01-08T07:17:48.237Z", "TheMoon", "RECEIVED")
        val deliveryList = mutableListOf<Delivery>()
        deliveryList.add(delivery)
        return Mono.just(deliveryList)
    }
}