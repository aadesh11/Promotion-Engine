package net.engine.web.service.cart;

import net.engine.web.model.CartItem;

import java.util.List;

/**
 * This service will be used for all cart related operations.
 */
public interface CartService {

    /**
     * To get the total billing amount after applying all the promotions to the cart items.
     * @param cartItemList List of cart items.
     * @return total billing amount.
     */
    double getBillingAmount(List<CartItem> cartItemList);
}
