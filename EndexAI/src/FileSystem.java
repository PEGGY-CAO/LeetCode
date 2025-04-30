import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *Implement a program that simulates basic file system navigation, like a simplified version of terminal commands.
 * Your code should let users navigate through directories, create files/folders, and see their contents -
 * similar to how you'd interact with a real terminal.
 * Your code should not rely on file system builtins. All "folders" and
 * "files" should be within the memory of the program itself.
 * Boilerplate has been provided to read in the inputs from the terminal.
 * Do not modify this code. Be sure to match error messages exactly.
 * Feel free to use whichever coding language you desire.
 * Goals
 * • See how you design clean interfaces for code
 * • See your familiarity with basic data structures and algorithms
 * • See how you think about edge cases and error handling
 * This coding challenge is split into different functions to implement, but you may choose to implement
 * in whatever order you desire.
 *
 * pwd
 * • Shows the full path from root to current location
 * • Always start at root (/)
 * Eg.
 * > pwd
 * /home/documents
 *
 * ls
 * • Show all items in the current directory
 * • Folders end with a /
 * • Folders go before files
 * Eg.
 * > cd /
 * > ls
 * home/ downloads/ documents/ filel.txt file2.txt
 *
 * cd
 * • Move between directories using a path as input
 * • If the path does not exist, give an error.
 * • Move to absolute path: cd /home/docs
 * • Move to child directory/relative path: cd photos
 * • Move up one level: cd ..
 * • Stay in same place: cd.
 * • Return to root: cd /
 * • Take complex input: cd./home/docs/../docs/. projects
 * Eg.
 * > cd /home/documents
 * > pwd
 * /home/documents
 *
 * > cd ..
 * > pwd
 * /home
 *
 * > cd /
 * > pwd
 * /
 *
 * mkdir
 * • Creates a new directory given a path
 * • Should error if the directory already exists
 * • Creates at most one directory (do not make intermediate paths that don't already exist)
 * • Support creating at:
 *      Current location: mkdir photos
 *      Absolute path: mkdir /home/documents/photos
 *      Nested path: mkdir documents/photos
 * • In general, supports the same path patterns as cd. You may assume that the final component
 * of the path is a valid name (not. or ..).
 * Eg.
 * > mkdir photos
 * > mkdir /home
 * > mkdir /home/docs
 * > mkdir nonexistent/photos
 * Error: Path not found
 *
 * touch
 * • Create new empty files
 * • Should not error if creating an already existing file (overwrite)
 * • Similar path support as mkdir:
 *      Current location: touch notes.txt
 *      Absolute path: touch /home/report.txt
 *      Nested path: touch documents/report.txt
 * • In general, supports the same path patterns as cd. You may assume that the final component
 * in the path is a valid file name.
 * Eg.
 * > touch notes.txt
 * > mkdir /home
 * > touch /home/ report. txt
 * > touch missing/file.txt
 * Error: Parent directory not found
 *
 * cat
 * • Read and print a file's contents
 * • In general, supports the same path patterns as cd
 * Eg.
 * > cat notes.txt
 * Hello World
 * > cat /home/documents/ report.txt
 * New content
 * > cat /no_file.txt
 * Error: File does not exist.
 *
 * echo
 * • Support > and >> operators
 * • Handle error cases
 * • In general, supports the same path patterns as cd
 * • Will create the file if the prefix exists but the file does not exist.
 * Eg.
 * > echo "First line" > notes.txt
 * First line
 * > cat notes.txt
 * First line
 * > echo "Second line" >> notes. txt
 * Second line
 * > cat notes.txt
 * First line
 * Second line
 * > echo "New content" > notes.txt #overwrites everything
 * New content
 * > cat notes.txt
 * New content
 * > echo "More content" >> /home/documents/report.txt # absolute path works too
 * More content
 */
public class FileSystem {

    interface FS {
        void pwd();
        void ls();
        void cd(String path);
        void mkdir(String path);
        void touch(String path);
        void cat(String path);
        void echo(String text, String path, boolean append);
    }

    class Node {
        String directory;
        Node parent;
        List<String> files;
        List<Node> subDirs;
        public Node(String dir, Node parent) {
            this.directory = dir;
            this.parent = parent;
            files = new ArrayList<>();
            subDirs = new ArrayList<>();
        }
    }

    class MyFileSyetem implements FS {
        Node root;
        Node current;

        public MyFileSyetem() {
            root = new Node("/", null);
        }

        @Override
        public void pwd() {
            StringBuilder sb = new StringBuilder();
            Node curP = current;
            while(!curP.directory.equals(root.directory)) {
                sb.insert(0, '/');

                sb.insert(1, curP.directory);
                curP = curP.parent;

            }
            System.out.println(sb.toString());
        }

        @Override
        public void ls() {
            if (current.files.size() != 0) {
                for (String file : current.files) {
                    System.out.println(file);
                }
            }

            if (current.subDirs.size() != 0) {
                for (Node dir : current.subDirs) {
                    System.out.println(dir.directory);
                }
            }
        }

        @Override
        public void cd(String path) {
            List<Node> subDir = current.subDirs;
            String[] pathSeg = path.split("/");
            Node currP = root;
            boolean found = false;
            for (int i = 0; i < pathSeg.length; i++) {
                String currentDir = pathSeg[i];
                if (currentDir.equals("..")) {
                    currP = currP.parent;
                } else if (currentDir.equals(".")) {
                    continue;
                } else {

                    for (Node dir : subDir) {
                        if (dir.directory.equals(path)) {
                            if (i == pathSeg. length - 1) {
                                found = true;

                            }
                            currP = dir;

                        }
                    }
                }
            }
            System.out.println("Path doesn't exist");
        }

        @Override
        public void mkdir(String path) {
            String[] pathSeg = path.split("/");
            Node curP = root;
            for (int i = 0; i < pathSeg.length; i++) {
                List<Node> subDirs = curP.subDirs;
                boolean found = false;

                for (Node dir : subDirs) {
                    if (dir.directory.equals(pathSeg[i]) && i == pathSeg.length - 1) {
                        System.out.println("Error: Path not found");
                        return;
                    } else {
                        curP = dir;
                    }
                }
                if (!found) {
                    Node newDir = new Node(pathSeg[i], curP);
                    curP.subDirs.add(newDir);
                    curP = newDir;
                }
            }
        }

        @Override
        public void touch(String path) {
            String[] pathSeg = path.split("/");
            Node curP = root;
            for (int i = 0; i < pathSeg.length; i++) {
                List<Node> subDirs = curP.subDirs;
                for (Node dir : subDirs) {
                    if (dir.directory.equals(pathSeg[i]) && i == pathSeg.length - 2) {
                        System.out.println("Error: Path not found");
                        return;
                    } else {
                        curP = dir;
                    }
                    if (i == pathSeg.length - 1) {
                        String newFile = pathSeg[i];
                        curP.files.add(newFile);
                    }
                }

            }
        }

        @Override
        public void cat(String path) {

        }

        @Override
        void echo(String text, String path, boolean append) {

        }

    }


    private static void driverEcho(FS fs, String line) {
        int q1 = line.indexOf('"');
        int q2 = line.indexOf('"', q1 + 1);
        if (q1 == -1 || q2 == -1) {
            System.out.println("Error: Malformed echo");
            return;
        }
        String txt = line.substring(q1 + 1, q2);
        String rest = line.substring(q2 + 1).trim();

        boolean append;
        if (rest.startsWith(">>")) {
            append = true;
            rest = rest.substring(2).trim();
        } else if (rest.startsWith(">")) {
            append = false;
            rest = rest.substring(1).trim();
        } else {
            System.out.println("Error: Malformed echo");
            return;
        }

        fs.echo(txt, rest, append);
    }

    private static void dispatch(FS fs, String line) {
        line = line.replaceAll("\\n$", "");
        if (line.equals("pwd"))  { fs.pwd(); return; }
        if (line.equals("ls"))   { fs.ls();  return; }

        if (line.startsWith("cd "))    { fs.cd   (line.substring(3)); return; }
        if (line.startsWith("mkdir ")) { fs.mkdir(line.substring(6)); return; }
        if (line.startsWith("touch ")) { fs.touch(line.substring(6)); return; }
        if (line.startsWith("cat "))   { fs.cat  (line.substring(4)); return; }
        if (line.startsWith("echo "))  { driverEcho(fs, line.substring(5)); return; }
    }

    public static void main(String[] args) throws Exception {
        FS fs = new FS() {
            public void pwd()                                    {}
            public void ls()                                     {}
            public void cd   (String path)                       {}
            public void mkdir(String path)                       {}
            public void touch(String path)                       {}
            public void cat  (String path)                       {}
            public void echo (String text, String path, boolean append) {}
        };

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) dispatch(fs, line);
        }
    }
}
