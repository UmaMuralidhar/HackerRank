package com.hackerrank.gevents.repository;

import com.hackerrank.gevents.model.Event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	Optional<Event> findById(Integer id);
	Event save(Event event);
	List<Event>  findAll();
	@Query("FROM Event where repoId=?1")
	List<Event> findAllEvent(Integer repoId);
}
