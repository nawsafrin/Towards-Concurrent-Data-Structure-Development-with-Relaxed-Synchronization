import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class StackDemoLock {
  
  private int top;  
  private int[] storage;
//  private WriteLock lock = new ReentrantReadWriteLock().writeLock();
   private final Lock lock = new ReentrantLock();
  public StackDemoLock(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("Stack's capacity must be positive");
    storage = new int[capacity];
    top = -1;
  }
 
  public void push(int value, int sleep) {
    try {
      //lock.lock();
      lock.tryLock(30,TimeUnit.SECONDS);
      top++;
      Thread.sleep(sleep);
      storage[top] = value;
    } catch(Exception e) {
    } finally {
      lock.unlock();
    }
  }
 
  public int peek() {
    return storage[top];
  }
 
  public int pop(int sleep) {
    int p = 0;
    try {
     // lock.lock();
      lock.tryLock(30,TimeUnit.SECONDS);
      p = storage[top];
      Thread.sleep(sleep);
      if (top > 0) top -- ;
    } catch(Exception e) {
    } finally {
      lock.unlock();
    }
    return p;
  }
 
  public int size() {
    return top + 1;
  }
  
  public boolean isEmpty() {
    return (top == -1);
  }
}
