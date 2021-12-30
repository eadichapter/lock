package lock

import spock.lang.Specification
import spock.lang.Unroll

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

	@Unroll
	def 'when trying keys: #keys then the lock gets unlocked'() {
		when: 'accepting keys: #keys'
		accept(keys)

		then: 'the lock is unlocked'
		!lock.isLocked()

		where:
		keys << [["1", "3", "1"], ["1", "1", "3", "1"]]
	}

	private def accept(def keys) {
		keys.each { lock.accept(it) }
	}

}
