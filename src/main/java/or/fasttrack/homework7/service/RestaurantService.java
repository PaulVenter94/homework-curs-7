package or.fasttrack.homework7.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import or.fasttrack.homework7.domain.Restaurant;
import or.fasttrack.homework7.exception.ResourceNotFoundException;
import or.fasttrack.homework7.model.RestaurantFilters;
import or.fasttrack.homework7.repository.RestaurantRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepo repo;
    private final ObjectMapper mapper;
    private final RestaurantValidator validator;


    public Page<Restaurant> getAll(RestaurantFilters filters, Pageable pageable) {
        if (filters.getCity() != null && !isEmpty(filters.getStars())) {
            return repo.findAllByCityAndStarsIn(filters.getCity(), filters.getStars(), pageable);
        } else if (isEmpty(filters.getStars()) && filters.getCity() != null) {
            return repo.findByCity(filters.getCity(), pageable);
        } else if (!isEmpty(filters.getStars())) {
            return repo.findByStarsIn(filters.getStars(), pageable);
        }
        return repo.findAll(pageable);
    }

    public Restaurant getById(long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find product with id " + id));
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        validator.validateNewThrow(restaurant);
        restaurant.setId(0);
        return repo.save(restaurant);
    }

    public Restaurant replaceRestaurant(Restaurant restaurant, long id) {
        validator.validateExistingThrow(restaurant,false);
        restaurant.setId(id);
        return repo.save(restaurant);
    }

    @SneakyThrows
    public Restaurant patchRestaurant(JsonPatch patch, long id) {
        Restaurant restaurant = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find product with id:" + id));

        JsonNode patchedRestaurantJson = patch.apply(mapper.valueToTree(restaurant));
        Restaurant patchedRestaurant = mapper.treeToValue(patchedRestaurantJson, Restaurant.class);
        return replaceRestaurant(patchedRestaurant, id);
    }

    public void deleteById(long id) {
        repo.deleteById(id);
    }
}
