package com.delivery.service.impl

import com.delivery.enum.DeliveryStatus
import com.delivery.model.Delivery
import com.delivery.repository.DeliveryRepository
import com.delivery.service.DeliveryService
import com.delivery.util.DeliveryUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * Delivery service impl that implementing the delivery service.
 *
 * @property deliveryRepository the delivery repository
 */
@Service
class DeliveryServiceImpl(private var deliveryRepository: DeliveryRepository) : DeliveryService {

    /** @inheritdoc */
    override suspend fun getDeliveries(status:String): Mono<List<Delivery>> {
        return deliveryRepository.findAllByStatus(status).collectList()
    }

    /** @inheritdoc */
    override suspend fun updateDeliveryStatus(deliveryId: Long): Mono<Delivery> {
        return getDeliveryDetailsWithId(deliveryId).map { data ->
            val status = DeliveryUtil.getStatus(data.expectedDate)
            if (DeliveryStatus.RECEIVED.toString().equals(status)) {
                data.status = status
                deliveryRepository.save(data).subscribe()
            }
            data
        }.switchIfEmpty(Mono.just(Delivery(deliveryId, "", "", 0, "", "", "")))
    }

    /**
     * Gets delivery details against the delivery id.
     *
     * @param deliveryId the delivery id
     * @return the delivery details
     */
    private fun getDeliveryDetailsWithId(deliveryId: Long): Mono<Delivery> {
        return deliveryRepository.findById(deliveryId)
    }

}