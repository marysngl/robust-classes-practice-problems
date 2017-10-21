package model;

import model.exceptions.NoCookException;
import model.exceptions.NoIngredientsException;
import model.exceptions.NotEnoughMoneyException;

public class Kitchen {

    private final static int INGREDIENT_PER_TACO = 3;
    private final static int DOLLAR_PER_INGREDIENT = 2;
    private int ingredient;
    private int tacoCount;
    private int balance;
    private boolean cookReady;

    public Kitchen(int initialIngredient, int initialTaco, int balance, boolean cookStatus) {
        ingredient = initialIngredient;
        tacoCount = initialTaco;
        cookReady = cookStatus;
        this.balance = balance;
    }

    // getters
    public int getIngredientCount() { return ingredient; }
    public int getTacoCount() { return tacoCount; }
    public boolean getCookState() { return cookReady; }
    public int getBalance() { return balance; }

    public void setCookStatus(boolean b) {
        cookReady = b;
    }

    // MODIFIES: this
    // EFFECTS:  number is added to tacoCount, and ingredient is decremented accordingly
    public void makeTaco(int number) throws NoCookException, NoIngredientsException {
        if (!cookReady) {
            throw new NoCookException("The cook is not present!");
        }
        if (ingredient < INGREDIENT_PER_TACO * number) {
            throw new NoIngredientsException("Not enough ingredients to make all the tacos!");
        }
        ingredient -= (INGREDIENT_PER_TACO * number);
        tacoCount += number;
    }

    // MODIFIES: this
    // EFFECTS: (amount) is added to the ingredient field, and the balance field
    //          is decremented accordingly
    public void buyIngredients(int amount) throws NotEnoughMoneyException {
        if (balance < DOLLAR_PER_INGREDIENT * amount) {
            throw new NotEnoughMoneyException("We're broke!");
        }
        balance -= (DOLLAR_PER_INGREDIENT * amount);
        ingredient += amount;
    }

  



}
