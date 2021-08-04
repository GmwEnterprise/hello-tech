package com.github.mrag.types;

import com.github.mrag.types.errortype.NotFoundException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public enum Item {
    iPhone(1, "8699"),
    mate40(2, "4999"),
    matebook14(3, "6399"),
    matebook13(4, "5899");

    @Getter
    private final int id;

    @Getter
    private final Money itemPrice;

    Item(int id, String itemPrice) {
        this.id = id;
        this.itemPrice = new Money(itemPrice);
    }

    public static Item findItem(String name) {
        return Arrays.stream(values()).filter(item -> Objects.equals(name, item.name())).findFirst()
                     .orElseThrow(() -> new NotFoundException("未找到对应商品: name = " + name));
    }

    public static Item findItem(int itemId) {
        return Arrays.stream(values()).filter(item -> itemId == item.id).findFirst()
                     .orElseThrow(() -> new NotFoundException("未找到对应商品: id = " + itemId));
    }

    public static Item findNullableItem(String name) {
        return Arrays.stream(values()).filter(item -> Objects.equals(name, item.name()))
                     .findFirst().orElse(null);
    }

    public static Item findNullableItem(int itemId) {
        return Arrays.stream(values()).filter(item -> itemId == item.id)
                     .findFirst().orElse(null);
    }
}
