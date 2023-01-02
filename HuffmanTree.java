package part1;

import java.util.*;

public class HuffmanTree {
	// properties
	HuffmanNode root;
	
	// methods
	public void printLegend() {
		printLegend(root, "");
	}
	private void printLegend(HuffmanNode t, String s) {
		if (t.letter.length() > 1) {
			printLegend(t.left, s + "0");
			printLegend(t.right, s + "1");
		}
		if (t.letter.length() == 1) {
			System.out.println(t.letter+" = "+s);
		}
	}
	public static BinaryHeap legendToHeap(String legend) {
		BinaryHeap heap = new BinaryHeap();
		String[] items = legend.split(" ");
		for (int i = 0; i < items.length; i += 2) {
			String word = items[i];
			Double num = Double.parseDouble(items[i + 1]);
			heap.insert(new HuffmanNode(word, num));
		}
		return heap;
	}
	public static HuffmanTree createFromHeap(BinaryHeap b) {
		while (b.getSize() != 1) {
			HuffmanNode node1 = (HuffmanNode) b.deleteMin();
			HuffmanNode node2 = (HuffmanNode) b.deleteMin();

			HuffmanNode temp = new HuffmanNode(node1, node2);
			temp.left = node1;
			temp.right = node2;
			b.insert(temp);
		}
		HuffmanTree output = new HuffmanTree((HuffmanNode) b.findMin());
		return output;
	}
	
	// constructor
	public HuffmanTree(HuffmanNode huff) {
		this.root = huff;
	}
	
	// main method
	public static void main(String[] args) {
		String value = "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
		BinaryHeap bheap = legendToHeap(value);
		bheap.printHeap();
		HuffmanTree tree = createFromHeap(bheap);
		tree.printLegend();
	}
}
