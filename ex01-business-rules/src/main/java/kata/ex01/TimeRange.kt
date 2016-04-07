package kata.ex01

import java.time.*

class TimeRange(
    private val from: LocalTime,
    private val to: LocalTime
) {

  /**
   * 指定のfromとtoが、このオブジェクトの範囲を含んでいるかどうか
   */
  fun include(other: TimeRange): Boolean {

    if (other.from.hour < from.hour
        || (other.from.hour == from.hour && other.from.minute < from.minute)) {
      // 開始が範囲より前
      if (other.from.isBefore(other.to) &&
          other.to.isBefore(from)) {
        // 終わりも範囲より前は範囲外
        return false
      } else {
        // それ以外は範囲内
        return true
      }
    } else if (from.equals(other.from)) {
      return true
    } else if (to.equals(other.to)) {
      return true
    }
    return false
  }

  private fun isBefore(time1:LocalTime, time2:LocalTime) {

  }
}