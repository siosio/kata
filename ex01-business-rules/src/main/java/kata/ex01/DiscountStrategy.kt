package kata.ex01

import kata.ex01.model.*

interface DiscountStrategy {
  fun isDiscountTarget(drive: HighwayDrive): Boolean

  fun getDiscountQuotient(): Long {
    return 0
  }
}