public class FormatStringArray {

    public static void printWithFormat(String[][] inputs, int width) {
        if (inputs == null || inputs.length == 0 || width <= 1) {
            throw new IllegalArgumentException("wrong inputs");
        }
        //construct first line and bottom line
        StringBuilder frame = new StringBuilder();
        for (int i = 0; i < width; i++) {
            frame.append('*');
        }
        System.out.println(frame.toString());

        //format content lines
        StringBuilder currentLine = new StringBuilder();
        int lineCounts = 1;

        for (int row = 0; row < inputs.length; row++) {
            for (int col = 0; col < inputs[row].length; col++) {
                String currentWord = inputs[row][col];
                //add first word in a new line
                if (currentLine.length() == 0) {
                    currentLine.append(currentWord);
                    continue;
                }

                //if current word can be put into current line
                if (currentLine.length() + currentWord.length() + 3 == width) {
                    //append current word
                    currentLine.insert(0, '*');
                    currentLine.append(' ');
                    currentLine.append(currentWord);
                    currentLine.append('*');
                    System.out.println(currentLine.toString());
                    //start a new line
                    currentLine.setLength(0);
                    lineCounts ++;
                } else if (currentLine.length() + currentWord.length() + 3 > width) {
                    //padding existing line and print out
                    if (lineCounts % 2 == 1) {
                        System.out.println(paddingWithSpace(currentLine, width, false));
                    } else {
                        System.out.println(paddingWithSpace(currentLine, width, true));
                    }
                    //start a new line
                    currentLine.setLength(0);
                    currentLine.append(currentWord);
                    lineCounts ++;

                } else {
                    //construct line content
                    currentLine.append(' ');
                    currentLine.append(currentWord);
                    if (row == inputs.length - 1 && col == inputs[inputs.length - 1].length - 1) {
                        //when we reach the end of the 2d string array, we simply print out the last line
                        if (lineCounts % 2 == 1) {
                            System.out.println(paddingWithSpace(currentLine, width, false));
                        } else {
                            System.out.println(paddingWithSpace(currentLine, width, true));
                        }

                    }
                }
            }
        }
        System.out.println(frame.toString());

    }

    private static String paddingWithSpace(StringBuilder content, int width, boolean spaceAtLeft) {
        StringBuilder sb = new StringBuilder();
        int counts = width - content.length() - 2;
        sb.append('*');
        if (spaceAtLeft) {
            for (int i = 0; i < counts; i++) {
                sb.append(' ');
            }
            sb.append(content);
        } else {
            sb.append(content);
            for (int i = 0; i < counts; i++) {
                sb.append(' ');
            }
        }
        sb.append('*');
        return  sb.toString();
    }


    public static void main(String[] args) {
        String[][] ex1 = {
                {"hello", "world"},
                {"I", "love", "cats", "and", "dogs"}
        };
        printWithFormat(ex1, 12);
    }
}
