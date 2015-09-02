package com.epages.microservice.handson.order.orderstatus;

import com.epages.microservice.handson.order.Order;
import com.epages.microservice.handson.order.OrderService;
import com.epages.microservice.handson.order.OrderStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class DeliveryOrderReceivedEventSubscriber extends OrderStatusEventSubscriber {

    private static final String DELIVERY_ORDER_RECEIVED_EVENT_TYPE = "DeliveryOrderReceived";

    @Autowired
    public DeliveryOrderReceivedEventSubscriber(ObjectMapper objectMapper, OrderService orderService) {
        super(orderService, objectMapper, DELIVERY_ORDER_RECEIVED_EVENT_TYPE, OrderStatus.BAKING);
    }

    @Override
    protected void enhanceOrder(Order order, Map<String, Object> payload) {
        LocalDateTime estimatedTimeOfDelivery = LocalDateTime.parse(((String) payload.get("estimatedTimeOfDelivery")));
        order.setEstimatedTimeOfDelivery(estimatedTimeOfDelivery);
    }
}
