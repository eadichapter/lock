package lock

import spock.lang.Specification
import spock.lang.Unroll

class LockSpec extends Specification {

	Lock lock

	def setup() {
		lock = new Lock()
	}

	@Unroll
	def 'when trying keys: #keys then the lock remains locked'() {
		when: 'accepting keys: #keys'
		accept(keys)

		then: 'the lock remains locked'
		lock.isLocked()
		lock.lampColor() == "red"


		where:
		keys << [
			["1", "2", "3", "4"],
			["1", "7", "3", "1"],
			["7", "1", "3", "1", "2"] //if you give the right sequence and then a wrong key then it gets locked again
		]
	}

	@Unroll
	def 'when trying keys: #keys then the lock gets unlocked'() {
		when: 'accepting keys: #keys'
		accept(keys)

		then: 'the lock is unlocked'
		!lock.isLocked()
		lock.lampColor() == "green"

		where:
		keys << [
			["1", "3", "1"],
			["1", "1", "3", "1"],
			["7", "8", "1", "3", "7", "1", "3", "1"],
		]
	}

	private def accept(def keys) {
		keys.each { lock.accept(it) }
	}

}
