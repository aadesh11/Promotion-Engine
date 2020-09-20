package net.engine.web.service.promotion;

import net.engine.web.model.CartItem;
import net.engine.web.model.SkuProduct;

import java.util.List;
import java.util.Map;

/**
 *  All promotions types should implement it.
 */
public interface PromotionService {

    /**
     * To get the discounted value after applying promotions to cartItems.
     * @param cartItemMap Map of product code vs cart item.
     * @param productMap Map of product code vs product details.
     * @return discounted price.
     */
    double getDiscount(Map<String, CartItem> cartItemMap, Map<String, SkuProduct> productMap);

    /**
     * To save the promotions in database.
     * @param items items details.
     * @param discountPrice discount value.
     * @return PromotionService.
     */
    PromotionService savePromotion(List<CartItem> items, double discountPrice);

}
