import java.util.concurrent.*;

public class StackTestLock {
  public static void main(String args[]) throws Exception {
    int NUM_OF_THREADS = AStackConstants.NUM_OF_THREADS;
    int NUM_OF_OPERATIONS = AStackConstants.NUM_OF_OPERATIONS;
    int NUM_OF_ITERATIONS = AStackConstants.NUM_OF_ITERATIONS;
    //int NUM_OF_THREADS = Integer.parseInt(args[0]);
    int sleep = 0; //Integer.parseInt(args[1]);
    long before = 0;
    long after = 0;
    ExecutorService service;
    FileRead b=new FileRead();
    long timeout=(b.get_time());
    StackDemoLock list;
    int size = NUM_OF_OPERATIONS * NUM_OF_THREADS * 2;

    for (int iteration = 1; iteration <= NUM_OF_ITERATIONS; iteration++) {
      service = Executors.newFixedThreadPool(2 * NUM_OF_THREADS);
      list = new StackDemoLock(size,timeout);    
      if (iteration == NUM_OF_ITERATIONS) {
        before = System.currentTimeMillis();    
      }
      for (int i = 0; i < NUM_OF_THREADS; i++) {
        service.submit(new StackAddLock(list, NUM_OF_OPERATIONS, sleep));
      } 
      /*for (int i = 0; i < NUM_OF_THREADS; i++) {
        service.submit(new StackRemoveLock(list, NUM_OF_OPERATIONS/2, sleep));
      }*/
      service.shutdown();
      service.awaitTermination(AStackConstants.TIMEOUT, TimeUnit.MINUTES);
      if (iteration == NUM_OF_ITERATIONS) {
        after = System.currentTimeMillis();
        System.out.println(NUM_OF_THREADS + ", " + AStackConstants.SLEEP + ", " + (NUM_OF_OPERATIONS * NUM_OF_THREADS) + ", "
            +  ((NUM_OF_OPERATIONS/2) * NUM_OF_THREADS) + ", " + list.size() + ", " + (after-before)+ ", " + list.pushCount() + ", " + list.popCount() + ", " + list.Called());
        /*System.out.println("Total number of nodes created for push: " + NUM_OF_OPERATIONS * NUM_OF_THREADS);
        System.out.println("Total number of nodes popped: " + (NUM_OF_OPERATIONS/2) * NUM_OF_THREADS);
        System.out.println("Total number of nodes actually pushed: " + list.size());   
        System.out.println("Time passed in ms: " + (after - before));*/
      }
      service = null;
      list = null;
      System.gc();
    }
  }
}
