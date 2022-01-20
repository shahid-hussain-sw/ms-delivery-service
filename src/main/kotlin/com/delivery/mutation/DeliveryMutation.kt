package com.delivery.mutation

import com.delivery.model.Delivery
import com.delivery.service.DeliveryService
import com.expediagroup.graphql.spring.operations.Mutation
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component

/**
 * Delivery Mutation that updates the delivery status.
 *
 * @property deliveryService  the delivery service
 */
@Component
class DeliveryMutation(private var deliveryService: DeliveryService) : Mutation {

    /**
     * Updates the delivery status against the delivery id.
     *
     * @param deliveryId the delivery id
     * @return the delivery details
     */
    suspend fun updateDeliveryStatus(deliveryId: Long): Delivery {
        val delivery = deliveryService.updateDeliveryStatus(deliveryId)
        return delivery.awaitSingle()
    }
}