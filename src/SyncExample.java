import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @author kaikanwu
 * @date 24/01/2020
 */
public class SyncExample {

    public static void doSomethingA() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----- do something A ------");
    }


    public static void doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----- do something B -----");
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        // 1. 使用 lambda 表达式创建 Runnable 接口的匿名实现类
//        Thread thread = new Thread(()->{
//            try {
//                doSomethingA();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"Thread A");
        // 2. 实现 Thread 类的方式，重写 run 方法，来创建线程
        Thread thread = new Thread("threadA"){
            @Override
            public void run() {
                try {
                    doSomethingA();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

        doSomethingB();
        thread.join();

        System.out.println(System.currentTimeMillis() - start);
    }
}
