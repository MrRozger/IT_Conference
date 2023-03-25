package com.demo.BRozga.controller;

import com.demo.BRozga.model.LectureHours;
import com.demo.BRozga.service.LectureHoursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lectureHours")
public class LectureHoursController {

    private final LectureHoursService lectureHoursService;

    public LectureHoursController(LectureHoursService lectureHoursService) {
        this.lectureHoursService = lectureHoursService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LectureHours>> showPlan(){
        return new ResponseEntity<>(lectureHoursService.showPlan(), HttpStatus.OK);
    }
}
