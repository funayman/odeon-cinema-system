package uk.ac.herts.odeon.model;

import java.util.Map;
import java.util.HashMap;

import javax.persistence.*;

@Entity
public class Screen {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;

  @OneToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
  private Movie movie;

  @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
  private Map<ScreeningType, Showing> showings = new HashMap<ScreeningType, Showing>();

  public Screen() { } //empty constructor

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Map<ScreeningType, Showing> getShowings() {
		return this.showings;
	}

	public void setShowings(Map<ScreeningType, Showing> showings) {
		this.showings = showings;
	}

  public void addShowing(ScreeningType type, Showing showing) {
    showings.put(type, showing);
  }


	@Override
	public String toString() {
		return "Screen [id=" + id + ", movie=" + movie + ", showings=" + showings + "]";
	}
}
