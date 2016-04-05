package kata.ex01.model;

import kata.ex01.util.HolidayUtils;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

/**
 * @author kawasima
 */
@Data
public class HighwayDrive implements Serializable {
    private LocalDateTime enteredAt;
    private LocalDateTime exitedAt;
    private VehicleFamily vehicleFamily;
    private RouteType routeType;

    private Driver driver;

    public boolean isHoliday() {
        return HolidayUtils.isHoliday(enteredAt.toLocalDate()) || HolidayUtils.isHoliday(exitedAt.toLocalDate());
    }

    public boolean isStandardAndMini() {
        return vehicleFamily == VehicleFamily.STANDARD
                || vehicleFamily == VehicleFamily.MINI
                || vehicleFamily == VehicleFamily.MOTORCYCLE;

    }

    public boolean isRural() {
        return routeType == RouteType.RURAL;
    }

    public boolean isLateAtNight() {
        return isLateAtNight(enteredAt.getHour()) || isLateAtNight(exitedAt.getHour());
    }

    private boolean isLateAtNight(int hour) {
        return hour >= 0 && hour <= 4;
    }

    public boolean isMorningAndEvening() {
        return isMorningAndEvening(enteredAt.getHour()) || isMorningAndEvening(exitedAt.getHour());
    }

    private boolean isMorningAndEvening(int hour) {
        return ((hour >= 6) && (hour <= 9)) || ((hour >= 17) && (hour <= 20));
    }

}
