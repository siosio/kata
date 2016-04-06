package kata.ex01

import kata.ex01.model.HighwayDrive

/**
 * @author kawasima
 */
interface DiscountService {
  fun calc(drive: HighwayDrive): Long
}
