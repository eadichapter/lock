package lock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lock {
	
	private List<String> acceptedKeys;
	
	public Lock() {
		acceptedKeys = new ArrayList<String>();
	}
	
	public void accept(String key) {
		acceptedKeys.add(key);
	}
	
	public boolean isLocked() {
		return !acceptedKeys.equals(new ArrayList<String>(Arrays.asList("1", "3", "1")));
	}

}
