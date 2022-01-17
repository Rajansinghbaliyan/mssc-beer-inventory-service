package guru.sfg.common.events;

import guru.sfg.beer.inventory.service.web.model.BeerInventoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerInventoryEvent {
    private BeerInventoryDto beerInventoryDto;
}
