
public int packingGroup(List<Integer> arr) {
    Collections.sort(arr);
    int result = 1;
    for (int i = 1; i < arr.size(); i++) {
        if (arr.get(i) - result >= 1)  {
            result++;
        }

    }
    return result;

}