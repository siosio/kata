package kata.ex01

import kata.ex01.model.*
import java.time.chrono.*

/**
 * 深夜割引
 */
class LateEveningDiscountStrategy : DiscountStrategy {

  companion object {
    private val lateEveningTime = (0..4)
  }

  override fun isDiscountTarget(drive: HighwayDrive): Boolean {
//    drive.enteredAt.isBefore(ChronoLocalDateTime.from())
    val enteredTime = drive.enteredAt.toLocalTime()
    val exitTime = drive.exitedAt.toLocalTime()

    if (drive.enteredAt.hour in lateEveningTime) {
      return true
    }
    return false
  }
}