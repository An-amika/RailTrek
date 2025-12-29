package com.anamikacode.RailTrek.controller;

import com.anamikacode.RailTrek.entity.Train;
import com.anamikacode.RailTrek.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trains")
@CrossOrigin(origins = "*")
public class TrainController {

    @Autowired
    private TrainService trainService;

    // 1. Create train details
    @PostMapping
    public ResponseEntity<Train> createTrain(@RequestBody Train train) {
        Train createdTrain = trainService.createTrain(train);
        return ResponseEntity.ok(createdTrain);
    }
    @GetMapping
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    // 2. Update train details (assign to route)
    @PutMapping("/{routeId}")
    public ResponseEntity<Train> updateTrain(
            @PathVariable Integer routeId,
            @RequestBody Train train) {
        Train updatedTrain = trainService.updateTrain(routeId, train);
        return ResponseEntity.ok(updatedTrain);
    }

    // 3. Update fare of a train
    @PatchMapping("/{trainId}/fare")
    public ResponseEntity<Train> updateFare(
            @PathVariable Integer trainId,
            @RequestParam Double fare) {
        Train updatedTrain = trainService.updateFare(trainId, fare);
        return ResponseEntity.ok(updatedTrain);
    }

    // 4. View train details by source and destination
    @GetMapping("/search")
    public ResponseEntity<?> getTrainsBySourceAndDestination(
            @RequestParam String source, @RequestParam String destination) {
        List<Train> trains = trainService.getTrainsBySourceAndDestination(source, destination);
        if (trains.isEmpty()) {
            // return a message with 404 or 200 depending on your requirement
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No trains available for this route: " + source + " → " + destination);
        }
        return ResponseEntity.ok(trains);
    }

}
