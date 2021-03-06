package no.kantega.debug.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import no.kantega.debug.log.Logging;

import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.VirtualMachine;
/**
 * I use JDI's capability to query threads for monitors to detect whether several threads are in a deadlock due to 
 * monitor deadlocks 
 * @author marska
 *
 */
public class DeadlockDetector {
	
	/**
	 * Get a list of deadlocked threads if any
	 * @param vm
	 * @return
	 */
	public static List<ThreadReference> deadlockedThreads(final VirtualMachine vm) {
		Logging.info(DeadlockDetector.class,"Looking for deadlocks in vm");
		final List<ThreadReference> lockedThreads=new LinkedList<ThreadReference>();
		try {
			vm.suspend();
		for (ThreadReference threadReference : vm.allThreads()) {
			if(isDeadLocked(threadReference)) {
				lockedThreads.add(threadReference);
			}
		}
		} finally {
			vm.resume();
		}
		
		Logging.info(DeadlockDetector.class,"Found {} deadlocks" , lockedThreads.size());
		return lockedThreads;
		
	}
	

	


	private static boolean isDeadLocked(final ThreadReference threadReference) {
		return isDeadLocked(threadReference, new HashSet<Long>());

	}
	
	public static boolean isDeadLocked(final ThreadReference threadReference, final Set<Long> recursionSet) {
		if(threadReference == null){//monitor chain broken
			return false;
		}
		
		if(recursionSet.contains(threadReference.uniqueID())) {
			return true;
		}
		

		
		recursionSet.add(threadReference.uniqueID());
		return isDeadLocked(waitingForThread(threadReference));
	}
	
	public static String waitingForThread(final VirtualMachine vm, final String waitingThread) {
		for (ThreadReference threadReference : vm.allThreads()) {
			if(threadReference.name().equals(waitingThread)) {
				final ThreadReference waitingForThread = waitingForThread(threadReference);
				return waitingForThread == null ? null : waitingForThread.name();
			}
		}
		throw new IllegalArgumentException("No thread named " + waitingThread);
	}

	private static ThreadReference waitingForThread(
			final ThreadReference threadReference) {
		ObjectReference waitingFor=null;
		try {
			waitingFor = threadReference.currentContendedMonitor();
		} catch (IncompatibleThreadStateException e) {
		}
		if(waitingFor == null) {
			return null;
		}
		else {
			try {
				return waitingFor.owningThread();
			} catch (IncompatibleThreadStateException e) {
				return null;
			}
		}
	}
	
	
}
