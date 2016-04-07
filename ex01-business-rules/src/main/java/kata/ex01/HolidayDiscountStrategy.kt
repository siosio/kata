package kata.ex01

import kata.ex01.model.*

class HolidayDiscountStrategy : DiscountStrategy {

  override fun isDiscountTarget(drive: HighwayDrive): Boolean {
    return false
  }
}

