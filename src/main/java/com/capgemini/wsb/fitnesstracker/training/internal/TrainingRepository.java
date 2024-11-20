package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

interface TrainingRepository extends JpaRepository<Training, Long> {
    default Optional<Training> findByTrainingId(Long trainingId) {
        return findAll().stream()
                        .filter(training -> training.getId().equals(trainingId))
                        .findFirst();
    }
    default List<Training>GetAllTrainings(){
        return findAll();
    }
    default List<Training>GetTrainingsByUser(Long userId){
        return findAll().stream()
                        .filter(training -> training.getUser().getId().equals(userId))
                        .toList();
    }
    default List<Training>GetTrainingsEndedAfter(Date date){
        return findAll().stream()
                        .filter(training -> training.getEndTime().after(date))
                        .toList();
    }
    default List<Training>GetTrainingsByActivityType(ActivityType activityType){
        return findAll().stream()
                        .filter(training -> training.getActivityType().equals(activityType))
                        .toList();
    }
}
