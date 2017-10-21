package model;

public class Movie {

    private String title;
    private int ageRestriction;
    private int currentSeating;
    private int maxSeating;

    public Movie(String title, int ageRestriction, int seating) {
        this.title = title;
        this.ageRestriction = ageRestriction;
        this.currentSeating = 0;
        this.maxSeating = seating;
    }

    //getters
    public String getTitle() {
        return title;
    }
    public int getAgeRestriction() {
        return ageRestriction;
    }
    public int getCurrentSeating() {
        return currentSeating;
    }
    public int getMaxSeating() {
        return maxSeating;
    }

    //setters
    public void setCurrentSeating(int currentSeating) {
        // Should throw an exception, if it is more than max seating
        this.currentSeating = currentSeating;
    }

    //EFFECTS: returns true if the movie is at full capacity, else return false
    public boolean isFull() {
        return currentSeating == maxSeating;
    }

    //EFFECTS: increment the currentSeating field by 1
    public void addViewer() {
        // Should throw an exception if the maxSeating will overflow - the user of this class shouldn't care
        currentSeating++;
    }


}
