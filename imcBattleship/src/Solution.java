import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Solution {
    /**
     *eg. Given N = 4, S = 1B 2C, 2D 4D, T = 2B 2D 3D 4D 4A
     *your function should return “1, 1”
     *N square map. Shipe is identified. A ship is considered to be hit if at least one of its constituent cells is hit. If all cells is hit, the ship is sunk
     * return how many sunk ships and how many ships hit but not sunk
     * https://github.com/mcustiel/PlayingCodility/blob/master/src/main/java/org/mcustiel/codility/Battleship.java
     */
    public String solution(int N, String S, String T) {
        if (N < 1 || S.length() < 2) { return "0,0";}
        List<Ship> ships = parseShips(S, N * N);
        List<Point> hits = parseHits(T, N * N);

        int hitted = 0;
        int sunken = 0;
        Iterator<Ship> shipIt = ships.iterator();
        while (shipIt.hasNext()) {
            Ship current = shipIt.next();
            int touching = current.getHits(hits);
            if (touching > 0) {
                if (touching == current.getSize()) {
                    sunken++;
                } else {
                    hitted++;
                }
            }
        }
        return "" + sunken + "," + hitted;
    }

    public List<Point>  parseHits(String T, int size) {
        List<Point> hitsList = new ArrayList<>(size);
        String[] coords = T.split(" ");
        for (String coord : coords) {
            if (coord.length() > 0) {
                hitsList.add(new Point(coord));
            }
        }
        return hitsList;
    }

    public List<Ship> parseShips(String S, int size) {
        List<Ship> shipList = new ArrayList<>(size);
        String[] ships = S.split(",");
        for (String ship : ships) {
            if (ship.length() > 0) {
                String[] coords = ship.split(" ");
                if (coords.length == 2) {
                    shipList.add(new Ship(new Point(coords[0]), new Point(coords[1])));
                }
            }
        }
        return shipList;
    }

    public class Point {
        int row;
        int col;
        public Point(String coord) {
            StringBuilder sb = new StringBuilder(coord.toUpperCase());
            int indexOfCol = coord.length() - 1;
            col = sb.charAt(indexOfCol) - 'A';
            row = Integer.parseInt(sb.deleteCharAt(indexOfCol).toString()) - '1';
        }

        public boolean greaterOrEqual(Point hit) {
            return row >= hit.row && col >= hit.col;
        }
    }

    public class Ship {
        Point topLeft;
        Point bottomRight;

        public Ship(Point topLeft, Point bottomRight) {
            this.topLeft = topLeft;
            this.bottomRight = bottomRight;
        }

        public int getSize() {
            return (Math.abs(topLeft.row - bottomRight.row) + 1) * (Math.abs(topLeft.col - bottomRight.col) + 1);
        }

        public int getHits(List<Point> shots) {
            Iterator<Point> shotIterator = shots.iterator();
            int hits = 0;
            while (shotIterator.hasNext()) {
                Point shot = shotIterator.next();
                if (shot.greaterOrEqual(topLeft) && bottomRight.greaterOrEqual(shot)) {
                    hits++;
                }
            }
            return hits;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String S = "1A 2A,12A 12A";
        String T = "12A";
        System.out.println(sol.solution(12, S, T));
    }
}
