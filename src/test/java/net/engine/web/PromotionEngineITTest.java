package net.engine.web;

import net.engine.web.model.CartItem;
import net.engine.web.model.SkuProduct;
import net.engine.web.service.cart.CartService;
import net.engine.web.service.cart.DefaultCartService;
import net.engine.web.service.promotion.ProductQuantityPromotion;
import net.engine.web.service.promotion.PromotionService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class PromotionEngineITTest {

    //Scenario A
    @Test
    public void expectDiscountItA() {

        //Add products
        List<SkuProduct> products = new ArrayList<>();
        products.add(new SkuProduct("A", 50));
        products.add(new SkuProduct("B", 30));
        products.add(new SkuProduct("C", 20));
        products.add(new SkuProduct("D", 15));

        //prepare promotions
        PromotionService productAPromo = new ProductQuantityPromotion(new ArrayList<CartItem>() {{
            add(new CartItem("A", 3));
        }}, 130);
        ProductQuantityPromotion productBPromo = new ProductQuantityPromotion(new ArrayList<CartItem>() {{
            add(new CartItem("B", 2));
        }}, 45);
        ProductQuantityPromotion productCDPromo = new ProductQuantityPromotion(new ArrayList<CartItem>() {{
            add(new CartItem("C", 1));
            add(new CartItem("D", 1));
        }}, 30);

        List<PromotionService> promotions = new ArrayList<>();
        promotions.add(productAPromo);
        promotions.add(productBPromo);
        promotions.add(productCDPromo);

        //prepare cart
        List<CartItem> carts = new ArrayList<>();
        carts.add(new CartItem("A", 1));
        carts.add(new CartItem("B", 1));
        carts.add(new CartItem("C", 1));

        //get the final billing amount.
        CartService cartService = new DefaultCartService(promotions, products);
        assertTrue("Amount should be 1000 Rs", cartService.getBillingAmount(carts) == 100.0d);
    }

    //Scenario B
    @Test
    public void expectDiscountItB() {

        //Add products
        List<SkuProduct> products = new ArrayList<>();
        products.add(new SkuProduct("A", 50));
        products.add(new SkuProduct("B", 30));
        products.add(new SkuProduct("C", 20));
        products.add(new SkuProduct("D", 15));

        //prepare promotions
        PromotionService productAPromo = new ProductQuantityPromotion(new ArrayList<CartItem>() {{
            add(new CartItem("A", 3));
        }}, 130);
        ProductQuantityPromotion productBPromo = new ProductQuantityPromotion(new ArrayList<CartItem>() {{
            add(new CartItem("B", 2));
        }}, 45);
        ProductQuantityPromotion productCDPromo = new ProductQuantityPromotion(new ArrayList<CartItem>() {{
            add(new CartItem("C", 1));
            add(new CartItem("D", 1));
        }}, 30);

        List<PromotionService> promotions = new ArrayList<>();
        promotions.add(productAPromo);
        promotions.add(productBPromo);
        promotions.add(productCDPromo);

        //prepare cart
        List<CartItem> carts = new ArrayList<>();
        carts.add(new CartItem("A", 5));
        carts.add(new CartItem("B", 5));
        carts.add(new CartItem("C", 1));

        //get the final billing amount.
        CartService cartService = new DefaultCartService(promotions, products);
        assertTrue("Amount should be 1000 Rs", cartService.getBillingAmount(carts) == 370.0d);
    }

    //Scenario C
    @Test
    public void expectDiscountItC() {

        //Add products
        List<SkuProduct> products = new ArrayList<>();
        products.add(new SkuProduct("A", 50));
        products.add(new SkuProduct("B", 30));
        products.add(new SkuProduct("C", 20));
        products.add(new SkuProduct("D", 15));

        //prepare promotions
        PromotionService productAPromo = new ProductQuantityPromotion(new ArrayList<CartItem>() {{
            add(new CartItem("A", 3));
        }}, 130);
        ProductQuantityPromotion productBPromo = new ProductQuantityPromotion(new ArrayList<CartItem>() {{
            add(new CartItem("B", 2));
        }}, 45);
        ProductQuantityPromotion productCDPromo = new ProductQuantityPromotion(new ArrayList<CartItem>() {{
            add(new CartItem("C", 1));
            add(new CartItem("D", 1));
        }}, 30);

        List<PromotionService> promotions = new ArrayList<>();
        promotions.add(productAPromo);
        promotions.add(productBPromo);
        promotions.add(productCDPromo);

        //prepare cart
        List<CartItem> carts = new ArrayList<>();
        carts.add(new CartItem("A", 3));
        carts.add(new CartItem("B", 5));
        carts.add(new CartItem("C", 1));
        carts.add(new CartItem("D", 1));

        //get the final billing amount.
        CartService cartService = new DefaultCartService(promotions, products);
        assertTrue("Amount should be 1000 Rs", cartService.getBillingAmount(carts) == 280.0d);
    }
}
