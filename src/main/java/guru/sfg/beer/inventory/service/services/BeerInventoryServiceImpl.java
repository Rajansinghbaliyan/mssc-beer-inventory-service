package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.beer.inventory.service.web.mappers.BeerInventoryMapper;
import guru.sfg.beer.inventory.service.web.model.BeerInventoryDto;
import guru.sfg.beer.inventory.service.web.model.BeerInventoryPageList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerInventoryServiceImpl implements BeerInventoryService {
    private final BeerInventoryRepository repository;
    private final BeerInventoryMapper mapper;

    @Override
    public List<BeerInventoryDto> findAllBeerById(UUID id) {
        return repository.findAllByBeerId(id)
                .stream()
                .map(mapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeerInventoryDto> findAllByUpc(String upc) {
        return repository.findAllByUpc(upc).stream()
                .map(mapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public BeerInventoryPageList findAll(Pageable pageable) {
        Page<BeerInventory> beerInventoryPage = repository.findAll(pageable);

        return new BeerInventoryPageList(beerInventoryPage
                .getContent()
                .stream()
                .map(mapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList()),
                beerInventoryPage.getPageable(),
                beerInventoryPage.getTotalElements()
        );
    }

    @Override
    public BeerInventoryDto update(UUID beerIUd, BeerInventoryDto dto) {
        return null;
    }

    @Override
    public BeerInventoryDto save(BeerInventoryDto dto) {
        dto.setId(null);
        return mapper.beerInventoryToBeerInventoryDto(
                repository.save(mapper.beerInventoryDtoToBeerInventory(dto)));
    }

}
