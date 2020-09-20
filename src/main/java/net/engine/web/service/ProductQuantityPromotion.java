package net.engine.web.service;

import net.engine.web.model.CartItem;

public class ProductQuantityPromotion implements PromotionService {

    @Override
    public double getDiscount(CartItem cartItem) {
        return 0;
    }
}
