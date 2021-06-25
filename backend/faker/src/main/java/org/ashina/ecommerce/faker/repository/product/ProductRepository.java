package org.ashina.ecommerce.faker.repository.product;

import org.ashina.ecommerce.faker.model.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByPriceBetween(int startPrice, int endPrice);
}
