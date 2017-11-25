package uk.ac.herts.odeon.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herts.odeon.model.Customer;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
