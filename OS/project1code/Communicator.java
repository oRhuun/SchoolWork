package nachos.threads;
import java.util.LinkedList;
import nachos.machine.*;

/**
 * A <i>communicator</i> allows threads to synchronously exchange 32-bit
 * messages. Multiple threads can be waiting to <i>speak</i>,
 * and multiple threads can be waiting to <i>listen</i>. But there should never
 * be a time when both a speaker and a listener are waiting, because the two
 * threads can be paired off at this point.
 */
public class Communicator {
    /**
     * Allocate a new communicator.
     */
    public Communicator() {
    }

    /**
     * Wait for a thread to listen through this communicator, and then transfer
     * <i>word</i> to the listener.
     *
     * <p>
     * Does not return until this thread is paired up with a listening thread.
     * Exactly one listener should receive <i>word</i>.
     *
     * @param	word	the integer to transfer.
     */
    public void speak(int word) {
    	Lib.assertTrue(!lk.isHeldByCurrentThread());
    	lk.acquire(); //have to get the lock before anything else
    	speechList.add(word);
    	if(listeners == 0) { //no listeners, speak needs to wait until there is
    		speakSleep.sleep();
    	}
    	listenSleep.wake();
    	listeners--;
    	lk.release(); //finally, release the lock
    }

    /**
     * Wait for a thread to speak through this communicator, and then return
     * the <i>word</i> that thread passed to <tt>speak()</tt>.
     *
     * @return	the integer transferred.
     */    
    public int listen() {
    	Lib.assertTrue(!lk.isHeldByCurrentThread());
    	lk.acquire(); //have to get the lock before anything else
    	listeners++;
    	if(speechList.isEmpty()) { //no speaker, wait until there is
    		listenSleep.sleep();
    	}
    	speakSleep.wake();
    	lk.release();//finally, release the lock
	return speechList.removeFirst();
    }
    
    private Lock lk = new Lock();
    private Condition2 speakSleep = new Condition2(lk);
    private Condition2 listenSleep = new Condition2(lk);
    private LinkedList<Integer> speechList = new LinkedList<Integer>();
    private int listeners = 0; // checks if listener present
}
