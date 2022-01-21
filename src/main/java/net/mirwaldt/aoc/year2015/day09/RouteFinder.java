package net.mirwaldt.aoc.year2015.day09;

public interface RouteFinder {
    void addPath(String from, String to, int distance);
    Route findShortestRoute();
    Route findLongestRoute();
}
