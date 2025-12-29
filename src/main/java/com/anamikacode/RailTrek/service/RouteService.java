package com.anamikacode.RailTrek.service;

import com.anamikacode.RailTrek.entity.Route;

import java.util.List;

public interface RouteService {
    Route createRoute(Route route);
    Route getRouteById(Integer routeId);
    List<Route> getRoutesBySourceAndDestination(String source, String destination);
    Route updateRoute(Integer routeId, String source, String destination);
    void deleteTrainFromRoute(Integer routeId, Integer trainId);
    String updateTrainInRoute(Integer routeId, Route updatedRoute);

    List<Route> getAllRoutes();
}
