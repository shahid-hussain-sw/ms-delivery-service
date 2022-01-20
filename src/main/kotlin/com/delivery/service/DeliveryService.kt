package com.delivery.service

import com.delivery.model.Delivery
import reactor.core.publisher.Mono

interface DeliveryService {

    /**
     * Gets all deliveries details that are received or not received.
     *
     * @return the list of deliveries details
     */
    suspend fun getDeliveries(): Mono<List<Delivery>>

    /**
     * Updates delivery status against the delivery id.
     *
     * @param deliveryId the delivery id
     * @return the delivery details
     */
    suspend fun updateDeliveryStatus(deliveryId: Long): Mono<Delivery>

}