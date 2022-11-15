import java.util.*;

public class Solution {
    public List<String> removeComments(String[] source) {

        List<String> result = new ArrayList<>();
        if (source == null || source.length == 0) {
            return result;
        }

        StringBuilder parseLine = new StringBuilder();
        boolean inBlock = false;

        for (String currentLine : source) {

            for (int i = 0; i < currentLine.length(); i++) {
                char cur = currentLine.charAt(i);

                if (!inBlock) {
                    //check '//'
                    if (cur == '/' && i + 1 < currentLine.length() && currentLine.charAt(i + 1) == '/') {
                        break;
                    }
                    //check '/*''
                    if (cur == '/' && i + 1 < currentLine.length() && currentLine.charAt(i + 1) == '*') {
                        inBlock = true;
                        i++;
                    } else {
                        parseLine.append(cur);
                    }

                } else {
                    // inBlock, we'll check and find '*/'
                    if (cur == '*' && i + 1 < currentLine.length() && currentLine.charAt(i + 1) == '/') {
                        i++;
                        inBlock = false;
                    }
                }


            }

            if (!inBlock && parseLine.length() > 0) {
                String line = parseLine.toString();
                result.add(line);
                parseLine.setLength(0);
            }

        }
        return result;
    }

    public static void main(String[] args) {
        Solution s1 = new Solution();
        String[] source = {"/*Test program */",
                            "int main()",
                            "{ ",
                            "  // variable declaration ",
                            "int a, b, c;",
                            "/* This is a test",
                            "   multiline  ",
                            "   comment for ",
                            "   testing */",
                            "a = b + c;",
                            "}"};
        List<String> listResult = s1.removeComments(source);
        for (String line : listResult) {
            System.out.println(line);
        }

    }
}
