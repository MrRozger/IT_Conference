package com.demo.BRozga.repository;

import com.demo.BRozga.model.LectureHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureHoursRepository extends JpaRepository<LectureHours,Long> {

    Optional<LectureHours> findById(Long id);


}
