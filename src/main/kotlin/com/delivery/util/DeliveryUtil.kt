package com.delivery.util

import com.delivery.enum.DeliveryStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * DeliveryUtil that holds all the common validation.
 *
 */
class DeliveryUtil {

    /**
     * Gets delivery status.
     *
     * @param expectedDate the delivery expected date
     * @return the status like RECEIVED or NOT_RECEIVED
     */
    companion object {
        fun getStatus(expectedDate: String): String {
            val dateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.[SSS]'Z'")
            val localDateTime = LocalDateTime.parse(expectedDate, dateFormater)
            val sysLocalDateTime = LocalDateTime.now()
            if (sysLocalDateTime.isEqual(localDateTime) || sysLocalDateTime.isAfter(localDateTime)) {
                return DeliveryStatus.RECEIVED.toString()
            }
            return DeliveryStatus.NOT_RECEIVED.toString()
        }
    }
}