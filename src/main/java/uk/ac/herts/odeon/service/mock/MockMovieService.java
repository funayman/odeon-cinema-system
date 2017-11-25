package uk.ac.herts.odeon.service.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.herts.odeon.model.Movie;
import uk.ac.herts.odeon.repo.MovieRepository;
import uk.ac.herts.odeon.service.IDataInitializer;

@Service
public class MockMovieService implements IDataInitializer {
  private static final Movie[] movies = {
    new Movie("Pulp Fiction", 1994, "crime", "Quentin Tarantino",
      "The lives of two mob hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption."),
    new Movie("V for Vendetta", 2005, "action", "James McTeigue",
      "In a future British tyranny, a shadowy freedom fighter, known only by the alias of \"V\", plots to overthrow it with the help of a young woman."),
    new Movie("鋼の錬金術師", 2017, "animation", "曽利文彦",
      "Two alchemist brothers go on a quest for the Philosopher's Stone after an attempt to revive their dead mother goes horribly wrong."),
    new Movie("The Castle", 1997, "comedy", "Rob Sitch",
      "A working-class family from Melbourne, Australia fights city hall after being told they must vacate their beloved family home to allow for infrastructural expansion."),
    new Movie("10 Things I Hate About You", 1999, "romance", "Gil Junger",
      "A pretty, popular teenager can't go out on a date until her ill-tempered older sister does."),
    new Movie("探偵はBARにいる3", 2017, "mystery", "橋本一",
      "To solve the case where the detective and its partner · Takada was involved in the bar of Susukino in the entertainment district of Sapporo in Hokkaido."),
    new Movie("Star Wars: Episode IV", 1977, "sci-fi", "George Lucas",
      "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle-station while also attempting to rescue Princess Leia from the evil Darth Vader."),
    new Movie("Kiss Kiss Bang Bang", 2005, "comedy", "Shane Black",
      "A murder mystery brings together a private eye, a struggling actress, and a thief masquerading as an actor.")
  };

  @Autowired
  protected MovieRepository mRepo;

  @Override
  public void initData() {
    for(Movie m : movies) {
      mRepo.save(m);
    }
  }

}
