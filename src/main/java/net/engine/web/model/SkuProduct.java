package net.engine.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * SKU product details.
 */
@Getter
@Setter
@Accessors(fluent = true)
@AllArgsConstructor
public class SkuProduct implements CodeIdentifier {

    private String name;

    private double price;

    @Override
    public String code() {
        return name;
    }
}
