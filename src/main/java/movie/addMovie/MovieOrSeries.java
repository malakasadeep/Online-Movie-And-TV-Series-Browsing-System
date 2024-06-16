package movie.addMovie;

public class MovieOrSeries {

	private int id;
	private String title;
	private String genre;
	private String releaseDate;
	private String director;
	private String description;
	private String imageUrl; // New field for image URL

	public MovieOrSeries(int id, String title, String genre, String releaseDate, String director, String description,
			String imageUrl) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.director = director;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public MovieOrSeries(String title, String genre, String releaseDate, String director, String description,
			String imageUrl) {
		super();
		this.title = title;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.director = director;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getDirector() {
		return director;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	 public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }

	
}
