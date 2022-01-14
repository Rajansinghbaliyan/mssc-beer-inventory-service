package guru.sfg.beer.inventory.service.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerInventoryPageList extends PageImpl<BeerInventoryDto> {
    public BeerInventoryPageList(List<BeerInventoryDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerInventoryPageList(List<BeerInventoryDto> content) {
        super(content);
    }
}
