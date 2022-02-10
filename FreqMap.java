import java.util.*;
import java.io.*;

public class FreqMap {
	
	private Map<String, Integer> freqMap;

	public FreqMap() {
		this.freqMap = new HashMap<String, Integer>();
	}

	public int getEntry(String key) {
		if (!this.freqMap.containsKey(key)) {
			return 0;
		}
		return this.freqMap.get(key);
	}

	public ArrayList<KeyValueHolder> getEntries() {
		ArrayList<KeyValueHolder> arrayToReturn = new ArrayList<KeyValueHolder>();

		for (String key : this.freqMap.keySet()) {
			arrayToReturn.add(new KeyValueHolder(key, this.freqMap.get(key));
		}

		return arrayToReturn;
	}

	public void decodeFile(String fileName) {
		this.freqMap = new HashMap<String, Integer>();
		
		BufferedReader input = new BufferedReader(new FileReader(fileName));
		String line;
		while((line = input.readLine()) != null) {
			for (int i = 0; i < line.length(); i++) {
				if (!this.freqMap.containsKey(line.charAt(i))) {
					this.freqMap.put(line.charAt(i), 0);
					continue;
				}
				this.freqMap.put(line.charAt(i), this.freqMap.get(line.charAt(i)) + 1);
			}
		}
	}
}
