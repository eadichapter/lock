package lock

import lock.Lock
import spock.lang.Specification

class LockSpec extends Specification {

	Lock lock
	
	def setup() {
		lock = new Lock()
	}
		
	def 'trying wrong combinations'() {
		when: 'accepting wrong keys'
		accept(["1", "2", "3", "4"])
		
		then: 'the lock remains locked'
		lock.isLocked() 
	}
	
	def 'trying the right code'() {
		when: 'to accepting the right keys'
		accept(["1", "3", "1"])
		
		then: 'the lock is unlocked'
		!lock.isLocked()
	}
	
	private def accept(def keys) {
		keys.each { lock.accept(it) }
	}
	
}
