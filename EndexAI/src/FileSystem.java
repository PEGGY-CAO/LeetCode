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
interface FS {
    void pwd();
    void ls();
    void cd(String path);
    void mkdir(String path);
    void touch(String path);
    void cat(String path);
    void echo(String text, String path, boolean append);
}

abstract class Node {
    String name;
    Node parent;

    public Node(String name) {
        this.name = name;
    }
    abstract boolean isFile();
}

class File extends Node {
    StringBuilder content;
    public File(String name) {
        super(name);
        content = new StringBuilder();
    }
    @Override
    public boolean isFile() {
        return true;
    }
}

class Directory extends Node {
    Map<String, Node> subDirs;
    public Directory(String name) {
        super(name);
        subDirs = new TreeMap<>();
    }

    @Override
    public boolean isFile() {
        return false;
    }
}
class MyFileSystem implements FS {
    Directory root;
    Directory current;

    public MyFileSystem() {
        root = new Directory("/");
        root.parent = root;
        current = root;
    }

    @Override
    public void pwd() {
        StringBuilder sb = new StringBuilder();
        Node curP = current;
        while(!curP.equals(root)) {
            sb.insert(0, '/');

            sb.insert(1, curP.name);
            curP = curP.parent;

        }
        if (sb.length() == 0) {
            sb.append('/');
        }
        System.out.println(sb);
    }

    @Override
    public void ls() {
        StringBuilder dirs = new StringBuilder();
        StringBuilder files = new StringBuilder();
        for (Node n : current.subDirs.values()) {
            if (n.isFile()) {
                files.append(n.name);
                files.append(" ");
            } else {
                dirs.append(n.name);
                dirs.append("/ ");
            }
        }
        System.out.println(dirs.append(files));
    }

    @Override
    public void cd(String path) {
        String[] pathSeg = path.split("/");
        if (pathSeg.length == 0) {
            current = root;
            return;
        }
        int i = 0;
        Node currP;
        if (path.charAt(0) == '/') {
            i = 1;
            currP = root;
        } else {
            currP = current;
        }

        for (; i < pathSeg.length; i++) {
            String currentDir = pathSeg[i];
            if (currentDir.equals("..")) {
                currP = currP.parent;
            } else if (currentDir.equals(".")) {
                continue;
            } else {
                Directory cuP = (Directory) currP;
                if (cuP.subDirs != null && cuP.subDirs.containsKey(currentDir)) {
                    currP = cuP.subDirs.get(currentDir);
                } else {
                    System.out.println("Error: Path doesn't exist");
                    return;
                }
            }
        }
        current = (Directory)currP;
    }

    @Override
    public void mkdir(String path) {
        String[] pathSeg = path.split("/");
        if (pathSeg.length == 0) {
            current = root;
            return;
        }
        int i = 0;
        Node currP;
        if (path.charAt(0) == '/') {
            i = 1;
            currP = root;
        } else {
            currP = current;
        }
        if (pathSeg.length > 1) {
            for (; i < pathSeg.length - 1; i++) {
                String currentDir = pathSeg[i];
                Directory cuP = (Directory) currP;
                if (cuP.subDirs != null && cuP.subDirs.containsKey(currentDir)) {
                    currP = cuP.subDirs.get(currentDir);
                } else {
                    System.out.println("Error: Path not found");
                    return;
                }
            }
        }
        String name = pathSeg[pathSeg.length - 1];
        Node target = new Directory(name);
        target.parent = currP;
        Directory cuP = (Directory) currP;
        cuP.subDirs.put(name, target);
    }

    @Override
    public void touch(String path) {
        String[] pathSeg = path.split("/");
        if (pathSeg.length == 0) {
            current = root;
            return;
        }
        int i = 0;
        Node currP;
        if (path.charAt(0) == '/') {
            i = 1;
            currP = root;
        } else {
            currP = current;
        }

        if (pathSeg.length > 1) {
            for (; i < pathSeg.length - 1; i++) {
                String currentDir = pathSeg[i];
                Directory cuP = (Directory) currP;
                if (cuP.subDirs != null && cuP.subDirs.containsKey(currentDir)) {
                    currP = cuP.subDirs.get(currentDir);
                } else {
                    System.out.println("Error: Parent directory not found");
                    return;
                }
            }
        }
        String fileName = pathSeg[pathSeg.length - 1];
        Node target = new File(fileName);
        target.parent = currP;
        Directory cuP = (Directory) currP;
        cuP.subDirs.put(fileName, target);
    }

    @Override
    public void cat(String path) {
        String[] pathSeg = path.split("/");
        if (pathSeg.length == 0) {
            current = root;
            return;
        }
        int i = 0;
        Node currP;
        if (path.charAt(0) == '/') {
            i = 1;
            currP = root;
        } else {
            currP = current;
        }
        if (pathSeg.length > 1) {
            for (; i < pathSeg.length - 1; i++) {
                String currentDir = pathSeg[i];
                Directory cuP = (Directory) currP;
                if (cuP.subDirs != null && cuP.subDirs.containsKey(currentDir)) {
                    currP = cuP.subDirs.get(currentDir);
                } else {
                    System.out.println("Error: Path doesn't exist");
                    return;
                }
            }
        }
        String fileName = pathSeg[pathSeg.length - 1];
        Directory cuP = (Directory) currP;
        if (cuP.subDirs.size() == 0 || !cuP.subDirs.containsKey(fileName)) {
            System.out.println("Error: File doesn't exist");
            return;
        }
        File f = (File)cuP.subDirs.get(fileName);
        System.out.println(f.content.toString());
    }

    @Override
    public void echo(String text, String path, boolean append) {
        String[] pathSeg = path.split("/");
        if (pathSeg.length == 0) {
            current = root;
            return;
        }
        int i = 0;
        Node currP;
        if (path.charAt(0) == '/') {
            i = 1;
            currP = root;
        } else {
            currP = current;
        }
        if (pathSeg.length > 1) {
            for (; i < pathSeg.length - 1; i++) {
                String currentDir = pathSeg[i];
                Directory cuP = (Directory) currP;
                if (cuP.subDirs != null && cuP.subDirs.containsKey(currentDir)) {
                    currP = cuP.subDirs.get(currentDir);
                } else {
                    System.out.println("Error: Path doesn't exist");
                    return;
                }
            }
        }
        String fileName = pathSeg[pathSeg.length - 1];
        Directory cuP = (Directory) currP;
        if (cuP.subDirs.size() == 0 || !cuP.subDirs.containsKey(fileName)) {
            System.out.println("Error: File doesn't exist");
            return;
        }
        File f = (File)cuP.subDirs.get(fileName);
        if (append) {
            if (f.content.length() != 0) {
                f.content.append("\n");
            }
            f.content.append(text);
        } else {
            f.content = new StringBuilder(text);
        }
    }

}
public class FileSystem {

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
        MyFileSystem fs = new MyFileSystem();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) dispatch(fs, line);
        }
    }
}
