package net.engine.web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class SkuProduct {

    private String name;

    private double price;
}
