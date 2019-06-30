package top.ninwoo.selfLearn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadDemo {
    // 生成Doc的线程池
    private static final ExecutorService generateDocPool
            = Executors.newFixedThreadPool(4);
    public static void main(String[] args) {

    }
}
