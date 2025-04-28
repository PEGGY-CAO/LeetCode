package codesignal.integercontainer;

import codesignal.integercontainer.IntegerContainer;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class IntegerContainerImpl implements IntegerContainer {
    Queue<Integer> arr;
    public IntegerContainerImpl() {
        arr = new PriorityQueue<>();

    }

    @Override
    public int add(int value) {
        arr.add(value);
        return arr.size();

    }

    @Override
    public boolean delete(int value) {
        if (arr.contains(value)) {
            arr.remove(value);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Integer> getMedian() {
        if (arr.size() == 0) {
            return Optional.empty();
        } else if (arr.size() % 2 == 0) {
            List<Integer> arrList = new ArrayList<>(arr);
            Collections.sort(arrList);
            int id = arr.size() / 2 - 1;
            return Optional.ofNullable(arrList.get(id));

        } else {
            List<Integer> arrList = new ArrayList<>(arr);
            Collections.sort(arrList);
            return Optional.ofNullable(arrList.get(arr.size() / 2));
        }
    }


}
