package com.anamikacode.RailTrek.controller;

import com.anamikacode.RailTrek.entity.Route;
import com.anamikacode.RailTrek.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
@CrossOrigin(origins = "*")
public class RouteController {
    @Autowired
    private RouteService routeService;

    // 1. Create a Route
    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        Route createdRoute = routeService.createRoute(route);
        return ResponseEntity.ok(createdRoute);
    }
    // READ - all
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }
    // 2. View route details by routeId
    @GetMapping("/{routeId}")
    public ResponseEntity<Route> getRouteById(@PathVariable Integer routeId) {
        Route route = routeService.getRouteById(routeId);
        return ResponseEntity.ok(route);
    }

    // 3. View train details by source and destination
    @GetMapping("/trains")
    public ResponseEntity<?> getRoutesBySourceAndDestination(
            //Url:http://localhost:8080/TrackRail/routes/trains?source=Delhi&destination=Mumbai
            @RequestParam String source,
            @RequestParam String destination) {
        List<Route> routes = routeService.getRoutesBySourceAndDestination(source, destination);
        if (routes.isEmpty()) {
            // return message when no route is found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No trains available for this route: " + source + " → " + destination);
        }
        // return routes if found
        return ResponseEntity.ok(routes);
    }


    // 4. Update route details (source, destination)
    @PutMapping("/{routeId}")
    //PUT http://localhost:8080/TrackRail/routes/{routeId}?source={newSource}&destination={newDestination}
    public ResponseEntity<Route> updateRoute(
            @PathVariable Integer routeId,
            @RequestParam String source,
            @RequestParam String destination) {
        Route updatedRoute = routeService.updateRoute(routeId, source, destination);
        return ResponseEntity.ok(updatedRoute);
    }

    // 5. Delete a train from a route
    @DeleteMapping("/{routeId}/{trainId}")
    public ResponseEntity<String> deleteTrainFromRoute(
            @PathVariable Integer routeId,
            @PathVariable Integer trainId) {
        routeService.deleteTrainFromRoute(routeId, trainId);
        return ResponseEntity.ok("Train deleted successfully from route " + routeId);
    }

    // 6. Update train details inside a route
    @PutMapping("/{routeId}/update-trains")
    public ResponseEntity<String> updateTrainInRoute(
            @PathVariable Integer routeId,
            @RequestBody Route updatedRoute) {
        String response = routeService.updateTrainInRoute(routeId, updatedRoute);
        return ResponseEntity.ok(response);
    }
}
