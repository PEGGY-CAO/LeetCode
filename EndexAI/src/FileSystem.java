import java.io.BufferedReader;
import java.io.InputStreamReader;

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

        }
    }

    class MyFileSyetem implements FS {
        Node root;
        Node current;

        public MyFileSyetem() {
            root = new Node("/");
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
            if (current.files.length != 0) {
                for (String file : current.files) {
                    System.out.println(file);
                }
            }

            if (current.subDirs.length != 0) {
                for (Node dir : current.subDirs) {
                    System.out.println(dir.directory);
                }
            }
        }

        @Override
        public void cd(String path) {
            Node[] subDir = current.subDirs;
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
            // "/home/downlods"
            // "child1/child2/.././"
            // boolean found = false;
            // for (Node dir : subDir) {
            //     if (dir.directory.equals(path)) {
            //         found = true;
            //         current = dir;
            //         return;
            //     }
            // }
            System.out.println("Path doesn't exist");
        }

        @Override
        public void mkdir(String path) {
            String[] pathSeg = path.split("/");
            Node curP = root;
            for (int i = 0; i < pathSeg.length; i++) {
                Node[] subDirs = curP.subDirs;
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
