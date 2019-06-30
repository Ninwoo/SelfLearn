package top.ninwoo.selfLearn.assist;

/**
 * 用于模拟实际工作中的时间消耗
 */
public class BusiWorkTime {
    public static void work(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
