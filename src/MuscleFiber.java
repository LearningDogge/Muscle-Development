import java.util.Random;

public class MuscleFiber {
    // muscle 属性
    private Double fiber_size;
    private Double max_size;

    // patch 属性
    private Double anabolic_hormone;
    private Double catabolic_hormone;

    // 全局的最大最小值
    private static Double anabolic_hormone_max = 200.0;
    private static Double anabolic_hormone_min = 50.0;
    private static Double catabolic_hormone_max = 250.0;
    private static Double catabolic_hormone_min = 52.0;

    private static final Random random = new Random();

    // 合并了netlogo里的patch和muscle
    public MuscleFiber() {

        // patch初始化部分initialize-hormones
        anabolic_hormone = 50.0;
        catabolic_hormone = 52.0;

        // muscle初始化部分 sprout-muscle-fibers 1
        // 每个patch创建一个muscle，所以每个patch对应一个muscle
        max_size = 4.0;
        for (int i = 0; i < 20; i++) {
            if (random.nextFloat() * 100 > GlobalSettings.getInstance().getSlow_twitch_fibers_percent()) {
                max_size = max_size + 1;
            }
        }
        fiber_size = max_size * (0.2 + random.nextFloat() * 0.4);

        regulate_muscle_fibers();
    }

    // 以下的函数都跟netlogo代码中一一对应
    public void perform_daily_activity() {
        catabolic_hormone = catabolic_hormone + 2.0 * Math.log10(fiber_size);
        anabolic_hormone = anabolic_hormone + 2.5 * Math.log10(fiber_size);
    }

    public void lift_weights() {
        double intensity = GlobalSettings.getInstance().getIntensity() / 100.0;
        if (random.nextFloat() < intensity * intensity) {
            anabolic_hormone = anabolic_hormone + Math.log10(fiber_size) * 55;
            catabolic_hormone = catabolic_hormone + Math.log10(fiber_size) * 44;
        }
    }

    public void sleep() {
        double hours_of_sleep = GlobalSettings.getInstance().getHours_of_sleep();
        catabolic_hormone = catabolic_hormone - 0.5 * Math.log10(catabolic_hormone) * hours_of_sleep;
        anabolic_hormone = anabolic_hormone - 0.48 * Math.log10(anabolic_hormone) * hours_of_sleep;
    }

    public void grow() {
        fiber_size = fiber_size - 0.20 * Math.log10(catabolic_hormone);
        fiber_size = fiber_size + 0.20 * Math.min(Math.log10(anabolic_hormone), 1.05 * Math.log10(catabolic_hormone));
    }

    public void regulate_muscle_fibers() {
        if (fiber_size < 1.0) {
            fiber_size = 1.0;
        }
        if (fiber_size > max_size) {
            fiber_size = max_size;
        }
    }

    public void regulate_hormones() {
        anabolic_hormone = Math.min(anabolic_hormone, anabolic_hormone_max);
        anabolic_hormone = Math.max(anabolic_hormone, anabolic_hormone_min);
        catabolic_hormone = Math.min(catabolic_hormone, catabolic_hormone_max);
        catabolic_hormone = Math.max(catabolic_hormone, catabolic_hormone_min);
    }

    // diffuse 的时候会有取值和赋值，会用到这四个函数
    public Double getAnabolic_hormone() {
        return anabolic_hormone;
    }

    public void setAnabolic_hormone(Double anabolic_hormone) {
        this.anabolic_hormone = anabolic_hormone;
    }

    public Double getCatabolic_hormone() {
        return catabolic_hormone;
    }

    public void setCatabolic_hormone(Double catabolic_hormone) {
        this.catabolic_hormone = catabolic_hormone;
    }
}