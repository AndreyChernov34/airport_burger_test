package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

public class KitchenTest {

    private Kitchen kitchen;

    @BeforeEach
    public void kitchenClean(){
        kitchen = new Kitchen();
    }


    /**
     * Ситуация №1: Был запрошен на приготовление бургер, кухня успешно приготовила бургер
     * (бургер появился на столе готовой еды)
     */
    @Test
    public void cookBurgerTest() {
        DishType dishType = DishType.BURGER;

        kitchen.cook(dishType);
        Assertions.assertTrue(kitchen.getCompletedDishes().containsKey(dishType));
        Queue<Dish> dishesBurger = kitchen.getCompletedDishes().get(dishType);
        Assertions.assertFalse(dishesBurger.isEmpty());
        Assertions.assertEquals(1, dishesBurger.size());
    }

    /**
     *  Ситуация №2: Был запрошен на приготовление бургер, на кухне выключили газ,
     *  вылетела ошибка KitchenHasNoGasException
    */
    @Test
    public void KitchenHasNoGasExceptionTest() {
        DishType dishType = DishType.BURGER;
        kitchen.setHasGas(false);
        Assertions.assertThrows(KitchenHasNoGasException.class, ()-> kitchen.cook(dishType));
    }

    /**
     * Ситуация №3: Была запрошена на приготовление фуагра,
     * вылетела ошибка UnsupportedDishTypeException
    */
    @Test
    public void FuagraTest() {
        DishType dishType = DishType.FUAGRA;
        Assertions.assertThrows(UnsupportedDishTypeException.class, ()-> kitchen.cook(dishType));

    }

}
