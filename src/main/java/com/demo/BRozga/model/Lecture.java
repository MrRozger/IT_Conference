package com.demo.BRozga.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table
public class Lecture {

    public enum Type {
        JAVA{
            @Override
            public String toString(){
                return "Java";
            }
        },
        CONSULTING{
            @Override
            public String toString(){
                return "Consulting";
            }
        },
        WORK_BALANCE{
            @Override
            public String toString(){
                return "Work Balance";
            }
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type conferenceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getConferenceType() {
        return conferenceType;
    }

    public void setConferenceType(Type conferenceType) {
        this.conferenceType = conferenceType;
    }

}
