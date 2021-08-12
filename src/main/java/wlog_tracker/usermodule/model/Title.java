package wlog_tracker.usermodule.model;

public enum Title {
    DEVELOPER("developer"),
    PM("project manager"),
    TESTER("tester");

    private final String name;

    Title(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
