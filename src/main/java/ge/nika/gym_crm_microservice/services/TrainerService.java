package ge.nika.gym_crm_microservice.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


import ge.nika.gym_crm_microservice.DTO.TrainerRequest;
import ge.nika.gym_crm_microservice.entities.Trainer;
import ge.nika.gym_crm_microservice.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.YearMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainerService {
    @Autowired
    TrainerRepository trainerRepository;

    private static final Logger logger = LoggerFactory.getLogger(TrainerService.class);
    @CircuitBreaker(name = "trainerServiceCircuitBreaker", fallbackMethod = "fallbackUpdateTrainerWorkload")
    public void updateTrainerWorkload(TrainerRequest request) {
        Trainer trainer = trainerRepository.findByUsername(request.getUsername())
                .orElseGet(() -> new Trainer(request.getUsername(), request.getFirstName(), request.getLastName(), request.isActive()));

        YearMonth yearMonth = YearMonth.from(request.getTrainingDate());
        long hours = request.getTrainingDuration().toHours();

        if ("ADD".equals(request.getActionType()) || "DELETE".equals(request.getActionType())) {
            trainer.updateTrainingDuration(yearMonth, hours, request.getActionType());
            trainerRepository.save(trainer);
        } else {
            throw new IllegalArgumentException("Invalid action type");
        }
    }

    public void fallbackUpdateTrainerWorkload(TrainerRequest request, Throwable t) {
        logger.error("Error happened while updating trainer request: " + t.getMessage());
    }
}

