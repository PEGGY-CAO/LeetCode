
import java.util.ArrayList;


/**
 * during my interview with Peloton, I was asked to solve a problem such that
 * given a String array {"abc", "cba", "dax", "rty", "abd"} and a String prefix (eg."ab")
 * return whether or not string x is a prefix of one element in the string array
 *
 * I solved this problem by constructing a Map<Character, ArrayList</String>>
 * key is the beginning character for element in the string array,
 * value is a list of strings starts with the key character
 *  Time complexity O(n*x)
 *      n is the length of the string array,
 *      x is the length of the string prefix
 * That solution was not optimal.
 *
 *
 *
 *
 * In this java file, I'll solve with a prefix tree data structure.
 *
 */


public class Trie {

    // design a TrieNode class used to implement each single node of it
    static class TrieNode {

        int number;
        ArrayList<TrieNode> children;

        public TrieNode(int number) {
            this.number = number;
            children = null;
        }

        public TrieNode(int number, ArrayList<TrieNode> children) {
            this.number = number;
            this.children = children

        }

    }



    public boolean solution(String[] input, String prefix) {
        //check corner case
        if (prefix == null || prefix.length() == 0) {
            return false;
        }

        //first, I'll construct the prefix tree with the given string array



    }



    public static void main(String[] args) {

        String[] input1 = {"abc", "cba", "dax", "rty", "abd"};
        String prefix = "ab";
        Trie sol = new Trie();
        System.out.println(sol.solution(input1, prefix));


    }




}
