package foodify.establishment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import foodify.establishment.domain.orm.Establishment;
import foodify.establishment.domain.orm.MenuItem;
import foodify.establishment.services.EstablishmentService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/foodify/v1/establishments-menu")
public class EstablishmentController {

    private final EstablishmentService establishmentService;

    public EstablishmentController(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    @GetMapping("/all-establishments")
    public List<Establishment> getAllEstablishments() {
        return establishmentService.getAllEstablishments();
    }

    @GetMapping("/one-establishment")
    public ResponseEntity<Establishment> getEstablishmentById(@RequestParam String id) {
        Establishment establishment = establishmentService.getEstablishmentById(id);
        return ResponseEntity.ok(establishment);
    }

    @PostMapping("/create-establishment")
    public ResponseEntity<Establishment> createEstablishment(@RequestBody Establishment establishment) {
        Establishment createdEstablishment = establishmentService.createEstablishment(establishment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEstablishment);
    }

    @PatchMapping("/update-establishment")
    public ResponseEntity<Establishment> updateEstablishment(@RequestParam String id, @RequestBody Establishment establishmentDetails) {
        Establishment updatedEstablishment = establishmentService.updateEstablishment(id, establishmentDetails);
        return ResponseEntity.ok(updatedEstablishment);
    }

    @DeleteMapping("/delete-establishment")
    public ResponseEntity<Map<String, Boolean>> deleteEstablishment(@RequestParam String id) {
        establishmentService.deleteEstablishment(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-menu")
    public ResponseEntity<List<MenuItem>> getMenu(@RequestParam String id) {
        List<MenuItem> menuItems = establishmentService.getMenu(id);
        return ResponseEntity.ok(menuItems);
    }

    @PostMapping("/create-menu")
    public ResponseEntity<MenuItem> addMenuItem(@RequestParam String id, @RequestBody MenuItem menuItem) {
        MenuItem createdItem = establishmentService.addMenuItem(id, menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @PatchMapping("/update-menu")
    public ResponseEntity<MenuItem> updateMenuItem(@RequestParam String id, @RequestParam String itemId, @RequestBody MenuItem menuItemDetails) {
        MenuItem updatedItem = establishmentService.updateMenuItem(itemId, menuItemDetails);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/delete-menu")
    public ResponseEntity<Map<String, Boolean>> deleteMenuItem(@RequestParam String id, @RequestParam String itemId) {
        establishmentService.deleteMenuItem(id, itemId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
