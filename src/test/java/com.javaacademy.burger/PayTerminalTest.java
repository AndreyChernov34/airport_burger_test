package com.javaacademy.burger;

import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@Slf4j
public class PayTerminalTest {
    private PayTerminal payTerminal;

    @BeforeEach
    public void PayTerminalClean() {
        payTerminal = new PayTerminal();
    }

    /**
     * Написать тесты, которые проверяют работу терминала оплаты:
     * Ситуация №1: На оплату поступил бургер, оплата в рублях.
     * Вернулся чек с оплатой в котором указано: 300 рублей, валюта - рубль, товар - бургер.
     */

    @Test
    public void payTerminalBurgerTest() {
        Paycheck resultpayCheck = payTerminal.pay(DishType.BURGER, Currency.RUB);
        Paycheck expendedPayCheck = new Paycheck(BigDecimal.valueOf(300), Currency.RUB, DishType.BURGER);
        Assertions.assertEquals(expendedPayCheck, resultpayCheck);
    }

    /**
     *  Ситуация №2: На оплату поступил бургер, оплата в мозамбикских долларах,
     *  вылетела ошибка NotAcceptedCurrencyException
     *
     */
    @Test
    public void payTerminalMozambicanDollarsTest() {
        DishType dishType = DishType.BURGER;
        Currency currency = Currency.MOZAMBICAN_DOLLARS;

        Assertions.assertThrows(NotAcceptedCurrencyException.class, () -> {
            Paycheck resultpayCheck = payTerminal.pay(dishType, currency);
            });
    }
}
