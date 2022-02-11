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
			String fileInChar = "";
			String currentByteString = "";
			BufferedBitReader reader = new BufferedBitReader(fileName);
			while (reader.hasNext()) {
				if (reader.readBit()) {
					currentByteString = currentByteString + "1";
				} else {
					currentByteString = currentByteString + "0";
				}

				if (currentByteString.length() == 8) {
					long currentByteLong = Long.parseLong(currentByteString, 2);
					fileInChar = fileInChar + String.valueOf((char)currentByteLong);
					currentByteString = "";
				}
			}
			for (int i = 0; i < fileInChar.length(); i++) {
				if(!this.freqMap.containsKey(String.valueOf(fileInChar.charAt(i)))) {
					this.freqMap.put(String.valueOf(fileInChar.charAt(i)), 1);
					continue;
				}
				this.freqMap.put(String.valueOf(fileInChar.charAt(i)), this.freqMap.get(String.valueOf(fileInChar.charAt(i))) + 1);
			}
			reader.close();	
		} catch(IOException e) {
			return;
		}
	}
}
