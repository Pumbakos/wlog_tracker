package wlog_tracker.vacationmodule;

import lombok.SneakyThrows;
import wlog_tracker.vacationmodule.model.Vacation;
import wlog_tracker.vacationmodule.model.VacationType;

import java.text.SimpleDateFormat;

public class VacationGenerator {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy, HH:mm:ss a z");

    @SneakyThrows
    public static Vacation createCompleteVacation() {
        Vacation vacation = new Vacation();
        vacation.setStartDate(dateFormat.parse("Aug 16, 2021, 12:00:00 PM UTC"));
        vacation.setEndDate(dateFormat.parse("Aug 26, 2021, 12:00:00 PM UTC"));
        vacation.setVacationType(VacationType.MEDICAL_LEAVE);

        return vacation;
    }

    @SneakyThrows
    public static Vacation createAnotherCompleteVacation() {
        Vacation vacation = new Vacation();
        vacation.setStartDate(dateFormat.parse("Aug 13, 2021, 8:00:00 PM UTC"));
        vacation.setEndDate(dateFormat.parse("Aug 30, 2021, 16:00:00 PM UTC"));
        vacation.setVacationType(VacationType.RELIGIOUS_LEAVE);

        return vacation;
    }

    public static Vacation createBlankVacation() {
        return new Vacation();
    }
}
