package net.engine.web.service;

import net.engine.web.model.CartItem;

import java.util.List;

public class ProductQuantityPromotion implements PromotionService {

    @Override
    public double getDiscount(List<CartItem> cartItems) {
        return 0;
    }
}
