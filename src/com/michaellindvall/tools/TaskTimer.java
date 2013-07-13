package com.michaellindvall.tools;

import java.util.concurrent.TimeUnit;

/**
 * @author mlindvall
 * 
 */
public class TaskTimer {
  private long start;
  private long end;
  private boolean hasStarted = false;
  private boolean hasStopped = false;
  

  public void start() {
    start = System.currentTimeMillis();
    hasStarted = true;
    hasStopped = false;
  }

  public void stop() {
    end = System.currentTimeMillis();
    hasStopped = true;
  }

  public void restart() {
    start = System.currentTimeMillis();

  }

  public String timespent() {
    String value = "hasn\'t started";
    if (hasStarted) {
      long ts = System.currentTimeMillis() - start;
      if (hasStopped) {
        ts = end - start;
      }
        
      value = String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(ts), TimeUnit.MILLISECONDS.toSeconds(ts)
          - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ts)));
    }
    return value;
  }
  
  @Override
  public String toString() {
    return timespent();
  }
}
