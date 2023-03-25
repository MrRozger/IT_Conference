package com.demo.BRozga.controller;

import com.demo.BRozga.model.LectureHours;
import com.demo.BRozga.model.Participant;
import com.demo.BRozga.model.Reservation;
import com.demo.BRozga.repository.ParticipantRepository;
import com.demo.BRozga.service.LectureHoursService;
import com.demo.BRozga.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;


    private final ParticipantRepository participantRepository;

    private final LectureHoursService lectureHoursService;


    public ReservationController(ReservationService reservationService, ParticipantRepository participantRepository, LectureHoursService lectureHoursService) {
        this.reservationService = reservationService;
        this.participantRepository = participantRepository;
        this.lectureHoursService = lectureHoursService;
    }

    @PostMapping("/add/{lectureId}")
    public ResponseEntity<Reservation> addReservation(@RequestBody Participant participant, @PathVariable Long lectureId) {
        LectureHours lectureHours = lectureHoursService.findById(lectureId);
        Participant participant1 = participantRepository.findByLogin(participant.getLogin()).get();
        if (reservationService.checkEmptySlots(lectureId) && reservationService.checkIfUserAlreadyReservedAtTime(participant1, lectureHours.getStartDate())) {
            Reservation reservation = reservationService.makeReservation(participant1, lectureId);
            reservationService.sendNotification(participant.getLogin());
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/showReservations/{login}")
    public ResponseEntity<List<Reservation>> showReservations(@PathVariable String login) {
        return new ResponseEntity<>(reservationService.showReservation(login), HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.cancelReservation(id), HttpStatus.OK);
    }

    @GetMapping("/interestInLecture")
    public ResponseEntity<List> showInterest() {
        return new ResponseEntity<>(reservationService.interestInLecture(), HttpStatus.OK);
    }

    @GetMapping("/interestInTopic")
    public ResponseEntity<List> showInterestInTopic() {
        return new ResponseEntity<>(reservationService.interestInTopic(), HttpStatus.OK);
    }


}
