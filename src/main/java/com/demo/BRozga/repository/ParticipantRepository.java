package com.demo.BRozga.repository;

import com.demo.BRozga.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    boolean existsByLogin(String login);

    Optional<Participant> findByLogin(String login);


}
