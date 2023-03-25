package com.demo.BRozga.service;


import com.demo.BRozga.dto.ParticipantDTO;
import com.demo.BRozga.model.Participant;
import com.demo.BRozga.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public void editParticipantEmail(ParticipantDTO eParticipant, Participant participant) {
        participant.setEmail(eParticipant.getEmail());
        participantRepository.save(participant);
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> showList() {
        return participantRepository.findAll();
    }


    public boolean checkIfLoginExist(String login) {
        return participantRepository.existsByLogin(login);
    }

    public Participant findByLogin(String login){
        return participantRepository.findByLogin(login).get();
    }

}
