package uk.ac.herts.odeon.service.mock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.herts.odeon.model.Movie;
import uk.ac.herts.odeon.model.Showing;
import uk.ac.herts.odeon.model.Screen;
import uk.ac.herts.odeon.model.ScreeningType;
import uk.ac.herts.odeon.repo.MovieRepository;
import uk.ac.herts.odeon.repo.ScreenRepository;
import uk.ac.herts.odeon.service.IDataInitializer;

@Service
public class MockScreenService implements IDataInitializer {
  public static int MAX_NUMBER_OF_SCREENS = 6;

  @Autowired
  protected MovieRepository mRepo;

  @Autowired
  protected ScreenRepository sRepo;

  @Override
  public void initData() {
    List<Movie> movies = mRepo.findAll();

    //set up screens and showings
    Screen[] screens = new Screen[MAX_NUMBER_OF_SCREENS];
    for(int i=0; i<screens.length; i++) {
      Screen screen = new Screen();
      screen.setMovie(movies.get(i));

      for(ScreeningType st : ScreeningType.values()) {
        double price = 0.0;
        switch(st) {
          case AFTERNOON:
          price = 5.50;
          break;
          case EVENING:
          price = 8.50;
          break;
          case NIGHT:
          price = 12.50;
          break;
          case LATE_NIGHT:
          price = 10.00;
          break;
        }

        Showing showing = new Showing(price);
        screen.addShowing(st, showing);
      }

      sRepo.save(screen);
    }



  }

}
