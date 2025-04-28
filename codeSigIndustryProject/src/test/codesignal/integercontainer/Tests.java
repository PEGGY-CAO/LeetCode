package test.codesignal.integercontainer;


import java.time.Duration;
import java.util.Optional;

import codesignal.integercontainer.IntegerContainer;
import codesignal.integercontainer.IntegerContainerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * The test class below includes 10 tests for Level 2.
 *
 * All have the same score.
 * You are not allowed to modify this file, but feel free to read the source code to better understand what is happening in every specific case.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Tests {

    private IntegerContainer container;

    @BeforeEach
    void setUp() {
        container = new IntegerContainerImpl();
    }

    @Test
    @Order(1)
    void testLevel2Case01SimpleMedianOddLength() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(1));
            Assertions.assertEquals(2, container.add(2));
            Assertions.assertEquals(3, container.add(5));
            Assertions.assertEquals(4, container.add(7));
            Assertions.assertEquals(5, container.add(9));
            Assertions.assertEquals(Optional.of(5), container.getMedian());
            Assertions.assertEquals(6, container.add(11));
            Assertions.assertEquals(7, container.add(15));
            Assertions.assertEquals(Optional.of(7), container.getMedian());
        });
    }

    @Test
    @Order(2)
    void testLevel2Case02SimpleMedianEvenLength() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(10));
            Assertions.assertEquals(2, container.add(20));
            Assertions.assertEquals(Optional.of(10), container.getMedian());
            Assertions.assertEquals(3, container.add(30));
            Assertions.assertEquals(4, container.add(40));
            Assertions.assertEquals(Optional.of(20), container.getMedian());
            Assertions.assertEquals(Optional.of(20), container.getMedian());
            Assertions.assertEquals(5, container.add(50));
            Assertions.assertEquals(6, container.add(60));
            Assertions.assertEquals(7, container.add(70));
            Assertions.assertEquals(8, container.add(80));
            Assertions.assertEquals(Optional.of(40), container.getMedian());
        });
    }

    @Test
    @Order(3)
    void testLevel2Case03MedianOfEmptyContainer() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertTrue(container.getMedian()                                                                                          .isEmpty());
            Assertions.assertEquals(1, container.add(1));
            Assertions.assertEquals(Optional.of(1), container.getMedian());
        });
    }

    @Test
    @Order(4)
    void testLevel2Case04MedianOfNonSortedContainer() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(3));
            Assertions.assertEquals(2, container.add(2));
            Assertions.assertEquals(3, container.add(5));
            Assertions.assertEquals(4, container.add(4));
            Assertions.assertEquals(5, container.add(1));
            Assertions.assertEquals(Optional.of(3), container.getMedian());
            Assertions.assertEquals(6, container.add(8));
            Assertions.assertEquals(7, container.add(6));
            Assertions.assertEquals(8, container.add(7));
            Assertions.assertEquals(Optional.of(4), container.getMedian());
        });
    }

    @Test
    @Order(5)
    void testLevel2Case05MedianOfContainerWithDuplicates() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(5));
            Assertions.assertEquals(2, container.add(3));
            Assertions.assertEquals(3, container.add(5));
            Assertions.assertEquals(4, container.add(5));
            Assertions.assertEquals(5, container.add(10));
            Assertions.assertEquals(6, container.add(3));
            Assertions.assertEquals(Optional.of(5), container.getMedian());
            Assertions.assertEquals(7, container.add(3));
            Assertions.assertEquals(8, container.add(3));
            Assertions.assertEquals(9, container.add(3));
            Assertions.assertEquals(Optional.of(3), container.getMedian());
        });
    }

    @Test
    @Order(6)
    void testLevel2Case06MedianWithDeletions() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(30));
            Assertions.assertEquals(2, container.add(20));
            Assertions.assertEquals(3, container.add(10));
            Assertions.assertEquals(Optional.of(20), container.getMedian());
            Assertions.assertTrue(container.delete(30));
            Assertions.assertEquals(Optional.of(10), container.getMedian());
            Assertions.assertTrue(container.delete(10));
            Assertions.assertEquals(Optional.of(20), container.getMedian());
            Assertions.assertTrue(container.delete(20));
            Assertions.assertTrue(container.getMedian().isEmpty());
        });
    }

    @Test
    @Order(7)
    void testLevel2Case07DoubleMedianAndDeletions() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertTrue(container.getMedian().isEmpty());
            Assertions.assertTrue(container.getMedian().isEmpty());
            Assertions.assertFalse(container.delete(239));
            Assertions.assertTrue(container.getMedian().isEmpty());
            Assertions.assertTrue(container.getMedian().isEmpty());
            Assertions.assertEquals(1, container.add(239));
            Assertions.assertEquals(Optional.of(239), container.getMedian());
            Assertions.assertEquals(Optional.of(239), container.getMedian());
            Assertions.assertTrue(container.delete(239));
            Assertions.assertFalse(container.delete(239));
            Assertions.assertTrue(container.getMedian().isEmpty());
            Assertions.assertTrue(container.getMedian().isEmpty());
        });
    }

    @Test
    @Order(8)
    void testLevel2Case08MedianOfContainerWithNegativeIntegers() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(-20));
            Assertions.assertEquals(2, container.add(-10));
            Assertions.assertEquals(3, container.add(10));
            Assertions.assertEquals(4, container.add(20));
            Assertions.assertEquals(5, container.add(0));
            Assertions.assertEquals(Optional.of(0), container.getMedian());
            Assertions.assertEquals(6, container.add(-30));
            Assertions.assertEquals(Optional.of(-10), container.getMedian());
            Assertions.assertEquals(7, container.add(30));
            Assertions.assertEquals(Optional.of(0), container.getMedian());
            Assertions.assertEquals(8, container.add(40));
            Assertions.assertEquals(9, container.add(50));
            Assertions.assertEquals(Optional.of(10), container.getMedian());
        });
    }

    @Test
    @Order(9)
    void testLevel2Case09MixedOperations1() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertTrue(container.getMedian().isEmpty());
            Assertions.assertEquals(1, container.add(5));
            Assertions.assertEquals(2, container.add(3));
            Assertions.assertEquals(3, container.add(5));
            Assertions.assertEquals(4, container.add(7));
            Assertions.assertEquals(5, container.add(8));
            Assertions.assertEquals(6, container.add(9));
            Assertions.assertEquals(Optional.of(5), container.getMedian());
            Assertions.assertTrue(container.delete(5));
            Assertions.assertTrue(container.delete(8));
            Assertions.assertEquals(Optional.of(5), container.getMedian());
            Assertions.assertTrue(container.delete(5));
            Assertions.assertFalse(container.delete(5));
            Assertions.assertEquals(Optional.of(7), container.getMedian());
            Assertions.assertEquals(4, container.add(5));
            Assertions.assertEquals(Optional.of(5), container.getMedian());
            Assertions.assertTrue(container.delete(5));
            Assertions.assertFalse(container.delete(5));
            Assertions.assertTrue(container.delete(7));
            Assertions.assertTrue(container.delete(3));
            Assertions.assertEquals(Optional.of(9), container.getMedian());
            Assertions.assertTrue(container.delete(9));
            Assertions.assertTrue(container.getMedian().isEmpty());
            Assertions.assertFalse(container.delete(9));
            Assertions.assertTrue(container.getMedian().isEmpty());
        });
    }

    @Test
    @Order(10)
    void testLevel2Case10MixedOperations2() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertTrue(container.getMedian().isEmpty());
            Assertions.assertEquals(1, container.add(1));
            Assertions.assertEquals(2, container.add(1));
            Assertions.assertEquals(3, container.add(2));
            Assertions.assertEquals(4, container.add(2));
            Assertions.assertEquals(5, container.add(3));
            Assertions.assertEquals(6, container.add(3));
            Assertions.assertEquals(7, container.add(4));
            Assertions.assertEquals(8, container.add(4));
            Assertions.assertEquals(9, container.add(5));
            Assertions.assertEquals(10, container.add(5));
            Assertions.assertEquals(Optional.of(3), container.getMedian());
            Assertions.assertTrue(container.delete(1));
            Assertions.assertTrue(container.delete(1));
            Assertions.assertFalse(container.delete(1));
            Assertions.assertEquals(Optional.of(3), container.getMedian());
            Assertions.assertTrue(container.delete(2));
            Assertions.assertTrue(container.delete(2));
            Assertions.assertFalse(container.delete(2));
            Assertions.assertEquals(Optional.of(4), container.getMedian());
            Assertions.assertTrue(container.delete(3));
            Assertions.assertTrue(container.delete(4));
            Assertions.assertTrue(container.delete(5));
            Assertions.assertEquals(Optional.of(4), container.getMedian());
        });
    }
}
