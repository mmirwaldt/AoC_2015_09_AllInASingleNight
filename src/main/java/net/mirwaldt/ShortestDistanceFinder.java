package net.mirwaldt;

import java.util.List;

public interface ShortestDistanceFinder {
    void addPath(String from, String to, int distance);
    ShortestDistancePath findShortestDistance();

    class ShortestDistancePath {
        private final List<String> places;
        private final int distance;

        public ShortestDistancePath(List<String> places, int distance) {
            this.places = places;
            this.distance = distance;
        }

        public List<String> getPlaces() {
            return places;
        }

        public int getDistance() {
            return distance;
        }
    }
}
