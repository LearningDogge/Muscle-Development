public class GlobalSettings {

    private static final GlobalSettings GLOBAL_SETTINGS = new GlobalSettings();

    int intensity;
    double hours_of_sleep;
    int days_between_workouts;
    int slow_twitch_fibers_percent;
    boolean isLift;

    // 这个没用到，netlogo中没有此变量
    int tick_duration;

    private GlobalSettings() {
    }

    public static GlobalSettings getInstance() {
        return GLOBAL_SETTINGS;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public double getHours_of_sleep() {
        return hours_of_sleep;
    }

    public void setHours_of_sleep(double hours_of_sleep) {
        this.hours_of_sleep = hours_of_sleep;
    }

    public int getDays_between_workouts() {
        return days_between_workouts;
    }

    public void setDays_between_workouts(int days_between_workouts) {
        this.days_between_workouts = days_between_workouts;
    }

    public int getSlow_twitch_fibers_percent() {
        return slow_twitch_fibers_percent;
    }

    public void setSlow_twitch_fibers_percent(int slow_twitch_fibers_percent) {
        this.slow_twitch_fibers_percent = slow_twitch_fibers_percent;
    }

    public boolean isLift() {
        return isLift;
    }

    public void setLift(boolean lift) {
        isLift = lift;
    }

    public int getTick_duration() {
        return tick_duration;
    }

    public void setTick_duration(int tick_duration) {
        this.tick_duration = tick_duration;
    }
}
