public class CountdownTimer {

    private int totalTimeInSeconds;
    private int remainingTimeInSeconds;
    private boolean running;
    private boolean paused;
    private Thread countdownThread;

    public CountdownTimer(int totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
        this.remainingTimeInSeconds = totalTimeInSeconds;
        this.running = false;
        this.paused = false;
    }

    public synchronized void start() {
        if (running || paused) return;
        running = true;
        countdownThread = new Thread(() -> {
            while (remainingTimeInSeconds > 0 && running) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!paused) {
                    remainingTimeInSeconds--;
                }
            }
            running = false;
            System.out.println("\nSession finished! Time for the next phase.");
        });
        countdownThread.start();
    }

    public synchronized void pause() {
        if (!running) return;
        paused = true;
    }

    public synchronized void resume() {
        if (!paused) return;
        paused = false;
    }

    public synchronized void reset(int totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
        this.remainingTimeInSeconds = totalTimeInSeconds;
        running = false;
        paused = false;
    }

    public synchronized int getRemainingTimeInSeconds() {
        return remainingTimeInSeconds;
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized boolean isPaused() {
        return paused;
    }

    public synchronized boolean isFinished() {
        return remainingTimeInSeconds <= 0;
    }
}