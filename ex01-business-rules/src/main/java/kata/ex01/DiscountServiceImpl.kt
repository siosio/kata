package kata.ex01

import kata.ex01.model.HighwayDrive
import kata.ex01.model.RouteType
import kata.ex01.model.VehicleFamily
import kata.ex01.util.HolidayUtils
import java.time.LocalDateTime

/**
 * @author kawasima
 */
class DiscountServiceImpl : DiscountService {

  private val strategies = listOf(
      LateEveningDiscountStrategy(),
      HolidayDiscountStrategy()
  )

  override fun calc(drive: HighwayDrive): Long {
    return strategies.filter {
      it.isDiscountTarget(drive)
    }.map {
      it.getDiscountQuotient(drive)
    }.max() ?: 0
  }
}
