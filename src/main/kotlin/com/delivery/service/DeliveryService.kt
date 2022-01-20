package com.delivery.service

import com.delivery.model.Delivery
import reactor.core.publisher.Mono

/**
 * Service that holds all the methods that interacting with repository.
 *
 * @constructor Create empty Delivery service
 */
interface DeliveryService {

    /**
     * Gets all deliveries details that are received or not received.
     *
     * @param status the delivery status
     * @return the list of deliveries details
     */
    suspend fun getDeliveries(status:String): Mono<List<Delivery>>

    /**
     * Updates delivery status against the delivery id.
     *
     * @param deliveryId the delivery id
     * @return the delivery details
     */
    suspend fun updateDeliveryStatus(deliveryId: Long): Mono<Delivery>

}