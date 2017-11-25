package uk.ac.herts.odeon.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class Movie {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;

  private String title;
  private int year;
  private String genre;
  private String director;
  private String desc;

  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
  private List<Review> reviews = new ArrayList<Review>();

  public Movie() { } //empty constructor

  public Movie(String title, int year, String genre, String director, String desc) {
    this.title = title;
    this.year = year;
    this.genre = genre;
    this.director = director;
    this.desc = desc;
  }

  public void addReview(Review review) {
    reviews.add(review);
    review.setMovie(this);
  }

  //getters and setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

  public String getDesc() {
    return this.desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Transient
  public double getAverageReview() {
    double total = 0.0;
    for(Review r : this.getReviews()) { total += r.getRating(); }
    return (total/this.getReviews().size());
  }

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", genre=" + genre + ", director=" + director + "]";
	}
}
