package com.demo.BRozga.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;


@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean cancelled = false;

    @ManyToOne
    @JsonManagedReference
    private Participant participant;

    @OneToOne
    private LectureHours lectureHours;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public LectureHours getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(LectureHours lectureHours) {
        this.lectureHours = lectureHours;
    }

}
