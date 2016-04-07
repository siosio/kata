package kata.ex01;

import kata.ex01.model.Driver;
import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static kata.ex01.model.VehicleFamily.*;
import static org.junit.Assert.*;

/**
 * @author kawasima
 */
public class DiscountServiceTest {
    DiscountService discountService;

    @Before
    public void setUp() {
        discountService = new DiscountServiceImpl();
    }

    @Test
    public void test平日朝夕割引() {
        HighwayDrive drive = new HighwayDrive(
                LocalDateTime.of(2016, 3, 31, 23, 0),
                LocalDateTime.of(2016, 4, 1, 6, 30),
                STANDARD,
                RouteType.RURAL,
                new Driver(10)
        );

        assertEquals(50, discountService.calc(drive));
    }

    @Test
    public void test休日朝夕は休日割が適用される() {
        HighwayDrive drive = new HighwayDrive(
                LocalDateTime.of(2016, 4, 1, 23, 0),
                LocalDateTime.of(2016, 4, 2, 6, 30),
                STANDARD,
                RouteType.RURAL,
                new Driver(10)
        );
        assertEquals(30, discountService.calc(drive));
    }

}
