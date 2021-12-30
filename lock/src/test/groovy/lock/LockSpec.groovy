package lock

import lock.Lock
import spock.lang.Specification

class LockSpec extends Specification {
	
	def "it accepts one number at a time" () {
		given: 'a new lock'
		Lock lock = new Lock()
		
		expect: 'to accept a key'
		lock.accept("1")
	}
	
}
