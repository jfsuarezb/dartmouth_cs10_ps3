public class Main {
	public static void main(String[] args) {
		String fileName = "";
		CompressorFile compressor = new CompressorFile(fileName);
		compressor.compress();
		compressor.deCompress();				
	}
}
