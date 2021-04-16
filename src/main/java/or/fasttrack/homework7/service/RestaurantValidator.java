package or.fasttrack.homework7.service;

import lombok.RequiredArgsConstructor;
import or.fasttrack.homework7.domain.Restaurant;
import or.fasttrack.homework7.exception.ValidationException;
import or.fasttrack.homework7.profile.CitiesProfile;
import or.fasttrack.homework7.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class RestaurantValidator {
    private final RestaurantRepo repo;
    private final CitiesProfile profile;
    @Value("${spring.profiles.active}")
    private String activeProfile;


    public void validateNewThrow(Restaurant restaurant) {
        validate(restaurant, true);
    }

    private void validate(Restaurant restaurant, Boolean isItNew) {
        if (restaurant.getName() == null) {
            throw new ValidationException("Name cannot be null");
        } else if (restaurant.getCity() == null) {
            throw new ValidationException("City cannot be null");
        } else if (restaurant.getSince() == null && restaurant.getSince().isAfter(LocalDate.now())) {
            throw new ValidationException("Date cannot be null or be after current date");
        } else if (isItNew && repo.existsByName(restaurant.getName())) {
            throw new ValidationException("This restaurant already exists");
        } else if (!profile.getCities().contains(restaurant.getCity())) {
            throw new ValidationException("City does not exists in " + activeProfile);
        }
    }

    public void validateExistingThrow(Restaurant restaurant, boolean isItNew) {
        validate(restaurant, isItNew);
    }
}
