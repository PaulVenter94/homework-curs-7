package or.fasttrack.homework7.repository;

import or.fasttrack.homework7.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findAllByCityAndStarsIn(String city, List<Integer> stars, Pageable pageable);

    Page<Restaurant> findByCity(String city, Pageable pageable);

    Page<Restaurant> findByStarsIn(List<Integer> stars, Pageable pageable);

    Boolean existsByName(String name);
}
