package com.anamikacode.RailTrek;

import com.anamikacode.RailTrek.repository.RouteRepository;
import com.anamikacode.RailTrek.repository.TrainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RailTrekApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailTrekApplication.class, args);
	}

	// 👇 must be inside the class
	@Bean
	CommandLineRunner testQueries(TrainRepository trainRepo, RouteRepository routeRepo) {
		return args -> {
			System.out.println("=== Test Queries ===");

			//  Find trains in a route
			System.out.println("Trains in Route 2:");
			trainRepo.findTrainsByRouteId(2).forEach(System.out::println);

			// Find route of a train
			System.out.println("Route of Train 3:");
			System.out.println(routeRepo.findRouteByTrainId(3));

			//  Find trains by source & destination
			System.out.println("Trains from Bangalore to Chennai:");
			trainRepo.findByRoute_SourceAndRoute_Destination("Bangalore", "Chennai")
					.forEach(System.out::println);
		};
	}
}
