package com.anamikacode.RailTrek.repository;

import com.anamikacode.RailTrek.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route,Integer> {


    @Query("SELECT r FROM Route r JOIN r.trainList t WHERE t.id = :trainId")
    Route findRouteByTrainId(@Param("trainId") Integer trainId);}
