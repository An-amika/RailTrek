package com.anamikacode.RailTrek.service.impl;

import com.anamikacode.RailTrek.entity.Route;
import com.anamikacode.RailTrek.entity.Train;
import com.anamikacode.RailTrek.repository.RouteRepository;
import com.anamikacode.RailTrek.repository.TrainRepository;
import com.anamikacode.RailTrek.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Train createTrain(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public Train updateTrain(Integer routeId, Train train) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found: " + routeId));

        train.setRoute(route);
        return trainRepository.save(train);
    }

    @Override
    public Train updateFare(Integer trainId, Double fare) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found: " + trainId));

        train.setFare(fare);
        return trainRepository.save(train);
    }
    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public List<Train> getTrainsBySourceAndDestination(String source, String destination) {
        return trainRepository.findByRoute_SourceAndRoute_Destination(source, destination);
    }



}
