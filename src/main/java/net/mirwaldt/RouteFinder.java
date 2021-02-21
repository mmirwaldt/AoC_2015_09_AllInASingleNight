package net.mirwaldt;

public interface RouteFinder {
    void addPath(String from, String to, int distance);
    Route findShortestRoute();
    Route findLongestRoute();
}
