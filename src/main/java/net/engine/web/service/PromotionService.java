package net.engine.web.service;

import net.engine.web.model.CartItem;

import java.util.List;

public interface PromotionService {

    double getDiscount(List<CartItem> cartItems);
}
