package nachos.threads;

import nachos.machine.*;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Uses the hardware timer to provide preemption, and to allow threads to sleep
 * until a certain time.
 */
public class Alarm {
    /**
     * Allocate a new Alarm. Set the machine's timer interrupt handler to this
     * alarm's callback.
     *
     * <p><b>Note</b>: Nachos will not function correctly with more than one
     * alarm.
     */
    public Alarm() {
	Machine.timer().setInterruptHandler(new Runnable() {
		public void run() { timerInterrupt(); }
	    });
    }

    /**
     * The timer interrupt handler. This is called by the machine's timer
     * periodically (approximately every 500 clock ticks). Causes the current
     * thread to yield, forcing a context switch if there is another thread
     * that should be run.
     * check if theres something in the hash, iterate over it
     * 	for each element, get the value and check the time
     * 	set it to ready if the time has passed
     *
     */
    public void timerInterrupt() {
	KThread.currentThread().yield();
	if(ThreadList.size() != 0) {
		//check all the threads stored in the hashmap for time
		for(Entry<KThread, Long> hash : ThreadList.entrySet()){
			if(hash.getValue() < Machine.timer().getTime()) {
				hash.getKey().ready();
				ThreadList.remove(hash.getKey());
			}
		}
	}
    }

    /**
     * Put the current thread to sleep for at least <i>x</i> ticks,
     * waking it up in the timer interrupt handler. The thread must be
     * woken up (placed in the scheduler ready set) during the first timer
     * interrupt where
     *
     * <p><blockquote>
     * (current time) >= (WaitUntil called time)+(x)
     * </blockquote>
     *
     * @param	x	the minimum number of clock ticks to wait.
     * @see	nachos.machine.Timer#getTime()
     * 
     * Need a list to save tuple (wakeUpTime, threadPointer), sleep thread after it saves
     * 	save as hash with thread as key and time as value
     * Wakeup in timerInterrupt
     * wake up if wakeUpTime < currentTime
     */
    public void waitUntil(long x) {
    Machine.interrupt().disable();
	long wakeTime = Machine.timer().getTime() + x;
	
	while (wakeTime > Machine.timer().getTime()) {
	    KThread.currentThread().yield();
    }
	
    //save the thread and the wakeup time before sleeping it
    ThreadList.put(KThread.currentThread(), wakeTime);
    KThread.currentThread().sleep();
    Machine.interrupt().enable();
    
    }
    
    private HashMap<KThread, Long> ThreadList = new HashMap<KThread, Long>();
}
