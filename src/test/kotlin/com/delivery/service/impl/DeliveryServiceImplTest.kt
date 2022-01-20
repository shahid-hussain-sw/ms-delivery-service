package com.delivery.service.impl

import com.delivery.model.Delivery
import com.delivery.repository.DeliveryRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * Delivery service impl tests
 *
 * @constructor Create empty Delivery service impl tests
 */
@SpringBootTest
class DeliveryServiceImplTest {

    private lateinit var deliveryServiceImpl: DeliveryServiceImpl

    /**
     * Updates delivery status when not delivery found against id test.
     *
     */
    @Test
    fun updateDeliveryStatusWhenNotDeliveryFoundAgainstIDTest() {
        val repository = mock(DeliveryRepository::class.java)
        Mockito.`when`(repository.findById(Mockito.anyLong())).thenReturn(Mono.empty())
        runBlocking {
            deliveryServiceImpl = DeliveryServiceImpl(repository)
            val service = deliveryServiceImpl.updateDeliveryStatus(102)
            val delivery = service.block()
            Assertions.assertNotNull(delivery!!)
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
     * Update delivery status test
     *
     */
    @Test
    fun updateDeliveryStatusTest() {
        val repository = mock(DeliveryRepository::class.java)
        Mockito.`when`(repository.findById(Mockito.anyLong())).thenReturn(getMonoDelivery())
        Mockito.`when`(repository.save(Mockito.any())).thenReturn(getMonoDelivery())
        runBlocking {
            deliveryServiceImpl = DeliveryServiceImpl(repository)
            val service = deliveryServiceImpl.updateDeliveryStatus(101)
            Assertions.assertNotNull(service)
            Assertions.assertNotNull(service.block())
            val delivery = service.block()
            Assertions.assertNotNull(delivery!!)
            Assertions.assertEquals(101, delivery.deliveryId)
            Assertions.assertEquals("Bananas", delivery.product)
            Assertions.assertEquals("JungleInc", delivery.supplier)
            Assertions.assertEquals(1000000, delivery.quantity)
            Assertions.assertEquals("2027-01-08T07:17:48.237Z", delivery.expectedDate)
            Assertions.assertEquals("TheMoon", delivery.expectedWarehouse)
            Assertions.assertEquals("RECEIVED", delivery.status)
        }
    }

    /**
     * Get deliveries test
     *
     */
    @Test
    fun getDeliveriesTest() {
        val repository = mock(DeliveryRepository::class.java)
        Mockito.`when`(repository.findAllByStatus(Mockito.anyString())).thenReturn(getFluxDelivery())
        runBlocking {
            deliveryServiceImpl = DeliveryServiceImpl(repository)
            val service = deliveryServiceImpl.getDeliveries()
            Assertions.assertNotNull(service)
            Assertions.assertNotNull(service.block())
            val delivery = service.block()
            Assertions.assertNotNull(delivery!!)
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

    private fun getMonoDelivery(): Mono<Delivery> {
        return Mono.just(getDelivery())
    }

    private fun getFluxDelivery(): Flux<Delivery> {
        return Flux.just(getDelivery())
    }

    private fun getDelivery(): Delivery {
        return Delivery(101, "Bananas", "JungleInc", 1000000, "2027-01-08T07:17:48.237Z", "TheMoon", "RECEIVED")

    }
}