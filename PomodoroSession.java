public enum PomodoroSession {
    WORK("Work"),
    SHORT_BREAK("Short Break"),
    LONG_BREAK("Long Break");

    private final String name;

    PomodoroSession(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}