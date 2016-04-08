package kata.ex01

import kata.ex01.model.*
import kata.ex01.util.*
import org.hamcrest.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import org.junit.Assert.*
import java.time.*

class HolidayDiscountStrategyTest {

  val sut = HolidayDiscountStrategy()


  @Test
  fun `土曜日・普通車・地方は割引対象`() {
    val drive = HighwayDrive(
        LocalDateTime.of(2016, Month.APRIL, 9, 1, 2, 3),
        LocalDateTime.of(2016, Month.APRIL, 9, 2, 3, 4),
        VehicleFamily.STANDARD,
        RouteType.RURAL,
        Driver(1)
    )

    val actual = sut.isDiscountTarget(drive)
    assertThat(actual, `is`(true))
  }

  @Test
  fun `日曜日・ミニ・地方は割引対象`() {
    val drive = HighwayDrive(
        LocalDateTime.of(2016, Month.APRIL, 10, 1, 2, 3),
        LocalDateTime.of(2016, Month.APRIL, 10, 2, 3, 4),
        VehicleFamily.MINI,
        RouteType.RURAL,
        Driver(1)
    )

    val actual = sut.isDiscountTarget(drive)
    assertThat(actual, `is`(true))
  }

  @Test
  fun `祝日・バイク・地方は割引対象`() {
    val drive = HighwayDrive(
        LocalDateTime.of(2016, Month.FEBRUARY, 11, 11, 2, 3),
        LocalDateTime.of(2016, Month.FEBRUARY, 11, 11, 3, 4),
        VehicleFamily.MINI,
        RouteType.RURAL,
        Driver(1)
    )

    val actual = sut.isDiscountTarget(drive)
    assertThat(actual, `is`(true))
  }

  @Test
  fun `土曜日・その他車種・地方部は割引対象外`() {
    val drive = HighwayDrive(
        LocalDateTime.of(2016, Month.APRIL, 10, 1, 2, 3),
        LocalDateTime.of(2016, Month.APRIL, 10, 2, 3, 4),
        VehicleFamily.OTHER,
        RouteType.RURAL,
        Driver(1)
    )

    val actual = sut.isDiscountTarget(drive)
    assertThat(actual, `is`(false))
  }

  @Test
  fun `土曜日・普通車・都市部は割引対象外`() {
    val drive = HighwayDrive(
        LocalDateTime.of(2016, Month.APRIL, 10, 1, 2, 3),
        LocalDateTime.of(2016, Month.APRIL, 10, 2, 3, 4),
        VehicleFamily.STANDARD,
        RouteType.URBAN,
        Driver(1)
    )

    val actual = sut.isDiscountTarget(drive)
    assertThat(actual, `is`(false))
  }
}

