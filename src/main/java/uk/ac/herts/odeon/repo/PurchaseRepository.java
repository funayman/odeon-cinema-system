package uk.ac.herts.odeon.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herts.odeon.model.Customer;
import uk.ac.herts.odeon.model.Movie;
import uk.ac.herts.odeon.model.Purchase;

@Transactional
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
  public List<Purchase> findAllByCustomer(Customer customer);

  public List<Purchase> findAllByMovie(Movie movie);

  //SELECT * FROM purchases ORDER BY date_created DESC LIMIT 5;
  public List<Purchase> findFirst5ByOrderByIdDesc();
}
