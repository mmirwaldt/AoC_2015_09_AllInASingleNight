package net.mirwaldt;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ShortestDistancePath implements Comparable<ShortestDistancePath>{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortestDistancePath that = (ShortestDistancePath) o;
        return distance == that.distance && Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(places, distance);
    }

    @Override
    public String toString() {
        return "ShortestDistancePath{" +
                "places=" + places +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(ShortestDistancePath other) {
        int result = Integer.compare(this.distance, other.distance);
        if(result == 0) {
            Iterator<String> thisIterator = places.listIterator();
            Iterator<String> otherIterator = places.listIterator();
            while(thisIterator.hasNext() && otherIterator.hasNext()) {
                result = thisIterator.next().compareTo(otherIterator.next());
                if(result != 0) {
                    return result;
                }
            }
            if(thisIterator.hasNext()) {
                return +1;
            } else {
                return -1;
            }
        }
        return result;
    }
}
