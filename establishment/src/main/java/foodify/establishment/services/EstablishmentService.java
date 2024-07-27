package foodify.establishment.services;

import foodify.establishment.domain.orm.Establishment;
import foodify.establishment.domain.orm.MenuItem;
import foodify.establishment.exceptions.ResourceNotFoundException;
import foodify.establishment.repository.EstablishmentRepository;
import foodify.establishment.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;
    private final MenuItemRepository menuItemRepository;

    public EstablishmentService(EstablishmentRepository establishmentRepository, MenuItemRepository menuItemRepository) {
        this.establishmentRepository = establishmentRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Establishment> getAllEstablishments() {
        return establishmentRepository.findAll();
    }

    public Establishment getEstablishmentById(String id) {
        return establishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Establishment not found for this id :: " + id));
    }

    public Establishment createEstablishment(Establishment establishment) {
        return establishmentRepository.save(establishment);
    }

    public Establishment updateEstablishment(String id, Establishment establishmentDetails) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Establishment not found for this id :: " + id));

        establishment.setName(establishmentDetails.getName());
        establishment.setAddress(establishmentDetails.getAddress());

        return establishmentRepository.save(establishment);
    }

    public void deleteEstablishment(String id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Establishment not found for this id :: " + id));
        establishmentRepository.delete(establishment);
    }

    public List<MenuItem> getMenu(String establishmentId) {
        Establishment establishment = getEstablishmentById(establishmentId);
        return menuItemRepository.findByIdIn(establishment.getMenuItems());
    }

    public MenuItem addMenuItem(String establishmentId, MenuItem menuItem) {
        Establishment establishment = getEstablishmentById(establishmentId);
        menuItem.setEstablishmentId(establishmentId);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        establishment.getMenuItems().add(savedMenuItem.getId());
        establishmentRepository.save(establishment);
        return savedMenuItem;
    }

    public MenuItem updateMenuItem(String itemId, MenuItem menuItemDetails) {
        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found for this id :: " + itemId));

        menuItem.setName(menuItemDetails.getName());
        menuItem.setDescription(menuItemDetails.getDescription());
        menuItem.setPrice(menuItemDetails.getPrice());

        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(String establishmentId, String itemId) {
        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found for this id :: " + itemId));
        menuItemRepository.delete(menuItem);

        Establishment establishment = getEstablishmentById(establishmentId);
        establishment.getMenuItems().remove(itemId);
        establishmentRepository.save(establishment);
    }
}
