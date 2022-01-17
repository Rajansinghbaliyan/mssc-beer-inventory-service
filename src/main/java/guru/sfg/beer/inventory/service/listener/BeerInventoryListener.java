package guru.sfg.beer.inventory.service.listener;

import guru.sfg.beer.inventory.service.config.jms.JmsConfig;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.beer.inventory.service.web.mappers.BeerInventoryMapper;
import guru.sfg.beer.inventory.service.web.model.BeerInventoryDto;
import guru.sfg.common.events.BeerInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BeerInventoryListener {
    private final BeerInventoryRepository repository;
    private final BeerInventoryMapper mapper;

    @JmsListener(destination = JmsConfig.BEER_INVENTORY_QUEUE)
    public void listener(BeerInventoryEvent event) {
        BeerInventoryDto dto = event.getBeerInventoryDto();
        log.info("Listener creating new Inventory for BeerId: " + dto.getBeerId());

        repository.save(mapper.beerInventoryDtoToBeerInventory(dto));
    }
}
