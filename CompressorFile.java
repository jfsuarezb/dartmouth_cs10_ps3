import java.util.*;
import java.io.*;

/**
* This class uses the map with optimized codes in the CodeMapBuilder class. It compresses and decompresses files
* It compresses files by reading each character and looking up the optimized code and replacing the character with the optimized code
* It decompresses in a symetrical process where it reads each optimized code and reverse searches for the corresponding character
*/
public class CompressorFile {
	private String fileName;
	private CodeMapBuilder codeMap;

	public CompressorFile(String fileName) {
		this.fileName = fileName;
	}


	// This method compresses the file. The way it funcitons is specified in the code above	
	public void compress() {
		try {
			try {
				this.codeMap = new CodeMapBuilder(this.fileName);
			} catch(Exception e) {
				System.out.println("There is only one character in file. No room for optimization");
				return;
			}
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
			reader.close();
			if (fileInChar.length() <= 1) {
				System.out.println("No optimization possible. File is too short");
				return;
			}
			File newFile = new File("compressed_" + fileName);
			newFile.createNewFile();
			BufferedBitWriter writer = new BufferedBitWriter("compressed_" + fileName);
			for (int i = 0; i < fileInChar.length(); i++) {
				String bitString = this.codeMap.getCode(String.valueOf(fileInChar.charAt(i)));
				for (int j = 0; j < bitString.length(); j++) {
					if (bitString.charAt(j) == '1') {
						writer.writeBit(true);
					} else {
						writer.writeBit(false);
					}
				}
			}
			writer.close();
		} catch(IOException e) {
			return;
		}
	}


	// This method decompresses the file. The way it functions is specified in the code above
	public void deCompress() {
		try {
			BufferedBitReader reader = new BufferedBitReader("compressed_" + fileName);
			File newFile = new File("decompressed_" + fileName);
			newFile.createNewFile();
			BufferedBitWriter writer = new BufferedBitWriter("decompressed_" + fileName);
			String newKey = "";
			while (reader.hasNext()) {
				if (reader.readBit()) {
					newKey = newKey + "1";
				} else {
					newKey = newKey + "0"; }
			
				if (codeMap.inverseGetCode(newKey) != "") {
					byte charByte = (byte)codeMap.inverseGetCode(newKey).charAt(0);
					String byteString = String.format("%8s", Integer.toBinaryString(charByte & 0xFF)).replace(' ', '0');
					for (int i = 0; i < byteString.length(); i++) {
						if (byteString.charAt(i) == '1') {
							writer.writeBit(true);
						} else {
							writer.writeBit(false);
						}
					}
					newKey = "";
				}	
			}	
			writer.close();
			reader.close();
		} catch(IOException e) {
			return;
		}
	}
}
