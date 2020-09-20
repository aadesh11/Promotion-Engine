package net.engine.web.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@AllArgsConstructor
public class CartItem implements CodeIdentifier {

    private String productName;

    private double quantity;

    @Override
    public String code() {
        return productName;
    }
}
