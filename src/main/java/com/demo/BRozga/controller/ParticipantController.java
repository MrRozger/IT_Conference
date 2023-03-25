package com.demo.BRozga.controller;

import com.demo.BRozga.dto.ParticipantDTO;
import com.demo.BRozga.model.Participant;
import com.demo.BRozga.service.ParticipantService;
import com.demo.BRozga.service.ReservationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    private final ReservationService reservationService;

    private final ParticipantService participantService;

    public ParticipantController(ReservationService reservationService, ParticipantService participantService) {
        this.reservationService = reservationService;
        this.participantService = participantService;
    }

    @PostMapping("/add/")
    public ResponseEntity<String> addParticipant(@RequestBody Participant participant) {
        if (participantService.checkIfLoginExist(participant.getLogin()) == false) {
            participantService.createParticipant(participant);
            return new ResponseEntity<>("Konto Stworzone", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Podany login jest już zajęty", HttpStatus.OK);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<Participant>> showAllParticipants() {
        return new ResponseEntity<>(participantService.showList(), HttpStatus.OK);
    }

    @PutMapping("/edit/{login}")
    public ResponseEntity<Participant> editParticipant(@RequestBody ParticipantDTO participantDto, @PathVariable String login) {
        Participant participant = participantService.findByLogin(login);
        participantService.editParticipantEmail(participantDto, participant);
        return new ResponseEntity<>(participant, HttpStatus.OK);
    }


}
