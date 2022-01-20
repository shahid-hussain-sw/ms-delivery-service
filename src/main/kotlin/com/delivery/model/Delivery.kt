package com.delivery.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column


/**
 * Delivery the entity class that holds the database fields.
 *
 * @property deliveryId the delivery id
 * @property product the product
 * @property supplier the supplier
 * @property quantity the quantity
 * @property expectedDate the expected date
 * @property expectedWarehouse the expected warehouse
 * @property status the delivery status
 */
data class Delivery(
    @Id
    @Column("id")
    val deliveryId: Long,

    @Column("product")
    val product: String,

    @Column("supplier")
    val supplier: String,

    @Column("quantity")
    val quantity: Long,

    @Column("expected_date")
    val expectedDate: String,

    @Column("expected_warehouse")
    val expectedWarehouse: String,

    @Column("status")
    var status: String
)
