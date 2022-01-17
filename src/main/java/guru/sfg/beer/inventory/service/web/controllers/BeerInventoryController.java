package guru.sfg.beer.inventory.service.web.controllers;

import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.services.BeerInventoryService;
import guru.sfg.beer.inventory.service.web.model.BeerInventoryDto;
import guru.sfg.beer.inventory.service.web.model.BeerInventoryPageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt on 2019-05-31.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/beer")
public class BeerInventoryController {

    private final BeerInventoryService beerInventoryService;

    @GetMapping("/{beerId}/inventory")
    ResponseEntity<List<BeerInventoryDto>> listBeersById(@PathVariable UUID beerId) {
        log.debug("Finding Inventory for beerId:" + beerId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(beerInventoryService.findAllBeerById(beerId));
    }

    @GetMapping("/inventory")
    public ResponseEntity<BeerInventoryPageList> listAllBeerInventory(@RequestParam(defaultValue = "25") int pageSize,
                                                                      @RequestParam(defaultValue = "0") int pageNumber){
        log.info("Getting All Beer Inventory");
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(beerInventoryService.findAll(PageRequest.of(pageNumber,pageSize)));
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerInventoryDto> update(@PathVariable UUID beerId,
                                                   @RequestBody BeerInventoryDto dto){

        return null;
    }

    @PostMapping("/{beerId}/inventory")
    public ResponseEntity<BeerInventoryDto> save(@Validated @RequestBody BeerInventoryDto dto){
        log.info("Post for Beer Inventory for BeerId: " + dto.getBeerId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(beerInventoryService.save(dto));
    }
}
