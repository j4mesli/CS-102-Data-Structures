package pack;
import java.io.*;
import java.util.*;

public class HuffmanConverter {
    // properties
    public static final int NUMBER_OF_CHARACTERS = 256;
    private String contents;
    private HuffmanTree huffmanTree;
    private int count[];
    private String code[];
    private int uniqueChars = 0;

    // methods
    //// public methods
    public static String readContents(String name) throws IOException {
        File fs = new File(name);
        Scanner input = new Scanner(fs);
        String output = input.useDelimiter("\\Z").next();
        return output;
    }
    public void recordFrequencies() {
        for (int i = 0; i < contents.length(); i++) { this.count[(int) contents.charAt(i)] += 1; }
        count[(int) '\n'] += 1;
        frequenciesToTree();
    }
    public void frequenciesToTree() {
        ArrayList<HuffmanNode> list = new ArrayList<>();
        for (int i = 0; i < count.length; i++) { if (count[i] != 0) { list.add(new HuffmanNode(Character.toString((char) i), (double) count[i])); } }
        HuffmanNode[] array = new HuffmanNode[list.size()];
        for (int i = 0; i < list.size(); i++) { array[i] = list.get(i); }
        BinaryHeap<HuffmanNode> heap = new BinaryHeap<>(array);
        heap.printHeap();
        huffmanTree = HuffmanTree.createFromHeap(heap);
        treeToCode();
    }
    public void treeToCode() { treeToCode(huffmanTree.root, ""); }
    public String encodeMessage() {
        String output = "";
        for (int i = 0; i < contents.length(); i++) { output += code[(int) contents.charAt(i)]; }
        huffmanTree.printLegend();
        System.out.println("\n\nHuffman Encoding:\n" + output);
        return output;
    }
    public String decodeMessage(String str) {
        String output = "";
        int temp = 0;
        for (int i = 1; i < str.length(); i++) {
            for (int j = 0; j < code.length; j++) {
                if (i == str.length()-1) { if (str.substring(temp).equals(code[j])) { output += Character.toString((char) j) + "\n"; break; } }
                else { if (str.substring(temp, i).equals(code[j])) { output += (char) j; temp = i; break; } }
            }
        }
        return output;
    }

    //// private methods
    private void treeToCode(HuffmanNode t, String s) {
        if (t.letter.length() > 1) { treeToCode(t.left, s + "1"); treeToCode(t.right, s + "0"); }
        else if (t.letter.length() == 1) { code[(int)t.letter.charAt(0)] = s; }
    }

    // constructor
    public HuffmanConverter (String str) {
        this.contents = str;
        this.count = new int[NUMBER_OF_CHARACTERS];
        this.code = new String[NUMBER_OF_CHARACTERS];
    }

    // main
    public static void main(String args[]) throws IOException {
        HuffmanConverter obj = new HuffmanConverter(readContents(args[0]));
        obj.recordFrequencies();
        String compress = obj.encodeMessage();
        System.out.println("\nMessage size in ASCII encoding:" + obj.contents.length() * 8 + 8);
        System.out.println("Message size in Huffman encoding:" + compress.length() + obj.code[(int)'\n'].length() + "\n\n");
        String extract = obj.decodeMessage(compress);
        System.out.println(extract);
    }

}