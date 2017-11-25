package uk.ac.herts.odeon.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herts.odeon.model.Movie;

@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer> {

  public Movie findByTitle(String title);
}
