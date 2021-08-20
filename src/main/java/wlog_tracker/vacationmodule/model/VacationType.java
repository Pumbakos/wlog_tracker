package wlog_tracker.vacationmodule.model;

public enum VacationType {
    MEDICAL_LEAVE("medical leave"),
    LEAVE_ON_REQUEST("leave on request"),
    EARNED_LEAVE("earned leave"),
    RELIGIOUS_LEAVE("religious leave");

    private String name;

    VacationType(String name) {
        this.name = name;
    }
}
