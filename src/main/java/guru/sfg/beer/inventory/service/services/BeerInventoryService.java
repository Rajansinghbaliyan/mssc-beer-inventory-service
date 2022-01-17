package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.inventory.service.web.model.BeerInventoryDto;
import guru.sfg.beer.inventory.service.web.model.BeerInventoryPageList;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BeerInventoryService {
    List<BeerInventoryDto> findAllBeerById(UUID beerId);
    List<BeerInventoryDto> findAllByUpc(String beerUpc);
    BeerInventoryPageList findAll(Pageable pageable);

    BeerInventoryDto update(UUID beerInventoryId, BeerInventoryDto dto);

    BeerInventoryDto save(BeerInventoryDto dto);

}
