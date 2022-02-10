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
			String line;
			BufferedReader input = new BufferedReader(new FileReader(this.fileName));	
			compressedFile = new File("compressed_" + this.fileName);
			compressedFile.delete()
			compressedFile.createNewFile();
			FileWriter writer = new FileWriter("compressed_" + this.fileName);
			while ((line = input.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					writer.write(this.codeMap.get(line.charAt(i)));
				}
			}
			writer.close() 
			File previousFile = new File(this.fileName);
			previousFile.delete();
		} catch(IOException e) {
			return;
		}

	}

	public deCompress() {
		try {
			
		} catch(IOException e) {
			return;
		}
	}
}
