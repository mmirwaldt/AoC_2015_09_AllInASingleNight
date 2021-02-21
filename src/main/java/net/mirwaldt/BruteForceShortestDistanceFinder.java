package net.mirwaldt;

import java.util.Collections;

public class BruteForceShortestDistanceFinder implements ShortestDistanceFinder {
    @Override
    public void addPath(String from, String to, int distance) {

    }

    @Override
    public ShortestDistancePath findShortestDistance() {
        return new ShortestDistancePath(Collections.emptyList(), 0);
    }
}
