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
    return if (other.from.isAfter(from) && other.from.isBefore(to)) {
      // 開始がこの範囲の中の場合
      true
    } else if (other.from.equals(from) || other.from.equals(to)) {
      // 開始が範囲の開始または終わりと同じ場合
      true
    } else if (other.to.isAfter(from) && other.to.isBefore(to)) {
      // 終わりこの範囲の中の場合
      true
    } else if (other.to.equals(from) || other.to.equals(to)) {
      // 終わりが範囲の開始または終わりと同じ場合
      true
    } else if (other.from.isBefore(from) && other.to.isAfter(to)) {
      // この範囲を内包している場合
      true
    } else if (other.from.isAfter(other.to) && other.to.isAfter(to)){
      true
    } else {
      false
    }
  }
}
