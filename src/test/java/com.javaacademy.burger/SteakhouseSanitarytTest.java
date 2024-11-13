package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class SteakhouseSanitarytTest {
    private Kitchen kitchen;
    private Waitress waitress;
    private PayTerminal payTerminal;
    private Steakhouse steakhouse;

    @BeforeEach
    public void setUp() {
        kitchen = new Kitchen();
        waitress = new Waitress();
        payTerminal = Mockito.mock(PayTerminal.class);
        steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
    }
    /**
     * Пришла проверка из санэпидемстанции, хочет проверить качество еды. Написать тесты, которые проверяют
     * работу ресторана, но никакой оплаты от санэпидемстанции мы конечно же не дождемся, поэтому настоящий терминал
     * оплаты не должен работать:
     * Ситуация №1: клиент захотел купить ребра за рубли. Заказал ребра, получил чек(700 руб, ребра, рубли),
     * подошел за заказом, получил ребра.
     */

    @Test
    public void SanitaryTest() {
        DishType dishType = DishType.RIBS;
        Currency currency = Currency.RUB;

        Mockito.when(payTerminal.pay(dishType, currency)).
                thenReturn(new Paycheck(dishType.getPrice(), currency, dishType));

        Paycheck resultPayCheck = steakhouse.makeOrder(dishType, currency);
        Paycheck expectedPayCheck = new Paycheck(dishType.getPrice(), currency, dishType);

        Assertions.assertEquals(expectedPayCheck, resultPayCheck);

        Dish resultdish = steakhouse.takeOrder(resultPayCheck);
        Dish expectedDish = new Dish(dishType);

        Assertions.assertEquals(expectedDish, resultdish);
    }

}
