package net.mirwaldt.aoc.year2015.day09;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RouteFinderMain {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final RouteFinder routeFinder = new BruteForceRouteFinder();
        for (String line : lines) {
            final String[] tokens = line.split(" ");
            routeFinder.addPath(tokens[0], tokens[2], Integer.parseInt(tokens[4]));
        }
        final Route shortestRoute = routeFinder.findShortestRoute();

        // result - [Tristram, AlphaCentauri, Norrath, Straylight, Faerun, Snowdin, Tambi, Arbre] : 141
        System.out.println(shortestRoute.getPlaces() + " : " + shortestRoute.getDistance());

        final Route longestRoute = routeFinder.findLongestRoute();

        // result - [AlphaCentauri, Arbre, Tristram, Snowdin, Straylight, Tambi, Norrath, Faerun] : 736
        System.out.println(longestRoute.getPlaces() + " : " + longestRoute.getDistance());
    }
}
