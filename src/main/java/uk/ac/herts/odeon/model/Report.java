package uk.ac.herts.odeon.model;

import java.util.Map;
import java.util.Set;

import uk.ac.herts.odeon.model.Movie;

public class Report {

  private Movie highestGrossingMovie; //computed on report generation
  private Movie highestRatedMovie;    //computed on report generation
  private Movie mostViewedMovie;      //computed on report generation

  private Map<Movie, Integer> movieSpectators;
  private Map<Movie, Double> movieProfits;

  public Report(Map<Movie, Integer> movieSpectators, Map<Movie, Double> movieProfits) {
    this.movieSpectators = movieSpectators;
    this.movieProfits = movieProfits;

    this.calculateHighestRatedMovie();
    this.calculateHighestGrossingMovie();
    this.calculateMostViewedMovie();
  }

  //public helper methods
  public Set<Movie> getMovies() {
    return movieSpectators.keySet();
  }

  public int getTotalSpectatorsPerMovie(Movie m) {
    return movieSpectators.getOrDefault(m, 0);
  }

  public double getTotalRevenuePerMovie(Movie m) {
    return movieProfits.getOrDefault(m, 0.0);
  }

  //private helper methods
  private void calculateHighestGrossingMovie() {
    double max = -1.0; //profits cannot be less than 0.0
    for(Map.Entry<Movie, Double> entry : movieProfits.entrySet()) {
      double profit = entry.getValue();
      if(profit > max) {
        this.highestGrossingMovie = entry.getKey();
        max = profit;
      }
    }
  }

  private void calculateMostViewedMovie() {
    double max = -1.0; //cannot have negative spectators
    for(Map.Entry<Movie, Integer> entry : movieSpectators.entrySet()) {
      int spectators = entry.getValue();
      if(spectators > max) {
        this.mostViewedMovie = entry.getKey();
        max = spectators;
      }
    }
  }

  private void calculateHighestRatedMovie() {
    double max = -1.0;  //movie reviews cannot be negative
    for(Movie m : movieSpectators.keySet()) {
      double review = m.getAverageReview();
      if(review > max) {
        this.highestRatedMovie = m;
        max = review;
      }
    }
  }

  //getters and setters
	public Movie getHighestGrossingMovie() {
		return this.highestGrossingMovie;
	}

	public Movie getHighestRatedMovie() {
		return this.highestRatedMovie;
	}

	public Map<Movie, Integer> getMovieSpectators() {
		return this.movieSpectators;
	}

	public void setMovieSpectators(Map<Movie, Integer> movieSpectators) {
		this.movieSpectators = movieSpectators;
	}

	public Map<Movie, Double> getMovieProfits() {
		return this.movieProfits;
	}

	public void setMovieProfits(Map<Movie, Double> movieProfits) {
		this.movieProfits = movieProfits;
	}
}
