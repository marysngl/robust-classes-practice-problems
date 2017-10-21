package model;

import model.exceptions.NoCookException;
import model.exceptions.NoIngredientsException;
import model.exceptions.NotEnoughMoneyException;

public class Owner {

    private String name;
    private Kitchen kitchen;

    public Owner(String name, Kitchen kit) {
        this.name = name;
        kitchen = kit;
    }

    //getters
    public String getName() { return name; }
    public Kitchen getKitchen() { return kitchen; }

    // MODIFIES: this object's kitchen field
    // EFFECTS: invokes the makeTaco() method on this object's kitchen, returns true
    public boolean orderMoreTacos(int amount) throws NoCookException, NoIngredientsException {
        kitchen.makeTaco(amount);
        return true;
    }

    //MODIFIES: this object's kitchen field
    //EFFECTS: invokes the buyIngredients() method on this object's kitchen, returns true.
    public boolean askForMoreIngredients(int amount) throws NotEnoughMoneyException {
        this.kitchen.buyIngredients(amount);
        return true;
    }


}
