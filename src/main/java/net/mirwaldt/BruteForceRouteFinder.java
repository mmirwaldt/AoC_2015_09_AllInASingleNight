package net.mirwaldt;

import java.util.*;
import java.util.stream.Collectors;

public class BruteForceRouteFinder implements RouteFinder {
    private final Map<Set<String>, Integer> routes = new HashMap<>();

    @Override
    public void addPath(String from, String to, int distance) {
        final Set<String> places = new HashSet<>();
        places.add(from);
        places.add(to);
        routes.put(places, distance);
    }

    @Override
    public Route findShortestDistance() {
        if (routes.isEmpty()) {
            return new Route(Collections.emptyList(), 0);
        } else {
            final Optional<Map.Entry<List<String>, Integer>> optionalShortestDistanceRoute =
                    new InternalBruteForceShortestDistanceFinder(routes).traverseAllPlaces();
            final Map.Entry<List<String>, Integer> entry = optionalShortestDistanceRoute.get();
            return new Route(entry.getKey(), entry.getValue());
        }
    }

    static class InternalBruteForceShortestDistanceFinder {
        private final Map<Set<String>, Integer> routes;
        private final Set<String> allPlaces;

        public InternalBruteForceShortestDistanceFinder(Map<Set<String>, Integer> routes) {
            this.routes = routes;
            this.allPlaces = routes.keySet().stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
        }

        private Optional<Map.Entry<List<String>, Integer>> traverseAllPlaces() {
            Map<List<String>, Integer> traversedPlaces = new HashMap<>();
            addPathStarts(traversedPlaces);
            for (int length = 3; length <= allPlaces.size(); length++) {
                traversedPlaces = traversePlaces(traversedPlaces);
            }

            return traversedPlaces.entrySet().stream()
                    .min(Comparator.comparingInt(Map.Entry::getValue));
        }

        private Map<List<String>, Integer> traversePlaces(Map<List<String>, Integer> traversedPaths) {
            final Map<List<String>, Integer> newTraversedPaths = new HashMap<>();
            for (Map.Entry<List<String>, Integer> entry : traversedPaths.entrySet()) {
                final List<String> path = entry.getKey();
                final int distance = entry.getValue();
                traverseSubPaths(newTraversedPaths, path, distance);
            }
            return newTraversedPaths;
        }

        private void traverseSubPaths(Map<List<String>, Integer> newTraversedPaths, List<String> route, int distance) {
            final String lastPlace = route.get(route.size() - 1);
            final Set<String> allPlacesWithoutTraversedPlaces = findAllPlacesWithoutTraversedPlaces(route);
            final Map<String, Set<String>> nextPathsByOtherPlaces = findNextPaths(lastPlace, allPlacesWithoutTraversedPlaces);

            for (Map.Entry<String, Set<String>> nextPathByOtherPlace : nextPathsByOtherPlaces.entrySet()) {
                final String destination = nextPathByOtherPlace.getKey();
                final Set<String> nextPath = nextPathByOtherPlace.getValue();

                final int distanceToDestination = routes.getOrDefault(nextPath, 0);
                if (0 < distanceToDestination) {
                    final List<String> newPath = new ArrayList<>(route);
                    newPath.add(destination);
                    final int totalDistance = distance + distanceToDestination;
                    newTraversedPaths.put(newPath, totalDistance);
                }
            }
        }

        private Set<String> findAllPlacesWithoutTraversedPlaces(List<String> route) {
            final Set<String> allPlacesWithoutTraversedPlaces = new HashSet<>(allPlaces);
            allPlacesWithoutTraversedPlaces.removeAll(route);
            return allPlacesWithoutTraversedPlaces;
        }

        private Map<String, Set<String>> findNextPaths(String lastPlace, Set<String> allPlacesWithoutTraversedPlaces) {
            final Map<String, Set<String>> pathsWithLastPlace = new HashMap<>();
            for (String otherPlace : allPlacesWithoutTraversedPlaces) {
                Set<String> nextPath = new HashSet<>();
                nextPath.add(otherPlace);
                nextPath.add(lastPlace);
                pathsWithLastPlace.put(otherPlace, nextPath);
            }
            return pathsWithLastPlace;
        }

        private void addPathStarts(Map<List<String>, Integer> traversedPaths) {
            for (Map.Entry<Set<String>, Integer> routeEntry : routes.entrySet()) {
                final Set<String> route = routeEntry.getKey();
                final int distance = routeEntry.getValue();
                final List<String> traversedPath = new ArrayList<>(route);
                traversedPaths.put(traversedPath, distance);
            }
        }
    }
}
