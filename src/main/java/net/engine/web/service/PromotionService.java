package net.engine.web.service;

import net.engine.web.model.CartItem;
import net.engine.web.model.SkuProduct;

import java.util.List;
import java.util.Map;

public interface PromotionService {

    double getDiscount(Map<String, CartItem> cartItemMap, Map<String, SkuProduct> productMap);

    PromotionService savePromotion(List<CartItem> items, double discountPrice);

}
