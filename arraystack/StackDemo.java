public class StackDemo {
  
  private int top;  
  private int[] storage;
 
  public StackDemo(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("Stack's capacity must be positive");
    storage = new int[capacity];
    top = -1;
  }
 
  public void push(int value, int sleep) {
    top++;
    try {
      Thread.sleep(sleep);
    } catch(Exception e) {
    }
    storage[top] = value;
  }
 
  public int peek() {
    return storage[top];
  }
 
  public int pop(int sleep) {
    int p = storage[top];
    try {
      Thread.sleep(sleep);
    } catch(Exception e) {
    }
    if (top > 0) top -- ;
    return p;
  }
 
  public int size() {
    return top + 1;
  }
  
  public boolean isEmpty() {
    return (top == -1);
  }
}
