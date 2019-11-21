import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Solution {
    // If you need extra classes, you can define them privately here within class Solution
  /*
  class Node {
    List<Node> parents;
    String val;
    List<Node> children;
    
    public Node(String value) {
      //parent = new ArrayList<>();
      val = value;
      children = new ArrayList<>();
    }
    
  }
  */

    // Prints to standard output.
    static void findStartAndEndLocations(String[][] pairs) {
        int length = pairs.length;
    /*
    SortedSet<Node> roots = new TreeSet<>();
    SortedSet<Node> leaves = new TreeSet<>();
    //build tree
    for (int i = 0; i < length; i++) {
      
      
      String s1 = pairs[i][0];
      String s2 = pairs[i][1];
      
      Node parent = new Node(s1);
      Node child = new Node(s2);
      parent.children.add(child);
      //child.parent.add(parent);
      
      if (!roots.contains(parent) && !leaves.contains(parent)) {
        roots.add(parent);
      } 
      leaves.add(child);
        
    
      if (roots.contains(child)) {
        roots.remove(child);
      }
            
    }
    
    Iterator<Node> it = roots.iterator();
    while (it.hasNext()) {
      System.out.print
    }
    */

        // Your code here.


        ConcurrentMap<String, SortedSet<String>> map = new ConcurrentHashMap<>();
        SortedSet<String> roots = new TreeSet<>();
        SortedSet<String> leaves = new TreeSet<>();
        SortedSet<String> midleaves = new TreeSet<>();

        for (int i = 0; i < length; i++) {
            String s1 = pairs[i][0];
            String s2 = pairs[i][1];
            //build map
            if (map.containsKey(s1)) {
                SortedSet<String> chiList = map.get(s1);
                chiList.add(s2);
                map.put(s1, chiList);
            } else {
                SortedSet<String> childList = new TreeSet<>();
                childList.add(s2);
                map.put(s1, childList);
            }

            //build sorted roots inorder to do iteration
            if (!midleaves.contains(s1) && !leaves.contains(s1)) {
                roots.add(s1);
            }
            leaves.add(s2);

            if (roots.contains(s2)) {
                roots.remove(s2);
                midleaves.add(s2);
                leaves.remove(s2);
            }
            if (leaves.contains(s1)) {
                leaves.remove(s1);
                midleaves.add(s1);
            }


        }

        Iterator<String> it = roots.iterator();
        while (it.hasNext()) {
            String p = it.next();
            //Set<String> cList = map.get(p);
            //System.out.println(p + ": " + cList);
            SortedSet<String> psleaves = new TreeSet<>();
            getLeaves(p, map, leaves, psleaves);
            System.out.print(p + ":");
            Iterator<String> iteee = psleaves.iterator();
            while (iteee.hasNext()) {
                String c = iteee.next();
                System.out.print(" " + c);

            }
            System.out.print("\n");
        }



    }

    private static void getLeaves(String parent, ConcurrentMap<String, SortedSet<String>> map, SortedSet<String> allleaves, SortedSet<String> psleaves) {
        if (allleaves.contains(parent)) {
            psleaves.add(parent);
            return;
        }
        SortedSet<String> chiList = map.get(parent);
        Iterator<String> it = chiList.iterator();
        while (it.hasNext()) {
            String p = it.next();
            if (allleaves.contains(p)) {
                psleaves.add(p);
            } else {
                getLeaves(p, map, allleaves, psleaves);
            }

        }
        return;


    }




    // DO NOT MODIFY MAIN()
    public static void main(String[] args) {
        List<String[]> arg1 = new ArrayList<String[]>();

        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }

                arg1.add(line.split(" "));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String[][] pairs = arg1.toArray(new String[arg1.size()][]);

        findStartAndEndLocations(pairs);
    }
}