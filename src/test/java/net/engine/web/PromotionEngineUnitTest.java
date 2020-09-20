package net.engine.web;

import net.engine.web.model.CartItem;
import net.engine.web.model.SkuProduct;
import net.engine.web.service.CartService;
import net.engine.web.service.DefaultCartService;
import net.engine.web.service.ProductRepository;
import net.engine.web.service.PromotionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromotionEngineUnitTest {

    @Mock
    private PromotionService promotionService;

    @Mock
    private ProductRepository productRepository;


    @Test
    public void expectDiscount() {
        when(promotionService.getDiscount(anyMap(), anyMap())).thenReturn(200.0d);
        List<SkuProduct> products = new ArrayList<SkuProduct>() {{
            add(new SkuProduct("A", 300d));
        }};
        CartService cartService = new DefaultCartService(new ArrayList<PromotionService>() {{
            add(promotionService);
        }}, products);
        List<CartItem> cartItems = new ArrayList<CartItem>() {{
            add(new CartItem("A", 4));
        }};

        assertTrue("Amount should be 1000 Rs", cartService.getBillingAmount(cartItems) == 1000d);
    }

    @Test
    public void getProducts() {
        //To get products.
        when(productRepository.findAll()).thenReturn(new ArrayList<SkuProduct>() {{
            add(new SkuProduct("A", 50));
        }});

        assertEquals(productRepository.findAll().size(), 1);
    }
}
