package test;

import exceptions.ShowingFullException;
import exceptions.UnderAgeException;
import model.Movie;
import model.MovieGoer;
import model.Ticket;
import model.TicketKiosk;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieGoerTest {

    private Movie m1, m2;
    private MovieGoer mg1, mg2;
    private Ticket t1, t2;
    private TicketKiosk tk;

    @Before
    public void setUp() {
        tk = new TicketKiosk("Scotiabank Theatre");
        mg1 = new MovieGoer("Tom Hanks", 28, tk);
        mg2 = new MovieGoer("Sarah Johnson", 17, tk);
        m1 = new Movie("Arrival", 19, 50);
        m2 = new Movie("Ratatouille", 0, 100);
        t1 = new Ticket(m1);
        t2 = new Ticket(m2);
    }

    @Test
    public void testGetters() {
        assertEquals(mg1.getName(),"Tom Hanks");
        assertEquals(mg2.getName(),"Sarah Johnson");
        assertEquals(mg1.getAge(),28);
        assertEquals(mg2.getAge(),17);
        assertEquals(mg1.getTicketKiosk(), tk);
        assertEquals(mg2.getTicketKiosk(), tk);
    }

    @Test
    public void testBuyTicketNoUnderAgeException() {
        try {
            mg1.buyTicket(m1);
        } catch (ShowingFullException e) {
            fail("Caught ShowingFullException: There should be places");
        } catch (UnderAgeException e) {
            fail("Caught UnderAgeException: The age is appropriate");
        }
    }

    @Test
    public void testBuyTicketUnderAgeException() {
        try {
            mg2.buyTicket(m1);
        } catch (ShowingFullException e) {
            fail("Caught ShowingFullException: There should be places");
        } catch (UnderAgeException e) {
            System.out.println("Caught UnderAgeException: Correct behaviour");
        }
    }


    @Test
    public void testBuyTicketNoShowingFullException() {
        m2.setCurrentSeating(60);
        try {
            mg2.buyTicket(m2);
        } catch (ShowingFullException e) {
            fail("Common, nobody wants to go Ratatouille");
        } catch (UnderAgeException e) {
            fail("Wut? This movie is for toddlers");
        }
    }

    @Test
    public void testBuyTicketShowingFullException() {
        m1.setCurrentSeating(50);
        try {
            mg1.buyTicket(m1);
        } catch (ShowingFullException e) {
            System.out.println("The Arrival got so popular, we can't even let Tom Hanks in");
        } catch (UnderAgeException e) {
            fail("This should not have happened");
        }
    }


}