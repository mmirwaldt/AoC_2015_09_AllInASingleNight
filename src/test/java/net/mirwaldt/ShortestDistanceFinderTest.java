package net.mirwaldt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShortestDistanceFinderTest {
    private static Stream<Arguments> shortestDistanceFinder() {
        return Stream.of(Arguments.of(new BruteForceShortestDistanceFinder()));
    }

    @ParameterizedTest
    @MethodSource("shortestDistanceFinder")
    void test_noCircuit(ShortestDistanceFinder shortestDistanceFinder) {
        shortestDistanceFinder.addPath("London", "Dublin", 464);
        shortestDistanceFinder.addPath("London", "Belfast", 518);
        shortestDistanceFinder.addPath("Dublin", "Belfast", 141);
        final ShortestDistanceFinder.ShortestDistancePath path = shortestDistanceFinder.findShortestDistance();
        assertEquals(Arrays.asList("London", "Dublin", "Belfast"), path.getPlaces());
        assertEquals(605, path.getDistance());
    }
}
