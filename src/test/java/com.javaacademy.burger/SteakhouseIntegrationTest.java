package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class SteakhouseIntegrationTest {
    private Kitchen kitchen;
    private Waitress waitress;
    private PayTerminal payTerminal;
    private Steakhouse steakhouse;

    @BeforeEach
    public void setUp() {
        kitchen = new Kitchen();
        waitress = new Waitress();
        payTerminal = new PayTerminal();
        steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
    }
    /**
     * Ситуация №1: клиент захотел купить бургер за рубли. Заказал бургер, получил чек(300 руб, бургер, рубли),
     * подошел за заказом, получил бургер. Проверить чек, проверить блюдо(что это именно бургер!).
     */

    @Test
    public void RestourantTest() {
        DishType dishType = DishType.BURGER;
        Currency currency = Currency.RUB;

        Paycheck resultPayCheck = steakhouse.makeOrder(dishType, currency);
        Paycheck expectedPayCheck = new Paycheck(dishType.getPrice(), currency, dishType);

        Assertions.assertEquals(expectedPayCheck, resultPayCheck);

        Dish resultdish = steakhouse.takeOrder(resultPayCheck);
        Dish expectedDish = new Dish(dishType);

        Assertions.assertEquals(expectedDish, resultdish);
    }

}
