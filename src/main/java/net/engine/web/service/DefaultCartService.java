package net.engine.web.service;

import lombok.AllArgsConstructor;
import net.engine.web.model.CartItem;
import net.engine.web.model.SkuProduct;

import java.util.List;

@AllArgsConstructor
public class DefaultCartService implements CartService {

    private final List<PromotionService> promotions;

    private final List<SkuProduct> skuProducts;

    @Override
    public double getBillingAmount(List<CartItem> cartItemList) {

        return 0;
    }
}
