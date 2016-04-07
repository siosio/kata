package kata.ex01

import kata.ex01.model.*
import org.hamcrest.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import org.junit.Assert.*
import java.time.*

class HolidayDiscountStrategyTest {

  val sut = HolidayDiscountStrategy()

  @Test
  fun `休日・普通車・地方`() {
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
}

