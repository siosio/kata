package kata.ex01

import kata.ex01.model.*
import kata.ex01.util.*
import java.time.*

class HolidayDiscountStrategy : DiscountStrategy {

  override fun isDiscountTarget(drive: HighwayDrive): Boolean {
    return if (drive.vehicleFamily == VehicleFamily.OTHER
        || drive.routeType != RouteType.RURAL) {
      false
    } else if (equalsWeekOfDay(drive, DayOfWeek.SATURDAY)
        || equalsWeekOfDay(drive, DayOfWeek.SUNDAY)
        || HolidayUtils.isHoliday(drive.enteredAt.toLocalDate())
        || HolidayUtils.isHoliday(drive.exitedAt.toLocalDate())) {
      true
    } else {
      false
    }
  }

  override fun getDiscountQuotient(drive: HighwayDrive): Long {
    return 30L
  }

  /**
   * 曜日が一致するかどうか
   */
  private fun equalsWeekOfDay(drive: HighwayDrive, dayOfWeek: DayOfWeek): Boolean {
    return if (drive.enteredAt.dayOfWeek == dayOfWeek
        || drive.exitedAt.dayOfWeek == dayOfWeek) {
      true
    } else {
      false

    }
  }
}

