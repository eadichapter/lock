package lock

import lock.Lock
import spock.lang.Specification

class LockSpec extends Specification {
	
	def "it accepts one number at a time"() {
		given: 'a new lock'
		Lock lock = new Lock()
		
		expect: 'to accept a key'
		lock.accept("1")
	}
	
	def 'trying wrong combinations'() {
		given: 'a new lock'
		Lock lock = new Lock()
		
		when: 'to accept a key'
		lock.accept("1")
		lock.accept("2")
		lock.accept("3")
		lock.accept("4")
		
		then: 'the lock remains locked'
		lock.isLocked() 
	}
	
	def 'trying the right code'() {
		given: 'a new lock'
		Lock lock = new Lock()
		
		when: 'to accept a key'
		lock.accept("1")
		lock.accept("3")
		lock.accept("1")
		
		then: 'the lock is unlocked'
		!lock.isLocked()
	}
	
	
}
