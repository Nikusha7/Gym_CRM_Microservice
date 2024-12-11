package ge.nika.gym_crm_microservice.repositories;


import ge.nika.gym_crm_microservice.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository
        extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByUsername(String username);
}
