package foodify.orchestrator.controllers;

import foodify.orchestrator.domain.orm.Establishment;
import foodify.orchestrator.domain.orm.MenuItem;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/foodify/v1/orchestrator")
public class OrchestratorController {

    private final RestTemplate restTemplate;

    public OrchestratorController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/menu-by-establishments")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        try {
            List<Establishment> establishments = fetchEstablishments();
            if (establishments == null) {
                return ResponseEntity.ok(Collections.emptyList());
            }

            List<MenuItem> menuItems = establishments.stream()
                    .flatMap(est -> Objects.requireNonNull(fetchMenuItems(est.getId())).stream())
                    .toList();

            return ResponseEntity.ok(menuItems);
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(Collections.emptyList());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }

    private List<Establishment> fetchEstablishments() {
        String url = "http://localhost:8081/foodify/v1/establishments-menu/all-establishments";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Establishment>>() {}).getBody();
    }

    private List<MenuItem> fetchMenuItems(String establishmentId) {
        String url = "http://localhost:8081/foodify/v1/establishments-menu/all-menu?id=" + establishmentId;
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MenuItem>>() {}).getBody();
    }
}
