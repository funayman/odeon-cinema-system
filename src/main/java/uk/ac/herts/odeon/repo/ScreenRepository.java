package uk.ac.herts.odeon.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herts.odeon.model.Screen;

@Transactional
public interface ScreenRepository extends JpaRepository<Screen, Integer> {

}
