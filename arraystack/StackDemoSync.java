public class StackDemoSync {
  
  private int top;  
  private int[] storage;
 
  public StackDemoSync(int capacity) {
    if (capacity <= 0) throw new IllegalArgumentException("Stack's capacity must be positive");
    storage = new int[capacity];
    top = -1;
  }
 
  public synchronized void pushOrPop(int d, int sleep, int op) {
    if (op == 1) {
      push(d, sleep);
    }
    else {
      pop(sleep);
    }
  }
  
  public synchronized void push(int value, int sleep) {
    //if (top == storage.length) throw new StackException("Stack's underlying storage is overflow");
    top++;
    try {
      Thread.sleep(sleep);
    } catch(Exception e) {
      System.out.println(e);
    }
    storage[top] = value;
  }
 
  public int peek() {
    //if (top == -1) throw new StackException("Stack is empty");
    return storage[top];
  }
 
  public synchronized int pop(int sleep) {
    //if (top == -1) throw new StackException("Stack is empty");
    int p = storage[top];
    try {
      Thread.sleep(sleep);
    } catch(Exception e) {
      System.out.println(e);
    }
    if (top > 0) top -- ;
    return p;
  }
 
  public synchronized int size() {
    return top + 1;
  }
  
  public boolean isEmpty() {
    return (top == -1);
  }
}
