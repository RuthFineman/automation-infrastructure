package wait;

public class WaitConfig {
    private static final int SHORT_WAIT = Integer.parseInt(System.getProperty("wait.short", "3"));
    private static final int MEDIUM_WAIT = Integer.parseInt(System.getProperty("wait.medium", "7"));
    private static final int LONG_WAIT = Integer.parseInt(System.getProperty("wait.long", "15"));

    public static int getShortWait() {
        return SHORT_WAIT;
    }

    public static int getMediumWait() {
        return MEDIUM_WAIT;
    }

    public static int getLongWait() {
        return LONG_WAIT;
    }
}
