import java.util.*;

// This class serves as a comparator for the nodes in the tree
// It compares based on the frequency of the character

public class BinaryTreeKeyValueHolderComparator implements Comparator<BinaryTree<KeyValueHolder>> {
	public int compare(BinaryTree<KeyValueHolder> bt1, BinaryTree<KeyValueHolder> bt2) {
		if (bt1.getData().getValue() < bt2.getData().getValue()) return -1;
		else if (bt1.getData().getValue() > bt2.getData().getValue()) return 1;
		return 0;
	}
}
