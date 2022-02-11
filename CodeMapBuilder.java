import java.util.*;

/**
* This class implements the core compression algorithm. It generates the optimized codes for every character
* It obtains the frequency map, builds a tree from a priority queue where the path to each node IS the new code for the character in the node
* The final product is a map of a character to an optimized code.
*/

public class CodeMapBuilder {
	private FreqMap freqMap;
	private BinaryTree<KeyValueHolder> codeTree;
	private HashMap<String, String> codeMap;	
	private HashMap<String, String> reverseCodeMap;

	public CodeMapBuilder(String fileName) throws Exception {
		this.freqMap = new FreqMap();
		this.freqMap.decodeFile(fileName);
		this.buildCodeTree();
	}


	// This method builds the map
	// It first generates a tree with a priority queue that is filled with the key value pairs from the frequency map
	// Then, from the paths of the tree from the root to the leave, it traverses the tree and generates an optimized code for each character
	private void buildCodeTree() throws Exception {
		ArrayList<KeyValueHolder> keyArrays = this.freqMap.getEntries();
		if (keyArrays.size() == 1) {
			throw new Exception("Only one character in file. No room for optimization");
		}
		PriorityQueue<BinaryTree<KeyValueHolder>> pqueue = new PriorityQueue<BinaryTree<KeyValueHolder>>(keyArrays.size(), new BinaryTreeKeyValueHolderComparator());

		for (int i = 0; i < keyArrays.size(); i++) {
			pqueue.add(new BinaryTree<KeyValueHolder>(keyArrays.get(i)));
		}	
		
		while(pqueue.size() > 1) {
			BinaryTree<KeyValueHolder> t1 = pqueue.poll();
			BinaryTree<KeyValueHolder> t2 = pqueue.poll();
			BinaryTree<KeyValueHolder> newRoot = new BinaryTree<KeyValueHolder>(new KeyValueHolder("null", t1.getData().getValue() + t2.getData().getValue()), t1, t2);
			pqueue.add(newRoot);
		}			

		this.codeTree = pqueue.poll();

		HashMap<String, String> newCodeMap = new HashMap<String, String>();
		
		addHash(newCodeMap, this.codeTree, "");

		this.codeMap = newCodeMap;

		this.reverseCodeMap = new HashMap<String, String>();
		
		for (Map.Entry<String, String> entry: this.codeMap.entrySet()) {
			this.reverseCodeMap.put(entry.getValue(), entry.getKey());
		}
	}		

	private void addHash(HashMap<String, String> codeMapParameter, BinaryTree<KeyValueHolder> tree, String path) {
		if (tree.isLeaf()) {
			codeMapParameter.put(tree.getData().getKey(), path);
			return;
		}

		if (tree.hasLeft()) addHash(codeMapParameter, tree.getLeft(), path + "0");
		if (tree.hasRight()) addHash(codeMapParameter, tree.getRight(), path + "1");
	}

	public String getCode(String theChar) {
		return this.codeMap.get(theChar);
	}
	
	public String inverseGetCode(String theCode) {
		if (this.reverseCodeMap.containsKey(theCode)) return this.reverseCodeMap.get(theCode);
		return "";
	}
}
