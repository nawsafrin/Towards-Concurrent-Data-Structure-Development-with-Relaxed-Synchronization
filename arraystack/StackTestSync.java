import java.util.concurrent.*;

public class StackTestSync {
  public static void main(String args[]) throws Exception {
    int NUM_OF_THREADS = AStackConstants.NUM_OF_THREADS;
    int NUM_OF_OPERATIONS = AStackConstants.NUM_OF_OPERATIONS;
    int NUM_OF_ITERATIONS = AStackConstants.NUM_OF_ITERATIONS;
    long before = 0;
    long after = 0;
    ExecutorService service;
    StackDemoSync list;
    int size = NUM_OF_OPERATIONS * NUM_OF_THREADS * 2;

    for (int iteration = 1; iteration <= NUM_OF_ITERATIONS; iteration++) {
      service = Executors.newFixedThreadPool(2 * NUM_OF_THREADS);
      list = new StackDemoSync(size);    
      if (iteration == NUM_OF_ITERATIONS) {
        before = System.currentTimeMillis();    
      }
      for (int i = 0; i < NUM_OF_THREADS; i++) {
        service.submit(new StackAddSync(list, NUM_OF_OPERATIONS, AStackConstants.SLEEP));
      } 
      for (int i = 0; i < NUM_OF_THREADS; i++) {
        service.submit(new StackRemoveSync(list, NUM_OF_OPERATIONS/2, AStackConstants.SLEEP));
      }
      service.shutdown();
      service.awaitTermination(AStackConstants.TIMEOUT, TimeUnit.MINUTES);
      if (iteration == NUM_OF_ITERATIONS) {
        after = System.currentTimeMillis();
        System.out.println(NUM_OF_THREADS + ", " + AStackConstants.SLEEP + ", " + (NUM_OF_OPERATIONS * NUM_OF_THREADS) + ", "
            +  ((NUM_OF_OPERATIONS/2) * NUM_OF_THREADS) + ", " + list.size() + ", " + (after-before));
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
