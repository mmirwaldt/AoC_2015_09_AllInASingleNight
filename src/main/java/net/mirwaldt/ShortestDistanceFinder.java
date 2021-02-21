package net.mirwaldt;

public interface ShortestDistanceFinder {
    void addPath(String from, String to, int distance);
    ShortestDistancePath findShortestDistance();
}
