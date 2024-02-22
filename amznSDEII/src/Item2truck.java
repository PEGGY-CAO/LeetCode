import java.util.*;

public class Item2truck {

    public static int[] findTrucks(int[] trucks, int[] items) {
        int numOfTruck = trucks.length;
        int numOfItem = items.length;
        int[] result = new int[numOfItem];
        int[][] truckToIndex = new int[numOfTruck][2];
        //use int[][] truckToIndex to store each truck's capacity with its original index
        for (int i = 0; i < numOfTruck; i++) {
            int[] capacityToIndex = new int[2];
            capacityToIndex[0] = trucks[i];
            capacityToIndex[1] = i;
            truckToIndex[i] = capacityToIndex;
        }

        //sort the truckToIndex 2D matrix by truck's capacity from small to large, if same capacity, sort by index
        Arrays.sort(truckToIndex, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0])  {
                    return -1;
                } else if (o1[0] > o2[0]) {
                    return 1;
                } else {
                    if (o1[1] < o2[1]) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });
//        for (int i = 0; i < numOfTruck; i++) {
//            System.out.println(truckToIndex[i][0] + " " + truckToIndex[i][1]);
//        }
        //binary search to assign the truck to the item according to its capacity
        for (int i = 0; i < numOfItem; i++) {
            int current = items[i];
            int r = biSearch(current, truckToIndex);
            result[i] = r;
        }
        return result;
    }

    public static int biSearch(int target, int[][] allTrucks) {
        int l = 0;
        int r = allTrucks.length - 1;//3
        int m;
        while (l <= r)  {//0 < 3 -> 2<3 -> 3<=3
            m = l + (r - l) / 2;//1->2 ->3
            //find the right truck
            if (allTrucks[m][0] == target) {
                if (m + 1 < allTrucks.length) {
                    return allTrucks[m + 1][1];
                } else {
                    return -1;
                }
            }
            if (m - 1 >= 0 && allTrucks[m - 1][0] <= target && allTrucks[m][0] > target) {
                return allTrucks[m][1];
            } else if (allTrucks[m][0] < target) {
                l = m + 1;//0->2->3
            } else {
                r = m;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] trucks = {5, 3, 8, 1};
        int[] items = {6, 10};

        int[] result = findTrucks(trucks, items);
//        for (int k : result) {
//            System.out.println(k);
//        }
    }
}
