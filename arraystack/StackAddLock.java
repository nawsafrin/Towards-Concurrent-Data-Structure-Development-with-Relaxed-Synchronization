/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PRITOMEE
 */
public class StackAddLock implements Runnable {
  private StackDemoLock list;
  private int limit;
  private int sleep;
  
  public StackAddLock (StackDemoLock list, int limit, int sleep) {
  	this.list = list;
  	this.limit = limit;
  	this.sleep = sleep;
  }
  
  public void run() {
    for (int i = 1; i <= limit; i++) {
      list.push(i, sleep);
    }
  }
}
