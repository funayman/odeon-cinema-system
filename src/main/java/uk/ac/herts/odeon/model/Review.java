package uk.ac.herts.odeon.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Review {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;

  @ManyToOne
  private Customer customer;

  @ManyToOne(cascade=CascadeType.MERGE)
  @JoinColumn(name="mid")
  private Movie movie;

  private int rating;
  private String description;
  private Date dateCreated;

  public Review() { } //empty constructor

	public Review(Customer customer, int rating, String description) {
		this.customer = customer;
		this.rating = rating;
		this.description = description;
	}

  @PrePersist
  public void prePersist() {
    //long now = new Date().getTime();
    this.dateCreated = new Date();
  }

  //getters and setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

  public Date getDateCreated() {
    return this.dateCreated;
  }

  //remove setDateCreated; non-updateable; set during PrePersist

	@Override
	public String toString() {
		return "Review [id=" + id + ", customer=" + customer + ", movie=" + movie + ", rating=" + rating + ", description=" + description + "]";
	}
}
