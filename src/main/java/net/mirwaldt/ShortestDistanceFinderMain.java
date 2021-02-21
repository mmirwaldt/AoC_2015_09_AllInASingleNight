package net.mirwaldt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ShortestDistanceFinderMain {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final ShortestDistanceFinder shortestDistanceFinder = new BruteForceShortestDistanceFinder();
        for (String line : lines) {
            final String[] tokens = line.split(" ");
            shortestDistanceFinder.addPath(tokens[0], tokens[2], Integer.parseInt(tokens[4]));
        }
        final ShortestDistancePath shortestDistancePath = shortestDistanceFinder.findShortestDistance();

        // result - [Tristram, AlphaCentauri, Norrath, Straylight, Faerun, Snowdin, Tambi, Arbre] : 141
        System.out.println(shortestDistancePath.getPlaces() + " : " + shortestDistancePath.getDistance());
    }
}
