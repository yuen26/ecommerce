package org.ashina.ecommerce.order.registry;

import org.ashina.ecommerce.order.application.command.handler.AddProductToCartCommandHandler;
import org.ashina.ecommerce.order.application.command.handler.DeleteCartLineCommandHandler;
import org.ashina.ecommerce.order.application.command.handler.UpdateCartLineCommandHandler;
import org.ashina.ecommerce.order.infrastructure.ecommerce.CatalogService;
import org.ashina.ecommerce.order.infrastructure.persistence.CartLinePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandHandlerRegistry {

    @Bean
    public AddProductToCartCommandHandler addProductToCartCommandHandler(CartLinePersistence cartLinePersistence,
                                                                         CatalogService catalogService) {
        return new AddProductToCartCommandHandler(cartLinePersistence, catalogService);
    }

    @Bean
    public UpdateCartLineCommandHandler updateCartLineCommandHandler(CartLinePersistence cartLinePersistence) {
        return new UpdateCartLineCommandHandler(cartLinePersistence);
    }

    @Bean
    public DeleteCartLineCommandHandler deleteCartLineCommandHandler(CartLinePersistence cartLinePersistence) {
        return new DeleteCartLineCommandHandler(cartLinePersistence);
    }
}