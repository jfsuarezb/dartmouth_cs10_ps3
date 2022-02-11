// This class is the KeyValueHolder class
// It's a type dessigned to keep the key and the frequency of the key in one single object
public class KeyValueHolder {
	
	private String key;
	private int value;

	public KeyValueHolder(String key, int value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public int getValue() {
		return value;
	}
}

