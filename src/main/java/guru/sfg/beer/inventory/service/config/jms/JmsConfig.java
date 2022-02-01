package guru.sfg.beer.inventory.service.config.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    public static final String BEER_INVENTORY_QUEUE = "beer-inventory";
    public static final String BREW_MORE_BEER_QUEUE = "brew_more_beer_queue";
    public static final String PENDING_TO_VALIDATE_QUEUE = "pending_to_validate_queue";

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        converter.setTypeIdPropertyName("_type");
        converter.setTargetType(MessageType.TEXT);

        return converter;
    }
}
