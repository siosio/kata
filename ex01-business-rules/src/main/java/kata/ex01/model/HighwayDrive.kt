package kata.ex01.model

import java.time.LocalDateTime

/**
 * @author kawasima
 */
data class HighwayDrive(
    val enteredAt: LocalDateTime,
    val exitedAt: LocalDateTime,
    val vehicleFamily: VehicleFamily,
    val routeType: RouteType,
    val driver: Driver
)

