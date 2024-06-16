package movie.Review;


public class MovieReview {

    private int id;
    private String movie;
    private int rating;
    private String review;
    private String name;
    private String email;

    public MovieReview(int id, String movie, int rating, String review, String name, String email) {
        this.id = id;
        this.movie = movie;
        this.rating = rating;
        this.review = review;
        this.name = name;
        this.email = email;
    }

    public MovieReview(String movie, int rating, String review, String name, String email) {
        this.movie = movie;
        this.rating = rating;
        this.review = review;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getMovie() {
        return movie;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

