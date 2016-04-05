package kata.ex01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import kata.ex01.model.HighwayDrive;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {

    private Function<HighwayDrive, Integer> discountHoliday = drive -> {
        if (drive.isHoliday() && drive.isStandardAndMini() && drive.isRural()) {
            return 30;
        }
        return 0;
    };

    private Function<HighwayDrive, Integer> discountLateAtNight = drive -> {
        if (drive.isLateAtNight()) {
            return 30;
        }
        return 0;
    };

    private Function<HighwayDrive, Integer> discountWeekdayMorningAndEvening = drive -> {
        if (!drive.isHoliday() && drive.isMorningAndEvening()) {
            final int countPerMonth = drive.getDriver()
                                   .getCountPerMonth();
            if (countPerMonth >= 10) {
                return 50;
            } else if (countPerMonth >= 5 && countPerMonth <= 9) {
                return 30;
            }
        }
        return 0;
    };

    private List<Function<HighwayDrive, Integer>> discountStrategies =
            Arrays.asList(discountHoliday, discountLateAtNight, discountWeekdayMorningAndEvening);

    @Override
    public long calc(HighwayDrive drive) {
        return discountStrategies.stream()
                                 .mapToInt(function -> function.apply(drive))
                                 .filter(discount -> discount > 0)
                                 .findFirst()
                                 .orElse(0);
    }
}
