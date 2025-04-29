package test.codesignal.integercontainer;

import java.time.Duration;

import codesignal.integercontainer.IntegerContainer;
import codesignal.integercontainer.IntegerContainerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * The test class below includes 10 tests for Level 1.
 *
 * All have the same score.
 * You are not allowed to modify this file, but feel free to read the source code to better understand what is happening in every specific case.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test1 {

    private IntegerContainer container;

    @BeforeEach
    void setUp() {
        container = new IntegerContainerImpl();
    }

    @Test
    @Order(1)
    void testLevel1Case01AddTwoNumbers() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(10));
            Assertions.assertEquals(2, container.add(100));
        });
    }

    @Test
    @Order(2)
    void testLevel1Case02AddManyNumbers() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(10));
            Assertions.assertEquals(2, container.add(9));
            Assertions.assertEquals(3, container.add(8));
            Assertions.assertEquals(4, container.add(7));
            Assertions.assertEquals(5, container.add(6));
            Assertions.assertEquals(6, container.add(5));
            Assertions.assertEquals(7, container.add(4));
            Assertions.assertEquals(8, container.add(3));
            Assertions.assertEquals(9, container.add(2));
            Assertions.assertEquals(10, container.add(1));
        });
    }

    @Test
    @Order(3)
    void testLevel1Case03DeleteNumber() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(10));
            Assertions.assertEquals(2, container.add(100));
            Assertions.assertTrue(container.delete(10));
        });
    }

    @Test
    @Order(4)
    void testLevel1Case04DeleteNonexistingNumber() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(10));
            Assertions.assertEquals(2, container.add(100));
            Assertions.assertFalse(container.delete(20));
            Assertions.assertTrue(container.delete(10));
            Assertions.assertFalse(container.delete(10));
        });
    }

    @Test
    @Order(5)
    void testLevel1Case05AddAndDeleteSameNumbers() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(10));
            Assertions.assertEquals(2, container.add(10));
            Assertions.assertEquals(3, container.add(10));
            Assertions.assertEquals(4, container.add(10));
            Assertions.assertEquals(5, container.add(10));
            Assertions.assertTrue(container.delete(10));
            Assertions.assertTrue(container.delete(10));
            Assertions.assertTrue(container.delete(10));
            Assertions.assertTrue(container.delete(10));
            Assertions.assertTrue(container.delete(10));
            Assertions.assertFalse(container.delete(10));
            Assertions.assertFalse(container.delete(10));
        });
    }

    @Test
    @Order(6)
    void testLevel1Case06AddDeleteSeveralTimes() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(555));
            Assertions.assertTrue(container.delete(555));
            Assertions.assertFalse(container.delete(555));
            Assertions.assertEquals(1, container.add(555));
            Assertions.assertTrue(container.delete(555));
            Assertions.assertFalse(container.delete(555));
        });
    }

    @Test
    @Order(7)
    void testLevel1Case07DeleteInRandomOrder() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(10));
            Assertions.assertEquals(2, container.add(20));
            Assertions.assertEquals(3, container.add(30));
            Assertions.assertEquals(4, container.add(40));
            Assertions.assertEquals(5, container.add(40));
            Assertions.assertTrue(container.delete(30));
            Assertions.assertFalse(container.delete(30));
            Assertions.assertTrue(container.delete(10));
            Assertions.assertFalse(container.delete(10));
            Assertions.assertTrue(container.delete(40));
            Assertions.assertTrue(container.delete(40));
            Assertions.assertFalse(container.delete(40));
            Assertions.assertTrue(container.delete(20));
            Assertions.assertFalse(container.delete(20));
        });
    }

    @Test
    @Order(8)
    void testLevel1Case08DeleteBeforeAdd() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertFalse(container.delete(1));
            Assertions.assertFalse(container.delete(2));
            Assertions.assertFalse(container.delete(3));
            Assertions.assertEquals(1, container.add(1));
            Assertions.assertEquals(2, container.add(2));
            Assertions.assertEquals(3, container.add(3));
            Assertions.assertTrue(container.delete(3));
            Assertions.assertTrue(container.delete(2));
            Assertions.assertTrue(container.delete(1));
            Assertions.assertFalse(container.delete(3));
            Assertions.assertFalse(container.delete(2));
            Assertions.assertFalse(container.delete(1));
        });
    }

    @Test
    @Order(9)
    void testLevel1Case09MixedOperation1() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertEquals(1, container.add(10));
            Assertions.assertEquals(2, container.add(15));
            Assertions.assertEquals(3, container.add(20));
            Assertions.assertEquals(4, container.add(10));
            Assertions.assertEquals(5, container.add(5));
            Assertions.assertTrue(container.delete(15));
            Assertions.assertTrue(container.delete(20));
            Assertions.assertFalse(container.delete(20));
            Assertions.assertFalse(container.delete(0));
            Assertions.assertEquals(4, container.add(7));
            Assertions.assertEquals(5, container.add(9));
            Assertions.assertTrue(container.delete(7));
            Assertions.assertTrue(container.delete(10));
            Assertions.assertTrue(container.delete(10));
            Assertions.assertFalse(container.delete(10));
            Assertions.assertFalse(container.delete(100));
        });
    }

    @Test
    @Order(10)
    void testLevel1Case10MixedOperation2() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            Assertions.assertFalse(container.delete(6));
            Assertions.assertEquals(1, container.add(100));
            Assertions.assertFalse(container.delete(200));
            Assertions.assertEquals(2, container.add(500));
            Assertions.assertFalse(container.delete(0));
            Assertions.assertEquals(3, container.add(300));
            Assertions.assertFalse(container.delete(1000));
            Assertions.assertEquals(4, container.add(400));
            Assertions.assertTrue(container.delete(300));
            Assertions.assertTrue(container.delete(400));
            Assertions.assertTrue(container.delete(100));
            Assertions.assertTrue(container.delete(500));
            Assertions.assertEquals(1, container.add(1000));
            Assertions.assertEquals(2, container.add(100));
            Assertions.assertEquals(3, container.add(10));
            Assertions.assertEquals(4, container.add(1));
            Assertions.assertTrue(container.delete(100));
            Assertions.assertFalse(container.delete(500));
            Assertions.assertFalse(container.delete(300));
            Assertions.assertFalse(container.delete(400));
        });
    }
}
