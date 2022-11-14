import java.util.*;

public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0
                || !wordList.contains(endWord)) {
            return res;
        }

        if (beginWord.equals(endWord)) {
            List<String> single = new ArrayList<>();
            single.add(endWord);
            res.add(single);
            return res;
        }

        if (beginWord.length() == 1) {
            List<String> single = new ArrayList<>();
            single.add(beginWord);
            single.add(endWord);
            res.add(single);
            return res;
        }

        //data structures we need
        Map<String, Integer> visited = new HashMap<>(); //store the least distance from beginWord to this word
        Queue<String> q = new LinkedList<>(); //used for BFS
        Map<String, List<String>> adjList = new HashMap<>();
        Set<String> wordSet = new HashSet<>(wordList);

        int endWordLevel = 0;
        boolean found = false;

        //STEP1: BFS
        q.offer(beginWord);
        visited.put(beginWord, 0);
        adjList.put(beginWord, new ArrayList<>());
        if (wordSet.contains(beginWord)) {
            wordSet.remove(beginWord);
        }

        while (!q.isEmpty()) {
            int levelSize = q.size();

            while (levelSize > 0) {
                String current = q.poll();
                char[] currentStr = current.toCharArray();
                int potentialCurLevel = visited.get(current) + 1;

                if (found && potentialCurLevel > endWordLevel) {
                    q.clear();
                    break;
                }

                for (int i = 0; i < current.length(); i++) {
                    char cur = currentStr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        currentStr[i] = c;
                        String potential = String.valueOf(currentStr);
                        if (!wordSet.contains(potential)) {
                            continue;
                        }

                        //prune the tree to save BFS's space and DFS's time
                        if (potential.equals(endWord) && !found) {
                            //we find  the endWord's level!!!
                            found = true;
                            endWordLevel = potentialCurLevel;
                        }

                        if (!visited.keySet().contains(potential)) {
                            List<String> children = adjList.getOrDefault(current, new ArrayList<>());
                            children.add(potential);
                            adjList.put(current, children);
                            visited.put(potential, potentialCurLevel);
                            q.offer(potential);
                        } else {
                            //if we've visited the potential string already
                            //we'll need to see if it's level is the same w current next level
                            int potentialExistLevel = visited.get(potential);
                            if (potentialExistLevel < potentialCurLevel) {
                                continue;
                            } else if (potentialExistLevel == potentialCurLevel) {
                                //one node shows twice in the same level
                                List<String> children = adjList.getOrDefault(current, new ArrayList<>());
                                children.add(potential);
                                adjList.put(current, children);
                            }
                        }
                    }
                    currentStr[i] = cur;
                }
                levelSize--;
            }
        }
        if (!found) {
            return res;
        }

        //STEP 2: DFS to build up result list
        List<String> path = new ArrayList<>();
        dfs(res, adjList, beginWord, endWord, path);

        return res;
    }

    private void dfs(List<List<String>> res, Map<String, List<String>> adjList, String current, String endWord, List<String> currentPath) {
        currentPath.add(current);
        //end case
        if (current.equals(endWord)) {

            res.add(new ArrayList<String>(currentPath));
            currentPath.remove(currentPath.size() - 1);
            return;
        }

        List<String> children = adjList.get(current);
        if (children != null) {
            // System.out.println(children);
            for (String child : children) {
                // System.out.println(child);
                dfs(res, adjList, child, endWord, currentPath);

            }
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {

        Solution s1 = new Solution();
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList1 = Arrays.asList(wordList);
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<List<String>> test1 = s1.findLadders(beginWord1, endWord1, wordList1);
        for (List<String> possibleSolution : test1) {
            System.out.println(possibleSolution);
        }

    }
}