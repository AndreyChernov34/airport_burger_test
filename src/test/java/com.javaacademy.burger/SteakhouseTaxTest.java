package com.javaacademy.burger;

import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


public class SteakhouseTaxTest {
    /**
     * Gришла проверка из налоговой, хочет посмотреть на то, что все заказы проходят через терминал
     * (вдруг скрыли какие то заказы от налоговой). Необходимо написать тесты, которые проверяют работу ресторана,
     * но кухня и официант на самом деле работать не будут, только терминал. При этом в налоговой попросили
     * не делать запросы в банк на расчет валюты (сказали поставить 1 у.е.):
     * Ситуация №1: клиент захотел купить ребра за рубли. Заказал ребра, получил чек(сумма - 700, тип заказа - ребра,
     * валюта - рубли).     *
     *
     */
    private Kitchen kitchen;
    private Waitress waitress;
    private PayTerminal payTerminal;
    private Steakhouse steakhouse;
    // условная еденица
    private static final BigDecimal UNIT = BigDecimal.ONE;

    @BeforeEach
    public void setUp() {
        kitchen = Mockito.mock(Kitchen.class);
        waitress = Mockito.mock(Waitress.class);
        payTerminal = Mockito.spy(new PayTerminal());
        steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        Mockito.when(waitress.giveOrderToKitchen(any(DishType.class), any(Kitchen.class))).thenReturn(true);

    }

    /**
     * Ситуация №1: клиент захотел купить ребра за рубли. Заказал ребра, получил чек(сумма - 700, тип заказа - ребра,
     * валюта - рубли).
     */
    @Test
    public void TaxRibsTest() {
        DishType dishType = DishType.RIBS;
        Currency currency = Currency.RUB;

        Paycheck resultPayCheck = steakhouse.makeOrder(dishType, currency);
        Paycheck expectedPayCheck = new Paycheck(dishType.getPrice(), currency, dishType);

        Assertions.assertEquals(expectedPayCheck, resultPayCheck);
    }

    /**
     *  Ситуация №2: клиент захотел купить картошку за доллары. Заказал картошку, получил чек(1, картошка, доллар)
     */
    @Test
    public void TaxPotaroTest() {
        DishType dishType = DishType.FRIED_POTATO;
        Currency currency = Currency.USD;

        Mockito.doReturn(new Paycheck(UNIT, currency, dishType)).
                when(payTerminal).pay(any(DishType.class), eq(currency));

        Paycheck expectedPayCheck = new Paycheck(UNIT, currency, dishType);

        Paycheck resultPayCheck = steakhouse.makeOrder(dishType, currency);

        Assertions.assertEquals(expectedPayCheck, resultPayCheck);
    }

    /**
     * Ситуация №3: клиент захотел купить бургер за мозамбикские доллары. Заказал бургер, получил чек(1, бургер,
     *      мозамбикский доллар)
     */
    @Test
    public void TaxMozambicTest() {

        DishType dishType = DishType.BURGER;
        Currency currency = Currency.MOZAMBICAN_DOLLARS;

        Mockito.doReturn(new Paycheck(UNIT, currency, dishType)).
                when(payTerminal).pay(any(DishType.class), eq(currency));

        Paycheck expectedPayCheck = new Paycheck(UNIT, currency, dishType);

        Paycheck resultPayCheck = steakhouse.makeOrder(dishType, currency);

        Assertions.assertEquals(expectedPayCheck, resultPayCheck);
    }

}
