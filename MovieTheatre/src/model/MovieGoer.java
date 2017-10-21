package model;

import exceptions.ShowingFullException;
import exceptions.UnderAgeException;

public class MovieGoer {

    private String name;
    private int age;
    private Ticket ticket;
    private TicketKiosk tk;

    public MovieGoer(String name, int age,  TicketKiosk tk) {
        this.name = name;
        this.age = age;
        this.tk = tk;
        ticket = null;
    }

    // getters
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public TicketKiosk getTicketKiosk() {
        return tk;
    }
    public Ticket getTicket() {
        return ticket;
    }

    // MODIFIES: this
    // EFFECTS: a new ticket associated with the given movie is created, and
    //           becomes this MovieGoer's ticket
    public void buyTicket(Movie m) throws ShowingFullException, UnderAgeException {
        if (m.isFull()) {
            throw new ShowingFullException("Sry, no more places for the movie");
        }
        if (age < m.getAgeRestriction()) {
            throw new UnderAgeException("Sry, no nudes for those who wear diapers");
        }
        ticket = new Ticket(m);
    }


}
