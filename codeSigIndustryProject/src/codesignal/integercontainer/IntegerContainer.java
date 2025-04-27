package codesignal.integercontainer;

import java.util.Optional;

/**
 * `IntegerContainer` interface.
 */
public interface IntegerContainer {

    /**
     * Should add the specified integer `value` to the container
     * and return the number of integers in the container after the
     * addition.
     */
    default int add(int value) {
        // default implementation
        return 0;
    }

    /**
     * Should attempt to remove the specified integer `value` from
     * the container.
     * If the `value` is present in the container, remove it and
     * return `true`, otherwise, return `false`.
     */
    default boolean delete(int value) {
        // default implementation
        return false;
    }

    /**
     * Should return the median integer - the integer in the middle
     * of the sequence after all integers stored in the container
     * are sorted in ascending order.
     * If the length of the sequence is even, the leftmost integer
     * from the two middle integers should be returned.
     * If the container is empty, this method should return
     * `Optional.empty()`.
     */
    default Optional<Integer> getMedian() {
        // default implementation
        return Optional.empty();
    }
}
