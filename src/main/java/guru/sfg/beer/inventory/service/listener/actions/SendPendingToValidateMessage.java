package guru.sfg.beer.inventory.service.listener.actions;

import guru.sfg.beer.inventory.service.config.jms.JmsConfig;
import guru.sfg.common.events.PendingToValidateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
public class SendPendingToValidateMessage {
    public static void action(JmsTemplate jmsTemplate, PendingToValidateEvent event) {
        log.info("Pending to validate for order id: "+ event.getBeerOrderDto().getId());
        jmsTemplate.convertAndSend(JmsConfig.PENDING_TO_VALIDATE_QUEUE, event);
    }
}
