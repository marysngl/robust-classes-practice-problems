package model;

import exceptions.IllegalAgeException;
import exceptions.IllegalAmountException;
import exceptions.MissingFareException;
import exceptions.NoBalanceException;

public class BusFareCard {

    private static final int AGE_CUTOFF = 18;
    public static final double ADULT_FARE = 2.75;
    public static final double CONCESSION_FARE = 1.75;
    private String ownerName;
    private int ownerAge;
    private double balance;
    private boolean fareLoaded;

    public BusFareCard(String nm, int age, double initialLoad) throws IllegalAmountException {
        if (initialLoad < 0) {
            throw new IllegalAmountException("$" + initialLoad + " is not a valid amount to load onto your card.");
        } else {
            ownerName = nm;
            ownerAge = age;
            balance = initialLoad;
            fareLoaded = false;
        }
    }

    // getters
    public String getName() { return ownerName; }
    public int getAge() { return ownerAge; }
    public double getBalance() { return balance; }
    public boolean isFareLoaded() { return fareLoaded; }

    // MODIFIES: this
    // EFFECTS: if the REQUIRES clause is fulfilled, subtract cost of the adult fare from the balance
    //          and set the isFareLoaded field to true
    public void purchaseAdultFare() throws IllegalAgeException, NoBalanceException {
        if (getAge() <= AGE_CUTOFF) {
            throw new IllegalAgeException("The owner's age is invalid");
        }
        if (getBalance() < ADULT_FARE) {
            throw new NoBalanceException("Not enough funds to purchase an adult ticket");
        }
        balance -= ADULT_FARE;
        fareLoaded = true;
    }

    // MODIFIES: this
    // EFFECTS: if the REQUIRES clauses is fulfilled, subtract cost of a concession fare from the balance
    //          and set the isFareLoaded field to true
    public void purchaseConcessionTicket() throws IllegalAgeException, NoBalanceException {
        if (getAge() > AGE_CUTOFF) {
            throw new IllegalAgeException("The owner's age is invalid");
        }
        if (getBalance() < CONCESSION_FARE) {
            throw new NoBalanceException("Not enough funds to purchase a concession ticket");
        }
        balance -= CONCESSION_FARE;
        fareLoaded = true;
    }

    // MODIFIES: this
    // EFFECTS: loads the specified amount onto the card's balance field
    public void reloadBalance(double amount) throws IllegalAmountException {
        if (amount <= 0) {
            throw new IllegalAmountException("The loaded amount should be more than zero");
        }
        balance = amount;
    }

    // MODIFIES: this
    // EFFECTS: sets fareLoaded field to false, else throws exception
    public void boardBus() throws MissingFareException {
        if (!fareLoaded) {
            throw new MissingFareException("No fare! You shall not pass!");
        }
        fareLoaded = false;
    }


}