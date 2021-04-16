package or.fasttrack.homework7.controller;

import com.github.fge.jsonpatch.JsonPatch;
import or.fasttrack.homework7.domain.Restaurant;
import or.fasttrack.homework7.model.RestaurantFilters;
import or.fasttrack.homework7.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {
    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Restaurant> getAll(RestaurantFilters filters,
                                   Pageable pageable) {
        return service.getAll(filters, pageable);
    }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return service.addRestaurant(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant replaceRestaurant(@RequestBody Restaurant restaurant, @PathVariable long id) {
        return service.replaceRestaurant(restaurant, id);
    }

    @PatchMapping("/{id}")
    public Restaurant patchRestaurant(@RequestBody JsonPatch patch, @PathVariable long id) {
        return service.patchRestaurant(patch,id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable long id){
        service.deleteById(id);
    }
}
