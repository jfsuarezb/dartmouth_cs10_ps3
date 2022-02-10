import java.util.*;

public class CodeMapBuilder {
	private FreqMap freqMap;
	private BinaryTree<KeyValueHolder> codeTree;
	private HashMap<String, String> codeMap;	

	public CodeMapBuilder(String fileName) {
		this.freqMap = new FreqMap();
		this.freqMap.decodeFile(fileName);
		this.buildCodeTree();
	}

	private void buildCodeTree() {
		ArrayList<KeyValueHolder> keyArrays = this.freqMap.getEntries();
		PriorityQueue<BinaryTree<KeyValueHolder>> pqueue = new PriorityQueue<BinaryTree<KeyValueHolder>>(keyArrays.size(), new BinaryTreeKeyValueHolderComparator());

		for (int i = 0; i < keyArrays.size(); i++) {
			pqueue.add(new BinaryTree<KeyValueHolder>(keyArrays.get(i)));
		}	
		
		while(pqueue.size() > 1) {
			BinaryTree<KeyValueHolder> t1 = pqueue.poll();
			BinaryTree<KeyValueHolder> t2 = pqueue.poll();
			BinaryTree<KeyValueHolder> newRoot = new BinaryTree<KeyValueHolder>(new KeyValueHolder("null", t1.getValue() + t2.getValue()), t1, t2);
			pqueue.add(newRoot);
		}			

		this.codeTree = pqueue.poll();
		
		HashMap<String, String> newCodeMap = new HashMap<String, String>();
		
		addHash(newCodeMap, this.codeTree, "");

		this.codeMap = newCodeMap;
					
	}		

	private void addHash(HashMap<String, String> codeMapParameter, BinaryTree<KeyValueHolder> tree, String path) {
		if (tree.isLeaf()) {
			codeMapParameter.add(tree.getData().getKey(), path);
			return;
		}

		if (tree.hasLeft()) addHash(codeMapParameter, tree.getLeft(), path + "0");
		if (tree.hasRight()) addHash(codeMapParameter, tree.getRight(), path + "1");
	}

	public String getCode(String character) {
		return this.codeMap.get(character);
	}
}
