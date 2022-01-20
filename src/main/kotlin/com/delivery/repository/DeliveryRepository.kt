package com.delivery.repository

import com.delivery.model.Delivery
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

/**
 * Delivery repository that holds the database operations like get, save,
 * update and delete data from the table.
 *
 * @constructor Create empty Delivery repository
 */
@Repository
interface DeliveryRepository : ReactiveCrudRepository<Delivery, Long>{

    /**
     * Finds all deliveries by status like that are not received yet.
     *
     * @param status the delivery status
     * @return the delivery details
     */
    fun findAllByStatus(status: String): Flux<Delivery>
}