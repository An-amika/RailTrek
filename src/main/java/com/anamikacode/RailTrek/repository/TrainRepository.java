package com.anamikacode.RailTrek.repository;

import com.anamikacode.RailTrek.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
    List<Train> findByRoute_SourceAndRoute_Destination(String source, String destination);

    @Query("SELECT t FROM Train t WHERE t.route.id = :routeId")
    List<Train> findTrainsByRouteId(@Param("routeId") Integer routeId);

  //  @Query("SELECT t.route.id FROM Train t WHERE t.id = :trainId")
   // Integer findRouteIdByTrainId(@Param("trainId") Integer trainId);
}
