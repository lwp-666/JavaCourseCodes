package week04.topic2;

import lombok.Data;

import java.util.concurrent.*;


/**
 * @author
 */
public class Homework04 {
    
    public static void main(String[] args) throws InterruptedException {

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

       //1
        FutureTask<Integer> task1 = new FutureTask<>(Homework04::sum);
        task1.run();
        try {
            System.out.println("task1 result = " + task1.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //2
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(Homework04::sum);
        try {
            System.out.println("task2 result = " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        //3
        Entity entity = new Entity();
        Thread task3 = new Thread(new MyRunnable(entity));
        task3.start();
        task3.join();
        System.out.println("task2 result = " + entity.getNum());

        // 然后退出main线程
    }
    
    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    private static class MyRunnable implements Runnable {

        private Entity entity;

        public MyRunnable(Entity entity) {
            this.entity = entity;
        }

        @Override
        public void run() {
            entity.setNum(sum());
        }
    }
    @Data
    private static class Entity {
        private int num;
    }
}
