package or.fasttrack.homework7.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("Japan")
public class JapanCities implements CitiesProfile {
    @Override
    public List<String> getCities() {
        return List.of("Tokyo", "Kyoto", "Hiroshima", "Nagasaki");
    }
}
