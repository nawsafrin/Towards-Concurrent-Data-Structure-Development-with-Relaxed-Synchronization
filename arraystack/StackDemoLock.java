import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class StackDemoLock {
  
  private int top;  
  private int[] storage;
  private WriteLock lock = new ReentrantReadWriteLock().writeLock();

  public StackDemoLock(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("Stack's capacity must be positive");
    storage = new int[capacity];
    top = -1;
  }
 
  public void push(int value, int sleep) {
    //if (top == storage.length) throw new StackException("Stack's underlying storage is overflow");
    lock.lock();
    top++;
    try {
      Thread.sleep(sleep);
    } catch(Exception e) {
    }
    storage[top] = value;
    lock.unlock();
  }
 
  public int peek() {
    //if (top == -1) throw new StackException("Stack is empty");
    return storage[top];
  }
 
  public int pop(int sleep) {
    //if (top == -1) throw new StackException("Stack is empty");
    lock.lock();
    int p = storage[top];
    try {
      Thread.sleep(sleep);
    } catch(Exception e) {
    }
    if (top > 0) top -- ;
    lock.unlock();
    return p;
  }
 
  public int size() {
    return top + 1;
  }
  
  public boolean isEmpty() {
    return (top == -1);
  }
}
