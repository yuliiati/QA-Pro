import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostTest {    // HW: параметризированные и не пар. тесты 2х2 - 2 валидных, 2 невалидных

    @Test
    void deliveryCostCalc() {
        Cost cost = new Cost();
        assertEquals(400,
        cost.deliveryCostCalc(2, true, false, "Высокая"));
    }

    @Test
    void deliveryCostCalc1() {
        Cost cost = new Cost();
        assertEquals(980,
                cost.deliveryCostCalc(30, true, true, "Высокая"));
    }

    // HW 04.04 CostCalcTests

    @Test
    void testDeliveryCostCalcPositive1() {
        Cost cost = new Cost();
        int expectedCost = 150;
        double actualCost = cost.deliveryCostCalc(1, false, false, "Низкая");
        assertEquals(expectedCost, actualCost);
    }
    // Проверка расчета стоимости доставки для заказа весом 1 кг, без доп. опций и с низким уровнем сложности местности.
    // Ожидаемый рез-т 150.

    @Test
    void testDeliveryCostCalcPositive2() {
        Cost cost = new Cost();
        int expectedCost = 800;
        double actualCost = cost.deliveryCostCalc(10, true, true, "Высокая");
        assertEquals(expectedCost, actualCost);
    }
    // доставки для заказа весом 10 кг, с дополнительными опциями "страховка" и "возврат", и высоким уровнем сложности местности.
    // Ожидаемый рез-т - 800.

    @Test
    void testDeliveryCostCalcNegative1() {
        Cost cost = new Cost();
        assertThrows(IllegalArgumentException.class, () -> {
            cost.deliveryCostCalc(-1, true, false, "Средняя");
        });
    }
   // Проверка обработки некорректного входного параметра веса (отрицательное значение).
    // Ожидаемое исключение - IllegalArgumentException.

    @Test
    void testDeliveryCostCalcNegative2() {
        Cost cost = new Cost();
        assertThrows(IllegalArgumentException.class, () -> {
            cost.deliveryCostCalc(0, false, true, "Высокая");
        });
    }
// Проверка обработки некорректного входного параметра веса (нулевое значение) и дополнительной опции "возврат".
// Ожидаемое исключение - IllegalArgumentException.
}