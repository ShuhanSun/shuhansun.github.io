package app;


import java.io.UncheckedIOException;
import java.util.concurrent.*;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ThreadPoolTest {

    private static ThreadPoolExecutor createPoolExecutor(final int poolSize) {
        final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("titanium-postprocessing-%d")
                .setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.print("uncaughtException Thread: " + t);
                        System.out.println("  Throwable : " + e);
                        if (e instanceof Error) {
                            poolExecutor.shutdown();
                            System.out.println("uncaughtException : poolExecutor shutdown");
                        }
                    }
                })
                .build();
        //
        // Core & Max pool size is set to same number for a reason - we want to run all messages read in parallel; instead of parking them in Queue (as much as possible)
        // 2X Queue size is allocated as a buffer to accommodate approximate values returned by getActiveTasks + getQueue.Size() methods on thread pool executor
        //
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(poolSize, poolSize, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(poolSize * 2),
                namedThreadFactory);
        executor.allowCoreThreadTimeOut(true);
        // by default (unfortunately) the ThreadPoolExecutor will throw an exception
        // when you submit the n+1th job, to have it block you do:
        //        executor.setRejectedExecutionHandler(new QueueRejectedHandler());
        return executor;
    }

    private static final ThreadPoolExecutor poolExecutor = createPoolExecutor(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //        poolExecutor.getThreadFactory().newThread(r).start();
        poolExecutor.execute(r);
        //        final Future<?> future1 = poolExecutor.submit(r);// submit can't use ThreadFactory uncaughtException, exception is in the result (future)
        poolExecutor.execute(r2);

        System.out.println(" main job start");
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException ignored) {
        }

        //        try {
        //            Object o = future.get();
        //        }catch (Throwable t){
        //            System.out.println("r throws : "+ t);
        //        }
        System.out.println("main job end");

        poolExecutor.execute(r2);

    }

    private static Runnable r = () -> {

        System.out.println("running 1");
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException ignored) {
        }

        System.out.println("running 1 throw ..");
//        throw new NoSuchMethodError();
        //        int j = 0;
        //        int i = 0 / j;
        //        j = i;
                throw new NullPointerException();
    };

    private static Runnable r2 = () -> {

        System.out.println("running 2");
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
        }

        System.out.println("running 2 end");
    };
}
