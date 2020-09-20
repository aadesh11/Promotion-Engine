package net.engine.web.service;

import net.engine.web.model.CartItem;
import net.engine.web.model.SkuProduct;

import java.util.Map;


public class ProductQuantityPromotion implements PromotionService {

    @Override
    public double getDiscount(Map<String, CartItem> cartItemMap, Map<String, SkuProduct> productMap) {
        return 0;
    }
}
