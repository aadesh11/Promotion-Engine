package net.engine.web.service;

import net.engine.web.model.CartItem;

public interface PromotionService {

    double getDiscount(CartItem cartItem);
}
