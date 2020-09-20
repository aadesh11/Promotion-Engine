package net.engine.web.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.engine.web.model.CartItem;
import net.engine.web.model.SkuProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Setter
@Getter
@Accessors(fluent = true)
public class ProductQuantityPromotion implements PromotionService {

    private List<CartItem> discountItems;

    //promotion discounted value.
    private double discountValue;

    @Override
    public double getDiscount(Map<String, CartItem> cartItems, Map<String, SkuProduct> productMap) {

        //check if promotion matches with any cart items by checking the product & quantity.
        if (discountItems.stream()
                .anyMatch(discountItem -> (!cartItems.containsKey(discountItem.code())))) {
            return 0.0d;
        }

        List<PromotionDiscount> promotionDiscounts = new ArrayList<>();
        for (CartItem discountItem : discountItems) {
            CartItem cart = cartItems.get(discountItem.code());
            double discountItemQuantity = discountItem.quantity();
            long iteration = 0;
            //get the product details.
            SkuProduct productDetails = productMap.get(discountItem.code());
            if (productDetails == null) {
                throw new RuntimeException("Product unit price not found");
            }
            while (discountItemQuantity <= cart.quantity()) {
                discountItemQuantity += discountItem.quantity();
                iteration++;
            }
            promotionDiscounts.add(new PromotionDiscount(iteration, discountItem.quantity(), productDetails.price()));
        }

        //if promotion is for single product present in cart items.
        if (promotionDiscounts.size() == 1) {
            PromotionDiscount dis = promotionDiscounts.get(0);
            /** calculate the total price for the items for which discount has been covered
             minus multiple * promotion discount value. **/
            return (dis.multiple * dis.perIndex * dis.unitPrice) - (dis.multiple * discountValue);
        }
        return 0.0d;

    }

    @AllArgsConstructor
    static class PromotionDiscount {

        private final long multiple;

        private final double perIndex;

        private final double unitPrice;

    }
}
