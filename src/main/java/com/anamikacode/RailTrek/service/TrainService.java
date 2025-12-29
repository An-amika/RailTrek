package com.anamikacode.RailTrek.service;

import com.anamikacode.RailTrek.entity.Train;

import java.util.List;

public interface TrainService {
    // 1. Create train details
    Train createTrain(Train train);

    // 2. Update train details in an existing route
    Train updateTrain(Integer routeId, Train train);

    // 3. Update fare of a particular train
    Train updateFare(Integer trainId, Double fare);

    // 4. View train details by source and destination
    List<Train> getTrainsBySourceAndDestination(String source, String destination);

    List<Train> getAllTrains();
}
