package kata.ex01

import kata.ex01.model.*
import org.hamcrest.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import org.junit.Assert.*
import java.time.*

class LateEveningDiscountStrategyTest {

  val sut = LateEveningDiscountStrategy()

  private fun createDrive(enteredTime: LocalDateTime? = null, exitTime: LocalDateTime? = null): HighwayDrive {
    return HighwayDrive(
        enteredTime ?: LocalDateTime.of(2016, Month.APRIL, 9, 10, 10, 10),
        exitTime ?: LocalDateTime.of(2016, Month.APRIL, 9, 10, 10, 10),
        VehicleFamily.STANDARD,
        RouteType.RURAL,
        Driver(1)
    )
  }

  @Test
  fun `0時から4時に入った場合は割引対象になること`() {
    val drive = createDrive(
        enteredTime = LocalDateTime.of(2016, Month.APRIL, 9, 0, 0, 0)
    )

    val actual = sut.isDiscountTarget(drive)
    assertThat(actual, `is`(true))
  }

  @Test
  fun `0時前に入って4時前に出た場合は割引対象になること`() {
    val drive = createDrive(
        enteredTime = LocalDateTime.of(2016, Month.APRIL, 9, 23, 50, 0),
        exitTime = LocalDateTime.of(2016, Month.APRIL, 10, 3, 30, 0)
    )

    val actual = sut.isDiscountTarget(drive)
    assertThat(actual, `is`(true))
  }


  @Test
  fun `23時台に入って4時台に出た場合は割引対象`() {
    val drive = createDrive(
        enteredTime = LocalDateTime.of(2016, Month.APRIL, 9, 23, 59, 59),
        exitTime = LocalDateTime.of(2016, Month.APRIL, 10, 4, 1, 0)
    )
    val actual = sut.isDiscountTarget(drive)
    assertThat(actual, `is`(true))
  }

  @Test
  fun `23時台に乗って23時台に出た場合は割引対象外`() {
    val drive = createDrive(
        enteredTime = LocalDateTime.of(2016, Month.APRIL, 9, 23, 0, 0),
        exitTime = LocalDateTime.of(2016, Month.APRIL, 9, 23, 59, 59)
    )
    val actual = sut.isDiscountTarget(drive)
    assertThat(actual, `is`(false))
  }
}
