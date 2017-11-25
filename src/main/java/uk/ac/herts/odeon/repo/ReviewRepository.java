package uk.ac.herts.odeon.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herts.odeon.model.Customer;
import uk.ac.herts.odeon.model.Review;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer> {
  public List<Review> findAllByCustomer(Customer customer);

  //SELECT * FROM reviews ORDER BY date_created DESC LIMIT 4;
  public List<Review> findFirst4ByOrderByIdDesc();

  //SELECT * FROM reviews ORDER BY date_created DESC;
  public List<Review> findAllByOrderByDateCreatedDesc();
}
