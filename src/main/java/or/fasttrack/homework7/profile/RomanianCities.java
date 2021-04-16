package or.fasttrack.homework7.profile;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
@Profile("Romania")
public class RomanianCities implements CitiesProfile {
    @Override
    public List<String> getCities() {
        return List.of("Oradea", "Cluj", "Arad", "Timisoara");
    }
}
