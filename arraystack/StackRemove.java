/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PRITOMEE
 */
public class StackRemove implements Runnable {
  private StackDemo list;
  private int limit;
  private int sleep;
  
  public StackRemove (StackDemo list, int limit, int sleep) {
  	this.list = list;
  	this.limit = limit;
  	this.sleep = sleep;
  }
  
  public void run() {
    for (int i = 1; i <= limit; i++) {
      list.pop(sleep);
    }
  }
}
