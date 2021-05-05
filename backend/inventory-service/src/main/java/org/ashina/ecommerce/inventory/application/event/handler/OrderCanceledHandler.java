package org.ashina.ecommerce.inventory.application.event.handler;

import lombok.RequiredArgsConstructor;
import org.ashina.ecommerce.inventory.domain.Stock;
import org.ashina.ecommerce.inventory.infrastructure.persistence.StockPersistence;
import org.ashina.ecommerce.sharedkernel.domain.DomainException;
import org.ashina.ecommerce.sharedkernel.event.handler.DomainEventHandler;
import org.ashina.ecommerce.sharedkernel.event.model.order.OrderCanceled;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderCanceledHandler implements DomainEventHandler<OrderCanceled> {

    private final StockPersistence stockPersistence;

    @Override
    public void handle(OrderCanceled event) throws DomainException {
        Map<String, Integer> lineMap = event.getLines()
                .stream()
                .collect(Collectors.toMap(OrderCanceled.Line::getProductId, OrderCanceled.Line::getQuantity));
        Set<String> productIds = lineMap.keySet();
        List<Stock> stocks = stockPersistence.findByProductIdIn(productIds);
        stocks.forEach(stock -> stock.setQuantity(stock.getQuantity() + lineMap.get(stock.getProductId())));
        stockPersistence.saveAll(stocks);
    }
}