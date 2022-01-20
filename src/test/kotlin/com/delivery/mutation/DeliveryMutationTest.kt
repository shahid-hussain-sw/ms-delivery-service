package com.delivery.mutation

import com.delivery.model.Delivery
import com.delivery.service.impl.DeliveryServiceImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono

/**
 * Delivery mutation tests
 *
 * @constructor Create empty Delivery mutation tests
 */
@SpringBootTest
class DeliveryMutationTest {

    private lateinit var deliveryMutation: DeliveryMutation

    /**
     * Verify delivery with delivery id test
     *
     */
    @Test
    fun verifyDeliveryWithDeliveryIDTest() {
        runBlocking {
            val service = mock(DeliveryServiceImpl::class.java)
            `when`(service.updateDeliveryStatus(Mockito.anyLong())).thenReturn(getEmptyDelivery())
            deliveryMutation = DeliveryMutation(service)
            val delivery = deliveryMutation.updateDeliveryStatus(102)
            Assertions.assertNotNull(delivery)
            Assertions.assertEquals(102, delivery.deliveryId)
            Assertions.assertEquals("", delivery.product)
            Assertions.assertEquals("", delivery.supplier)
            Assertions.assertEquals(0, delivery.quantity)
            Assertions.assertEquals("", delivery.expectedDate)
            Assertions.assertEquals("", delivery.expectedWarehouse)
            Assertions.assertEquals("", delivery.status)
        }

    }

    /**
     * Verify delivery test
     *
     */
    @Test
    fun verifyDeliveryTest() {
        runBlocking {
            val service = mock(DeliveryServiceImpl::class.java)
            `when`(service.updateDeliveryStatus(Mockito.anyLong())).thenReturn(getDelivery())
            deliveryMutation = DeliveryMutation(service)
            val delivery = deliveryMutation.updateDeliveryStatus(102)
            Assertions.assertNotNull(delivery)
            Assertions.assertEquals(101, delivery.deliveryId)
            Assertions.assertEquals("Bananas", delivery.product)
            Assertions.assertEquals("JungleInc", delivery.supplier)
            Assertions.assertEquals(1000000, delivery.quantity)
            Assertions.assertEquals("2027-01-08T07:17:48.237Z", delivery.expectedDate)
            Assertions.assertEquals("TheMoon", delivery.expectedWarehouse)
            Assertions.assertEquals("RECEIVED", delivery.status)
        }

    }

    private fun getDelivery(): Mono<Delivery> {
        val delivery = Delivery(101, "Bananas", "JungleInc", 1000000, "2027-01-08T07:17:48.237Z", "TheMoon", "RECEIVED")
        return Mono.just(delivery)
    }

    private fun getEmptyDelivery(): Mono<Delivery> {
        val delivery = Delivery(102, "", "", 0, "", "", "")
        return Mono.just(delivery)
    }
}