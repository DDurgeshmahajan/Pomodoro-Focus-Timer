public class PomodoroManager {

    private static final int WORK_DURATION = 25 * 60;
    private static final int SHORT_BREAK_DURATION = 5 * 60;
    private static final int LONG_BREAK_DURATION = 20 * 60;

    private int pomodoroCount;
    private PomodoroSession currentSession;
    private CountdownTimer countdownTimer;

    public PomodoroManager() {
        pomodoroCount = 1;
        currentSession = PomodoroSession.WORK;
        countdownTimer = new CountdownTimer(WORK_DURATION);
    }

    public void startOrResumeSession() {
        if (countdownTimer.isFinished()) {
            startNextSession();
        } else {
            countdownTimer.start();
        }
    }

    public void pauseSession() {
        countdownTimer.pause();
    }

    public void resetSession() {
        countdownTimer.reset(getCurrentSessionDuration());
    }

    public void resetCycle() {
        pomodoroCount = 1;
        currentSession = PomodoroSession.WORK;
        countdownTimer.reset(WORK_DURATION);
    }

    public void startNextSession() {
        if (pomodoroCount == 4) {
            currentSession = PomodoroSession.LONG_BREAK;
            pomodoroCount = 1;
            countdownTimer.reset(LONG_BREAK_DURATION);
        } else if (currentSession == PomodoroSession.WORK) {
            currentSession = PomodoroSession.SHORT_BREAK;
            countdownTimer.reset(SHORT_BREAK_DURATION);
        } else if (currentSession == PomodoroSession.SHORT_BREAK) {
            currentSession = PomodoroSession.WORK;
            pomodoroCount++;
            countdownTimer.reset(WORK_DURATION);
        }

        countdownTimer.start();
    }

    public PomodoroSession getCurrentSessionType() {
        return currentSession;
    }

    public int getCurrentPomodoroCount() {
        return pomodoroCount;
    }

    public int getRemainingTimeInSeconds() {
        return countdownTimer.getRemainingTimeInSeconds();
    }

    private int getCurrentSessionDuration() {
        switch (currentSession) {
            case WORK:
                return WORK_DURATION;
            case SHORT_BREAK:
                return SHORT_BREAK_DURATION;
            case LONG_BREAK:
                return LONG_BREAK_DURATION;
            default:
                return 0;
        }
    }
}