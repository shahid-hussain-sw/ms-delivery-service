package com.delivery.query

import com.delivery.model.Delivery
import com.delivery.service.DeliveryService
import com.expediagroup.graphql.spring.operations.Query
import kotlinx.coroutines.reactive.awaitFirstOrDefault
import org.springframework.stereotype.Component

/**
 * Delivery Query that handles all the query details like to fetch all deliveries details that are received or not
 * received.
 *
 * @property deliveryService  the delivery service
 */
@Component
class DeliveryQuery(private var deliveryService: DeliveryService) : Query {

    /**
     * Gets all deliveries details that received or not received
     *
     * @return the list of deliveries details
     */
    suspend fun getDeliveries(): List<Delivery> {
        return deliveryService.getDeliveries().awaitFirstOrDefault(listOf())
    }
}