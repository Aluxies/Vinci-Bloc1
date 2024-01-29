package domaine;

import java.time.Duration;

public class Instruction {

    private String description;
    private Duration dureeEnMinutes;

    public Instruction(String description, int dureeEnMinutes) {

        this.description = description;
        this.dureeEnMinutes = Duration.ofMinutes( dureeEnMinutes );

    }

    public String getDescription() {
        return description;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDureeEnMinutes(Duration dureeEnMinutes) {
        this.dureeEnMinutes = dureeEnMinutes;
    }

    @Override
    public String toString() {
        return "(" + String.format("%d:%02d", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60) + ") " + description;
    }
}