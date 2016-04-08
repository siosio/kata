package kata.ex01

import kata.ex01.model.*
import java.time.*
import java.time.chrono.*

/**
 * 深夜割引
 */
class LateEveningDiscountStrategy : DiscountStrategy {

  companion object {
    private val lateEveningTime = TimeRange(LocalTime.of(0, 0, 0), LocalTime.of(4, 0, 0))
  }

  override fun isDiscountTarget(drive: HighwayDrive): Boolean {
    return if (lateEveningTime.include(TimeRange(drive.enteredAt.toLocalTime(), drive.exitedAt.toLocalTime()))) {
      true
    } else {
      false
    }
  }

  override fun getDiscountQuotient(drive: HighwayDrive): Long {
    return 30L
  }
}
