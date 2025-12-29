package com.anamikacode.RailTrek.service.impl;

import com.anamikacode.RailTrek.entity.Route;
import com.anamikacode.RailTrek.entity.Train;
import com.anamikacode.RailTrek.repository.RouteRepository;
import com.anamikacode.RailTrek.repository.TrainRepository;
import com.anamikacode.RailTrek.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;


    @Autowired
    private TrainRepository trainRepository;


    @Override
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }
    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
    @Override
    public Route getRouteById(Integer routeId) {
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route ID not found: " + routeId));
    }

    @Override
    public List<Route> getRoutesBySourceAndDestination(String source, String destination) {
        return routeRepository.findAll().stream()
                .filter(r -> r.getSource().equalsIgnoreCase(source)
                        && r.getDestination().equalsIgnoreCase(destination))
                .toList();
    }

    @Override
    public Route updateRoute(Integer routeId, String source, String destination) {
        Route route = getRouteById(routeId);
        route.setSource(source);
        route.setDestination(destination);
        return routeRepository.save(route);
    }

    @Override
    public void deleteTrainFromRoute(Integer routeId, Integer trainId) {
        Route route = getRouteById(routeId);

        Optional<Train> trainOpt = route.getTrainList()
                .stream()
                .filter(t -> t.getId().equals(trainId))
                .findFirst();

        if (trainOpt.isPresent()) {
            Train train = trainOpt.get();
            route.getTrainList().remove(train);
            trainRepository.delete(train);
            routeRepository.save(route);
        } else {
            throw new RuntimeException("Train ID " + trainId + " not found in route " + routeId);
        }
    }

    @Override
    public String updateTrainInRoute(Integer routeId, Route updatedRoute) {
        Route route = getRouteById(routeId);
        route.setTrainList(updatedRoute.getTrainList());
        routeRepository.save(route);

        return "Train details updated successfully in route " + routeId;
    }
}
