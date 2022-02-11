public class Main {
	public static void main(String[] args) {
		String fileName = "WarAndPeace.txt";
		CompressorFile compressor = new CompressorFile(fileName);
		compressor.compress();
		compressor.deCompress();				
	}
}
