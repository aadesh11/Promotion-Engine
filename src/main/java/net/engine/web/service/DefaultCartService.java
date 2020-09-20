package net.engine.web.service;

import net.engine.web.model.CartItem;
import net.engine.web.model.SkuProduct;
import net.engine.web.utils.ConverterUtils;

import java.util.List;
import java.util.Map;

public class DefaultCartService implements CartService {

    private final List<PromotionService> promotions;

    private final Map<String, SkuProduct> productsMap;

    public DefaultCartService(List<PromotionService> promotions, List<SkuProduct> products) {
        this.promotions = promotions;
        this.productsMap = ConverterUtils.convert(products);
    }

    @Override
    public double getBillingAmount(List<CartItem> cartItemList) {
        double discountedValue = 0;

        //get discounted amount.
        for(PromotionService promotion : promotions) {
            discountedValue += promotion.getDiscount(cartItemList);
        }

        //return the billing amount after subtracting total cart price with discounted value
        return cartItemList.stream()
                .mapToDouble(cartItem -> productsMap.get(cartItem.code()).price() * cartItem.quantity())
                .sum() - discountedValue;
    }
}
