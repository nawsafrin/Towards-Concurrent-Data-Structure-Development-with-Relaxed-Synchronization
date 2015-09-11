import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class StackDemoLock {

  private int top;
  private int[] storage;
  private int push_flag=0;
  private int pop_flag=0;
  private int push_flag_count=0;
  private int pop_flag_count=0;
  public int timeout=60;
  private int tp;
//  private WriteLock lock = new ReentrantReadWriteLock().writeLock();
   private final Lock lock = new ReentrantLock();
  public StackDemoLock(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("Stack's capacity must be positive");
    storage = new int[capacity];
    top = -1;
  }

  public void push(int value, int sleep) {
      tp++;
    try {
      //lock.lock();
      lock.tryLock(timeout,TimeUnit.SECONDS);
      top++;
      Thread.sleep(sleep);
      storage[top] = value;
	  push_flag=1;
    } catch(Exception e) {
    } finally {
      if(push_flag==1)push_flag_count++;
      push_flag = 0;
      lock.unlock();

    }
    int c=100*(Math.abs(tp-push_flag_count))/tp;
    if(tp%5==0)
    {
        if(c>10)
        {
            timeout=timeout + 60;
        }
        else if (c<10 && timeout >0)
	{
		timeout=timeout - 60;
	}

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
	  pop_flag=1;
    } catch(Exception e) {
    } finally {
      if(pop_flag==1)pop_flag_count++;
      pop_flag = 0;
      lock.unlock();

    }
    return p;
  }
  public int push_count() {
    return push_flag_count;
  }
  public int pop_count() {
    return pop_flag_count;
  }



  public int size() {
    return top + 1;
  }

  public boolean isEmpty() {
    return (top == -1);
  }
}