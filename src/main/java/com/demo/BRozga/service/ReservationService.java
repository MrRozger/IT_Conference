package com.demo.BRozga.service;

import com.demo.BRozga.model.LectureHours;
import com.demo.BRozga.model.Participant;
import com.demo.BRozga.model.Reservation;
import com.demo.BRozga.repository.LectureHoursRepository;
import com.demo.BRozga.repository.ParticipantRepository;
import com.demo.BRozga.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final LectureHoursRepository lectureHoursRepository;

    private final ParticipantRepository participantRepository;


    private final ReservationRepository reservationRepository;

    public ReservationService(LectureHoursRepository lectureHoursRepository, ParticipantRepository participantRepository, ReservationRepository reservationRepository) {
        this.lectureHoursRepository = lectureHoursRepository;
        this.participantRepository = participantRepository;
        this.reservationRepository = reservationRepository;
    }

    public Reservation makeReservation(Participant participant, Long lectureHoursId) {
        Reservation reservation = new Reservation();
        reservation.setParticipant(participant);
        LectureHours lectureHours = lectureHoursRepository.findById(lectureHoursId).get();
        reservation.setLectureHours(lectureHours);
        return reservationRepository.save(reservation);

    }

    public List<Reservation> showReservation(String login) {
        Participant participant;
        participant = participantRepository.findByLogin(login).get();
        return reservationRepository.findReservationsByParticipantId(participant.getId());
    }

    public Reservation cancelReservation(Long id) {
        Reservation reservation;
        reservation = reservationRepository.findById(id).get();
        reservation.setCancelled(true);
        reservationRepository.save(reservation);
        return reservation;
    }

    public void sendNotification(String login) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("C:/WORK/powiadomienia" + login + ".txt"),
                StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW)) {
            String someText = LocalDateTime.now() + " Dziekujemy za zapisanie sie na prelekcje " + login;
            writer.write(someText);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public boolean checkEmptySlots(Long lectureId) {
        List<Reservation> notCancelled = checkIfCancelled();
        int count = 0;
        for (Reservation notCancel : notCancelled) {
            if (notCancel.getLectureHours().getId() == lectureId) {
                count++;
            }
        }
        return count >= 5 ? false : true;

    }

    public List<String> interestInLecture() {
        List<Reservation> notCancelled = checkIfCancelled();
        List<String> percentOfPeople = new ArrayList<>();
        float count = 0;
        for (int i = 1; i < 5; i++) {
            for (Reservation reservation : notCancelled) {
                if (reservation.getLectureHours().getId() == i) {
                    count++;
                }
            }
            percentOfPeople.add("Lecture no " + i + ": " + count / 5 * 100 + "%");
            count = 0;
        }
        return percentOfPeople;
    }

    public List<String> interestInTopic() {
        List<Reservation> notCancelled = checkIfCancelled();
        List<String> percentOfPeopleInTopic = new ArrayList<>();
        float count = 0;
        String type = null;
        for (int i = 1; i < 3; i++) {
            for (Reservation reservation : notCancelled) {
                if (reservation.getLectureHours().getLectures().getId() == i) {
                    count++;
                    type = String.valueOf(reservation.getLectureHours().getLectures().getConferenceType());
                }
            }
            String number = String.format("%.2f", count / 15);
            percentOfPeopleInTopic.add("Topic " + type + " : " + number + "%");
            count = 0;
        }
        return percentOfPeopleInTopic;
    }

    public List<Reservation> checkIfCancelled() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> notCancelled = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.isCancelled() == false) {
                notCancelled.add(reservation);
            }
        }
        return notCancelled;
    }

    public boolean checkIfUserAlreadyReservedAtTime(Participant participant, LocalDateTime localDateTime) {
        List<Reservation> notCancelled = checkIfCancelled();
        for (Reservation reservation : notCancelled) {
            if (reservation.getParticipant().getLogin() == participant.getLogin() && reservation.getLectureHours().getStartDate().equals(localDateTime)) {
                System.out.println("Uzytkownik jest zarejstrowany na inna prelekcje w tym czasie");
                return false;
            }
        }
        return true;
    }

}
