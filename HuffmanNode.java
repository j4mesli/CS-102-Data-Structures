package part1;

public class HuffmanNode implements Comparable {
	// properties
	public String letter;
	public Double frequency;
	public HuffmanNode left, right;
	
	// methods
	public int compareTo(Object o) {
		HuffmanNode huff = (HuffmanNode) o;
		return this.frequency.compareTo(huff.frequency);
	}
	public String toString() {
		return "<\"" + this.letter + "\", \"" + this.frequency + "\">";
	}
	
	// constructors
	public HuffmanNode(String letter, Double frequency) {
		this.letter = letter;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}
	public HuffmanNode(HuffmanNode left, HuffmanNode right) {
		this.letter = left.letter + right.letter;
		this.frequency = left.frequency + right.frequency;
		this.left = left;
		this.right = right;
	}
}
