package com.javaacademy.burger;

import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class WaitressTest {
    private Kitchen kitchen;
    private Waitress waitress;

    @BeforeEach
    public void waitressClean() {
        waitress = new Waitress();
        kitchen = mock(Kitchen.class);
    }

    /**
     * 3. Написать тесты, которые проверяют работу официанта:
     * При каждодневной проверке нужно чтобы мы не заставляли реально готовить кухню (дорого просто так еду готовить!),
     * поэтому кухня должна быть фальшивая.
     * Ситуация №1: Был запрошен бургер, официант принял заказ
     */

    @Test
    public void waitressBurgerTest() {
      boolean result = waitress.giveOrderToKitchen(DishType.BURGER, kitchen);
        assertTrue(result);
    }

    /**
     *  Ситуация №2: Была запрошена фуагра, официант не принял заказ
     */

    @Test
    public void waitressFuagraTest() {
        boolean result = waitress.giveOrderToKitchen(DishType.FUAGRA, kitchen);
        assertFalse(result);
    }

}
