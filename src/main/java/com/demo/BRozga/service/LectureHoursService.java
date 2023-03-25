package com.demo.BRozga.service;

import com.demo.BRozga.model.LectureHours;
import com.demo.BRozga.repository.LectureHoursRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LectureHoursService {

    private final LectureHoursRepository lectureHoursRepository;

    public LectureHoursService(LectureHoursRepository lectureHoursRepository) {
        this.lectureHoursRepository = lectureHoursRepository;
    }

    public List<LectureHours> showPlan(){
        return lectureHoursRepository.findAll();
    }

    public LectureHours findById(Long id){
        return lectureHoursRepository.findById(id).get();
    }
}
