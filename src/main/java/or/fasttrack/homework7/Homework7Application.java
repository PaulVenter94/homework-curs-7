package or.fasttrack.homework7;

import or.fasttrack.homework7.domain.Restaurant;
import or.fasttrack.homework7.repository.RestaurantRepo;
import or.fasttrack.homework7.service.RestaurantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Homework7Application implements CommandLineRunner {
    private final RestaurantService service;

    public Homework7Application(RestaurantService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Homework7Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.addRestaurant(new Restaurant(1, "Alegria", 1, "Oradea", LocalDate.of(2017, 3, 1)));
        service.addRestaurant(new Restaurant(2, "Klausenburg", 2, "Cluj", LocalDate.of(2013, 5, 1)));
        service.addRestaurant(new Restaurant(3, "Mirazur", 5, "Arad", LocalDate.of(2001, 7, 1)));
        service.addRestaurant(new Restaurant(4, "Arpege", 5, "Oradea", LocalDate.of(1995, 8, 1)));
        service.addRestaurant(new Restaurant(5, "Den", 4, "Cluj", LocalDate.of(2010, 3, 1)));

    }
}
