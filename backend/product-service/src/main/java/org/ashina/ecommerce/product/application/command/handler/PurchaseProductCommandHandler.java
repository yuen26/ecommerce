package org.ashina.ecommerce.product.application.command.handler;

import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ashina.ecommerce.product.application.command.model.PurchaseProductCommand;
import org.ashina.ecommerce.product.infrastructure.persistence.repository.ProductRepository;
import org.ashina.ecommerce.sharedkernel.command.handler.CommandHandler;
import org.checkerframework.checker.index.qual.SameLen;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PurchaseProductCommandHandler implements CommandHandler<PurchaseProductCommand, Void> {

    private final ProductRepository productRepository;

    @Override
    public Class<?> support() {
        return PurchaseProductCommand.class;
    }

    @Override
    @Transactional
    public Void handle(PurchaseProductCommand command) {
        UpdateResult updateResult = productRepository.purchaseProduct(command.getProductId(), command.getQuantity());
        log.debug("Purchase {}/{} items of product {}",
                updateResult.getModifiedCount(), command.getQuantity(), command.getProductId());
        return null;
    }
}