
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StackDemoLock {

  private int top;
  private int [] storage;
  private int pushFlag = 0;
  private int popFlag = 0;
  private int pushFlagCount = 0;
  private int popFlagCount = 0;
  private int timeout = 60;
  private int functionCalled = 0;
  private static final int timeadd = 60;
  private static final int sample = 10;
  private static final int error = 20;
  private static final boolean log = false;
  private final Lock lock = new ReentrantLock();

  public StackDemoLock(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("Stack's capacity must be positive");
    storage = new int[capacity];
    top = -1;
  }

  public void push(int value, int sleep) {
    functionCalled++;
    try {
      //lock.lock();
      lock.tryLock(timeout,TimeUnit.SECONDS);
      top++;
      Thread.sleep(sleep);
      storage[top] = value;
	  pushFlag =1;
    } catch(Exception e) {
      System.out.println(e);
    } finally {
      if(pushFlag ==1) pushFlagCount++;
      pushFlag = 0;
      lock.unlock();
    }

    int currentError = 100*(Math.abs(functionCalled - pushFlagCount))/ functionCalled;
    if(functionCalled % sample == 0)
    {
      if (log) System.out.print(currentError + " -> ");
      if (log) System.out.print(timeout + " -> ");
      if(currentError > error) {
        timeout = timeout + timeadd;
      } else if (currentError < error && timeout >0) {
		timeout = timeout - timeadd;
      }
      if (log) System.out.println(timeout);
    }

  }



  public int peek() {
    return storage[top];
  }

  public int pop(int sleep) {
    int p = 0;
    try {
     // lock.lock();
      lock.tryLock(timeout,TimeUnit.SECONDS);
      p = storage[top];
      Thread.sleep(sleep);
      if (top > 0) top -- ;
	  popFlag =1;
    } catch(Exception e) {
    } finally {
      if(popFlag ==1) popFlagCount++;
      popFlag = 0;
      lock.unlock();

    }
    return p;
  }

  public int pushCount() {
    return pushFlagCount;
  }

  public int popCount() {
    return popFlagCount;
  }

  public int size() {
    return top + 1;
  }

  public boolean isEmpty() {
    return (top == -1);
  }
}