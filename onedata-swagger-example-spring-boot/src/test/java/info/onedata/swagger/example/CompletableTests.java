//package info.onedata.swagger.example;
//
//import io.swagger.models.auth.In;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.locks.LockSupport;
//
//@SpringBootTest
//class CompletableTests {
//
//    @Test
//    public void test1() throws InterruptedException {
//        Thread t1 = new Thread(() -> {
//            LockSupport.park();
//            System.out.println("t1, unpark");
//        }, "t1");
//        t1.start();
//
//        System.out.println("t1 parking");
//        Thread.sleep(5000);
//
//        LockSupport.unpark(t1);
//    }
//
//
//    @Test
//    public void test2() throws InterruptedException {
//        Thread t1 = new Thread(() -> {
//            try {
//                LockSupport.park();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println("t1, unpark");
//        }, "t1");
//        t1.start();
//
//        System.out.println("t1 parking");
//        Thread.sleep(5000);
//
//        // LockSupport.unpark(t1);
//    }
//
//    @Test
//    public void test3() throws InterruptedException, ExecutionException {
//        // 业务1
//        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return ((int) (Math.random() * 100));
//        });
//
//        // 业务2
//        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return ((int) (Math.random() * 100));
//        });
//
//
//        CompletableFuture.allOf(completableFuture1, completableFuture2).get();
//
//        System.out.println("completableFuture1 = " + completableFuture1.get());
//        System.out.println("completableFuture2 = " + completableFuture2.get());
//        Thread.sleep(10000);
//    }
//
//    @Test
//    public void test4() throws InterruptedException, ExecutionException {
//        // 业务1
//        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//                System.out.println("当前线程名：" + Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            int a = 1 / 0;
//            return ((int) (Math.random() * 100));
//        }).whenCompleteAsync((result, e) -> {
//            System.out.println("当前线程名：" + Thread.currentThread().getName());
//            if (e == null) {
//                System.out.println(result);
//            }
//        }).exceptionally((e) -> {
//            System.out.println("当前线程名：" + Thread.currentThread().getName());
//            System.out.println(e.getMessage());
//            return -1;
//        }) ;
//
//        System.out.println("completableFuture1 = " + completableFuture1.get());
//        Thread.sleep(10000);
//    }
//
//    @Test
//    public void test5() throws InterruptedException, ExecutionException {
//        // 业务1
//        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 1;
//        }) ;
//
//        // 业务1
//        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 2;
//        }) ;
//
//        CompletableFuture<Integer> completableFuture = completableFuture1.applyToEitherAsync(completableFuture2, (result) -> result);
//        Integer integer = completableFuture.get();
//        System.out.println("result: " + integer);
//        Thread.sleep(10000);
//    }
//
//    @Test
//    public void test6() throws InterruptedException, ExecutionException {
//        // 业务1
//        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 1;
//        }).thenApply((result1) -> result1 * 3);
//
//        System.out.println("completableFuture1.get() = " + completableFuture1.get());
//
//        // 业务1
//        CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 5;
//        }).thenAccept(System.out::println);
//
//        Thread.sleep(4000);
//    }
//
//    @Test
//    public void test7() throws InterruptedException, ExecutionException {
//        // 业务1
//        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 10;
//        });
//
//        // 业务2
//        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 5;
//        });
//
//        CompletableFuture<Integer> completableFuture =
//                completableFuture1.thenCombine(completableFuture2, (result1, result2) -> result1 + result2);
//
//        System.out.println(completableFuture.get());
//        Thread.sleep(4000);
//    }
//
//
//    @Test
//    public void test8() throws InterruptedException, ExecutionException {
//        // 业务1
//        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 10;
//        }).thenCompose((item1) -> CompletableFuture.supplyAsync(() -> item1 * 10))
//                .thenCompose(item2 -> CompletableFuture.supplyAsync(() -> item2 * 10));
//
//        System.out.println(completableFuture1.get());
//        Thread.sleep(4000);
//    }
//}
