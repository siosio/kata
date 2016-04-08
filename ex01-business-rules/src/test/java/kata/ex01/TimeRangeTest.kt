package kata.ex01

import org.hamcrest.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import org.junit.Assert.assertThat
import java.time.*

class TimeRangeTest {

  @Test
  fun 開始終了が同じ場合は範囲内() {
    val sut = TimeRange(LocalTime.of(0, 0, 0), LocalTime.of(4, 0, 0))

    val actual = sut.include(
        TimeRange(LocalTime.of(0, 0, 0), LocalTime.of(4, 0, 0)))
    assertThat(actual, `is`(true))
  }

  @Test
  fun 開始は前で終わりが後の場合は範囲内() {
    val sut = TimeRange(LocalTime.of(0, 0, 0), LocalTime.of(4, 0, 0))

    val actual = sut.include(
        TimeRange(LocalTime.of(23, 50, 0), LocalTime.of(4, 1, 0)))
    assertThat(actual, `is`(true))
  }

  @Test
  fun 開始は前だが終わりが範囲内の場合は範囲内() {
    val sut = TimeRange(LocalTime.of(0, 0, 0), LocalTime.of(4, 0, 0))

    val actual = sut.include(
        TimeRange(LocalTime.of(23, 50, 0), LocalTime.of(3, 50, 0)))
    assertThat(actual, `is`(true))
  }

  @Test
  fun 開始も終わりも範囲の開始前は範囲外() {
    val sut = TimeRange(LocalTime.of(0, 0, 0), LocalTime.of(4, 0, 0))

    val actual = sut.include(
        TimeRange(LocalTime.of(23, 50, 0), LocalTime.of(23, 59, 0)))
    assertThat(actual, `is`(false))
  }

  @Test
  fun 分単位で範囲内の場合() {
    val sut = TimeRange(LocalTime.of(1, 0, 0), LocalTime.of(1, 30, 0))

    val actual = sut.include(
        TimeRange(LocalTime.of(1, 25, 0), LocalTime.of(1, 31, 0)))
    assertThat(actual, `is`(true))
  }

  @Test
  fun 分単位で範囲を内包している場合() {
    val sut = TimeRange(LocalTime.of(1, 0, 0), LocalTime.of(1, 30, 0))

    val actual = sut.include(
        TimeRange(LocalTime.of(0, 59, 0), LocalTime.of(1, 31, 0)))
    assertThat(actual, `is`(true))
  }

  @Test
  fun 分単位で範囲外の場合() {
    val sut = TimeRange(LocalTime.of(1, 0, 0), LocalTime.of(1, 30, 0))

    val actual = sut.include(
        TimeRange(LocalTime.of(1, 31, 0), LocalTime.of(1, 32, 0)))
    assertThat(actual, `is`(false))
  }

  @Test
  fun 分単位で範囲外_範囲より前の場合() {
    val sut = TimeRange(LocalTime.of(1, 0, 0), LocalTime.of(1, 30, 0))

    val actual = sut.include(
        TimeRange(LocalTime.of(0, 50, 0), LocalTime.of(0, 59, 0)))
    assertThat(actual, `is`(false))
  }
}
