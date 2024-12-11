package ge.nika.gym_crm_microservice.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
public class TrainerRequest {
    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private LocalDate trainingDate;
    private Duration trainingDuration;
    private String actionType;

    @JsonCreator
    public TrainerRequest(
            @JsonProperty("username") String username,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("isActive") boolean isActive,
            @JsonProperty("trainingDate") LocalDate trainingDate,
            @JsonProperty("trainingDuration") Duration trainingDuration,
            @JsonProperty("actionType") String actionType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
        this.actionType = actionType;
    }
}

