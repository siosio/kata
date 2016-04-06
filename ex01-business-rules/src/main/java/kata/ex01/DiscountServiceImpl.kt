package kata.ex01

import kata.ex01.model.HighwayDrive
import kata.ex01.model.RouteType
import kata.ex01.model.VehicleFamily
import kata.ex01.util.HolidayUtils
import java.time.LocalDateTime

private val lateAtNight = (0..4)
private val morning = (6..9)
private val evening = (17..20)

fun discountHoliday(drive: HighwayDrive): Long {
  return if ((drive.enteredAt.isHoliday()
      || drive.exitedAt.isHoliday())
      && drive.routeType == RouteType.RURAL
      && drive.vehicleFamily in listOf(VehicleFamily.STANDARD, VehicleFamily.MINI, VehicleFamily.MOTORCYCLE)
  ) {
    30L
  } else {
    0L
  }
}

fun discountLateAtNight(drive: HighwayDrive): Long {
  return if (drive.enteredAt.hour in lateAtNight
      || drive.exitedAt.hour in lateAtNight) {
    30L
  } else {
    0L
  }
}

fun LocalDateTime.isHoliday(): Boolean {
  return HolidayUtils.isHoliday(this.toLocalDate())
}

fun discountWeekday(drive: HighwayDrive): Long {
  val enteredAt = drive.enteredAt
  val exitedAt = drive.exitedAt

  return if ((!enteredAt.isHoliday() && !exitedAt.isHoliday())
      && drive.routeType == RouteType.RURAL
      && (enteredAt.hour in morning || enteredAt.hour in evening
      || exitedAt.hour in morning || exitedAt.hour in evening)) {
    val countPerMonth = drive.driver.countPerMonth
    if (countPerMonth >= 10) {
      50L
    } else if (countPerMonth >= 5) {
      30L
    } else {
      0L
    }
  } else {
    0L
  }
}


/**
 * @author kawasima
 */
class DiscountServiceImpl : DiscountService {

  private val factory = listOf(
      ::discountHoliday,
      ::discountLateAtNight,
      ::discountWeekday
  )

  override fun calc(drive: HighwayDrive): Long {
    return factory.map { it(drive) }.firstOrNull() { it > 0 } ?: 0
  }
}
