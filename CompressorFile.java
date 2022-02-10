import java.util.*;
import java.io.*;

public class CompressorFile.java {
	private String fileName;
	private CodeMapBuilder codeMap;

	public CompressorFile(String fileName) {
		this.fileName = fileName;
	}
	
	public compress() {
		try {
			this.codeMap = new CodeMapBuilder(this.fileName);
			BufferedReader input = new BufferedReader(new FileReader(this.fileName));
			BufferedWriter output = new BufferedWriter(new FileWriter("compressed_" + this.fileName));
			int data;
			while((data = input.read()) != -1) {
				char charData = (char)data;
				output.write(this.codeMap.get(String.valueOf(charData)));
			}
			input.close();
			output.close();
		} catch(IOException e) {
			return;
		}

	}

	public deCompress() {
		try {
			BufferedReader input = new BufferedReader(new FileReader("compressed_" + this.fileName));
			File oldFile  = new File(this.fileName);
			oldFile.delete();
			BufferedWriter output = new BufferedWriter(new FileWriter(this.fileName));
			String currentKey;
			int newDigit;
			while((newDigit = input.read()) != -1) {
				currentKey = currentKey + String.valueOf((char)newDigit));
				if (codeMap.inverseGetCode(currentKey) != "") {
					output.write(codeMap.inverseGetCode(currentKey));
					currentKey = "";
				}
			}
			input.close();
			output.close() 
	} catch(IOException e) {
			return;
		}
	}
}
