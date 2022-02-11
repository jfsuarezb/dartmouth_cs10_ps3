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
			arrayToReturn.add(new KeyValueHolder(key, this.freqMap.get(key)));
		}

		return arrayToReturn;
	}

	public void decodeFile(String fileName) {
		this.freqMap = new HashMap<String, Integer>();

		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = input.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					if (!this.freqMap.containsKey(String.valueOf(line.charAt(i)))) {
						this.freqMap.put(String.valueOf(line.charAt(i)), 1);
						continue;
					}
					this.freqMap.put(String.valueOf(line.charAt(i)), this.freqMap.get(String.valueOf(line.charAt(i))) + 1);
				}
			}

			System.out.println(this.freqMap);
		} catch(IOException e) {
			return;
		}
	}
}
