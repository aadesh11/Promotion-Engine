package net.engine.web.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.engine.web.model.CartItem;
import net.engine.web.model.SkuProduct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .anyMatch(discountItem -> (!cartItems.containsKey(discountItem.code()))
                        || cartItems.get(discountItem.code()).quantity() < discountItem.quantity())) {
            return 0.0d;
        }

        List<PromotionDiscount> promotionDiscounts = discountItems.stream().map(discountItem -> {
            CartItem cart = cartItems.get(discountItem.code());
            double discountItemQuantity = discountItem.quantity();
            long iter = 0;
            //get the product details.
            SkuProduct productDetails = productMap.get(discountItem.code());
            if (productDetails == null) {
                throw new RuntimeException("Product unit price not found");
            }
            while (discountItemQuantity <= cart.quantity()) {
                discountItemQuantity += discountItem.quantity();
                iter++;
            }
            return new PromotionDiscount(iter, discountItem.quantity(), productDetails.price());
        }).collect(Collectors.toList());

        //if promotion is for single product present in cart items.
        if (promotionDiscounts.size() == 1) {
            PromotionDiscount dis = promotionDiscounts.get(0);
            /** calculate the total price for the items for which discount has been covered
             minus multiple * promotion discount value. **/
            return (dis.multiple * dis.perIndex * dis.unitPrice) - (dis.multiple * discountValue);
        }

        Optional<PromotionDiscount> minApplied = promotionDiscounts.stream()
                .min(Comparator.comparingLong(p -> p.multiple));

        if (minApplied.isPresent()) {
            PromotionDiscount min = minApplied.get();
            return promotionDiscounts.stream()
                    .mapToDouble(prm -> prm.unitPrice * min.multiple * prm.perIndex).sum()
                    - (discountValue * min.multiple);

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
