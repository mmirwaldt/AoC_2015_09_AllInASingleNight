package net.mirwaldt.aoc.year2015.day09;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RouteFinderTest {
    private static Stream<Arguments> shortestDistanceFinder() {
        return Stream.of(Arguments.of(new BruteForceRouteFinder()));
    }

    @ParameterizedTest
    @MethodSource("shortestDistanceFinder")
    void test_simpleExample(RouteFinder routeFinder) {
        routeFinder.addPath("London", "Dublin", 464);
        routeFinder.addPath("London", "Belfast", 518);
        routeFinder.addPath("Dublin", "Belfast", 141);
        final Route path = routeFinder.findShortestRoute();
        assertEquals(Arrays.asList("London", "Dublin", "Belfast"), path.getPlaces());
        assertEquals(605, path.getDistance());
    }
}
