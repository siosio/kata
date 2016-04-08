package kata.ex01

import kata.ex01.model.*

interface DiscountStrategy {
  fun isDiscountTarget(drive: HighwayDrive): Boolean

  fun getDiscountQuotient(drive: HighwayDrive): Long {
    return 0
  }
}
