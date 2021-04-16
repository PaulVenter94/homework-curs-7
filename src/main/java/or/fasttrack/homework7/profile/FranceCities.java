package or.fasttrack.homework7.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Profile("France")
public class FranceCities implements CitiesProfile {
    @Override
    public List<String> getCities() {
        return List.of("Paris", "Menton", "Marseille", "Lyon");
    }
}
