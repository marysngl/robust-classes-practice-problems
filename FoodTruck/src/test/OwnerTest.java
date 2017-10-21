package test;

import model.Kitchen;
import model.Owner;
import model.exceptions.NoCookException;
import model.exceptions.NoIngredientsException;
import model.exceptions.NotEnoughMoneyException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OwnerTest {

    private Kitchen testKitchen;
    private Owner testOwner;

    @Before
    public void setUp() {
        testKitchen = new Kitchen(30, 4, 100, true);
        testOwner = new Owner("Paul Lockwood", testKitchen);
    }

    @Test
    public void testGetters() {
        assertEquals(testOwner.getName(), "Paul Lockwood");
        assertEquals(testOwner.getKitchen(), testKitchen);
    }

    @Test
    public void testOrderMoreTacos() {
        assertEquals(testOwner.getKitchen().getIngredientCount(),30);
        assertEquals(testOwner.getKitchen().getTacoCount(),4);

        try {
            testOwner.orderMoreTacos(10);
        } catch (NoCookException e) {
            fail("Caught NoCookException: The cook should be present in this case");
        } catch (NoIngredientsException e) {
            fail("Caught NoIngredientsException: There are enough ingredients by now");
        }

        assertEquals(testOwner.getKitchen().getTacoCount(),14);
        assertEquals(testOwner.getKitchen().getIngredientCount(),0);

        try {
            assertFalse(testOwner.orderMoreTacos(1));
        } catch (NoCookException e) {
            fail("Caught NoCookException: The cook should be present in this case");
        } catch (NoIngredientsException e) {
            System.out.println("Caught NoIngredientsException: This is correct behavior");
        }
    }

    @Test
    public void tesTaskForMoreIngredients() {
        assertEquals(testOwner.getKitchen().getIngredientCount(), 30);
        assertEquals(testOwner.getKitchen().getBalance(),100);

        try {
            assertTrue(testOwner.askForMoreIngredients(30));
        } catch (NotEnoughMoneyException e) {
            fail("Caught NotEnoughMoneyException: There should be enough money in this case");
        }

        assertEquals(testOwner.getKitchen().getBalance(),40);
        assertEquals(testOwner.getKitchen().getIngredientCount(),60);

        try {
            assertFalse(testOwner.askForMoreIngredients(100000));
        } catch (NotEnoughMoneyException e) {
            System.out.println("Caught NotEnoughMoneyException: This is correct behavior");
        }
    }


}