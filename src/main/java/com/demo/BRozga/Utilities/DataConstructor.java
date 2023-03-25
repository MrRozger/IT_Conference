package com.demo.BRozga.Utilities;

import com.demo.BRozga.model.Lecture;
import com.demo.BRozga.model.LectureHours;
import com.demo.BRozga.repository.LectureHoursRepository;
import com.demo.BRozga.repository.LectureRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.*;


@Component
public class DataConstructor {

    private LectureRepository lectureRepository;
    private LectureHoursRepository lectureHoursRepository;

    public DataConstructor(LectureRepository lectureRepository, LectureHoursRepository lectureHoursRepository) {
        this.lectureRepository = lectureRepository;
        this.lectureHoursRepository = lectureHoursRepository;
    }

    LocalDate date = LocalDate.of(2021, 06, 1);
    LocalTime timeStart1 = LocalTime.of(10, 00);
    LocalTime timeEnd1 = LocalTime.of(11, 45);

    LocalDateTime localDateTime1 = LocalDateTime.of(date, timeStart1);
    LocalDateTime localDateTime2 = LocalDateTime.of(date, timeEnd1);

    // Print statement
    @PostConstruct
    public void dataConstruct() {
        Lecture lectureJava = new Lecture();
        lectureJava.setConferenceType(Lecture.Type.JAVA);
        Lecture lectureConsulting = new Lecture();
        lectureConsulting.setConferenceType(Lecture.Type.CONSULTING);
        lectureRepository.save(lectureJava);
        lectureRepository.save(lectureConsulting);
        LectureHours JavaLecture = new LectureHours();
        JavaLecture.setLectures(lectureJava);
        JavaLecture.setStartDate(localDateTime1);
        JavaLecture.setEndDate(localDateTime2);
        JavaLecture.setDescription("Prelekcja dotycząca Javy");
        lectureHoursRepository.save(JavaLecture);
        LectureHours JavaLecture1 = new LectureHours();
        JavaLecture1.setLectures(lectureJava);
        JavaLecture1.setStartDate(localDateTime1);
        JavaLecture1.setEndDate(localDateTime2);
        JavaLecture1.setDescription("Prelekcja dotycząca Javy 2");
        lectureHoursRepository.save(JavaLecture1);
        LectureHours consultingLecture = new LectureHours();
        consultingLecture.setLectures(lectureConsulting);
        consultingLecture.setStartDate(localDateTime1);
        consultingLecture.setEndDate(localDateTime2);
        consultingLecture.setDescription("Prelekcja dotycząca Consultingu");
        lectureHoursRepository.save(consultingLecture);
        LectureHours consultingLecture1 = new LectureHours();
        consultingLecture1.setLectures(lectureConsulting);
        consultingLecture1.setStartDate(localDateTime1);
        consultingLecture1.setEndDate(localDateTime2);
        consultingLecture1.setDescription("Prelekcja dotycząca Consultingu 2");
        lectureHoursRepository.save(consultingLecture1);

    }


}
