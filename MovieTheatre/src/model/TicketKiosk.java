package model;
import exceptions.ShowingFullException;
import exceptions.UnderAgeException;

import java.util.LinkedList;
import java.util.List;

public class TicketKiosk {

    private String name;
    private List<Movie> movies;

    // EFFECTS: TicketKiosk with name (name) is created
    public TicketKiosk(String name) {
        this.name = name;
        movies = new LinkedList<>();
    }

    // getters
    public String getName() {
        return name;
    }
    public List<Movie> getMovies() {
        return movies;
    }

    //EFFECTS: adds the movie (m) to the movies field. Check if the movie (m) is already within movies before you
    //         add it in the method. If adding (m) is successful, return true, else return false.
    public boolean addMovie(Movie m) {
        if (movies.contains(m)) {
            return false;
        }
        movies.add(m);
        return true;
    }

    //EFFECTS: calls MovieGoer.buyTicket(Movie m) on mg, where the ticket is for the movie m.
    public boolean sellTicket(MovieGoer mg, Movie m) throws ShowingFullException, UnderAgeException {
        mg.buyTicket(m);
        return true;
    }


}
