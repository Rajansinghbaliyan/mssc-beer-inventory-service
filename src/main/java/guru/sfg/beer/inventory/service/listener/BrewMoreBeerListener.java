package guru.sfg.beer.inventory.service.listener;

import guru.sfg.beer.inventory.service.config.jms.JmsConfig;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.listener.actions.SendPendingToValidateMessage;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.common.events.BrewMoreBeerEvent;
import guru.sfg.common.events.PendingToValidateEvent;
import guru.sfg.common.models.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BrewMoreBeerListener {

    private final BeerInventoryRepository beerInventoryRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREW_MORE_BEER_QUEUE)
    public void listener(BrewMoreBeerEvent event) {
        BeerOrderDto beerOrderDto = event.getBeerOrderDto();
        List<UUID> beerIdList = event.getBeersWithLessInventory();
        long noOfBeerCreated = beerIdList.parallelStream()
                .flatMap(beerId -> beerOrderDto.getBeerOrderLines().parallelStream()
                        .filter(beerOrderLineDto -> beerOrderLineDto.getBeerId().equals(beerId))
                        .map(beerOrderLineDto ->
                                beerInventoryRepository.save(
                                        BeerInventory.builder()
                                                .upc(beerOrderLineDto.getUpc())
                                                .quantityOnHand(beerOrderLineDto.getOrderQuantity())
                                                .beerId(beerOrderLineDto.getBeerId())
                                                .build()
                                )
                        )
                )
                .count();

        SendPendingToValidateMessage.action(
                jmsTemplate,
                PendingToValidateEvent.builder()
                        .beerOrderDto(beerOrderDto)
                        .build()
        );
    }
}
