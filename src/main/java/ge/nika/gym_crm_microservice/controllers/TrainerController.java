package ge.nika.gym_crm_microservice.controllers;

import ge.nika.gym_crm_microservice.DTO.TrainerRequest;
import ge.nika.gym_crm_microservice.services.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class TrainerController {

    @Autowired
    TrainerService trainerService;

    private static final Logger logger = LoggerFactory.getLogger(TrainerController.class);

    @JmsListener(destination = "trainerWorkloadQueue")
    public void updateTrainerWorkload(@RequestBody TrainerRequest request) {
        logger.info("Received request to update trainer: {}", request.getUsername());

        try {
            trainerService.updateTrainerWorkload(request);
            logger.info("Workload updated successfully: {}", request.getUsername());
        } catch (Exception e) {
            logger.error("Error happened while updating trainer: " + e.getMessage());
        }
    }
}

