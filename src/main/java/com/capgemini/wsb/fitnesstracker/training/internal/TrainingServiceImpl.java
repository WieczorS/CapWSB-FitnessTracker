package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {
    @Override
    public Training createTraining(Training training) {
        return null;
    }

    @Override
    public Training updateTraining(Training training) {
        return null;
    }

    @Override
    public void deleteTraining(Long trainingId) {

    }

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> GetAllTrainings() {
        return List.of();
    }

    @Override
    public List<Training> GetTrainingsByUser(Long userId) {
        return List.of();
    }

    @Override
    public List<Training> GetTrainingsEndedAfter(String date) {
        return List.of();
    }

    @Override
    public List<Training> GetTrainingsByActivityType(ActivityType activityType) {
        return List.of();
    }

}
