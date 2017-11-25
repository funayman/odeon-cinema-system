package uk.ac.herts.odeon.service.mock;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.herts.odeon.model.Customer;
import uk.ac.herts.odeon.model.Movie;
import uk.ac.herts.odeon.model.Purchase;
import uk.ac.herts.odeon.model.Review;
import uk.ac.herts.odeon.repo.MovieRepository;
import uk.ac.herts.odeon.repo.PurchaseRepository;
import uk.ac.herts.odeon.service.IDataInitializer;

@Service
public class MockReviewService implements IDataInitializer {
  private Random rand = new Random();

  private static final String[] BAD_REVIEWS = new String[] {
    "Just not my taste in film...",
    "This has got to be one of the most overrated movies",
    "No plot. Pointless. What was the goal in this movie? It was all over the place",
    "Ehh.... I did NOT like it. Boring, way too much talking, NOT enough action",
    "Stupid~ Went over my head I guess...boring",
  };

  private static final String[] OKAY_REVIEWS = new String[] {
    "Eh. Very good movie but still overrated. But watch it.",
    "Good basis, poor thought The movie was well done, but had some major holes",
    "Way too much hype.... It was an okay movie. Nothing overly special.",
    "Source material is great, I just wish it would have translated to the screen better",
    "Good not great. Would maybe watch again if my friends really wanted to"
  };

  private static final String[] GOOD_REVIEWS = new String[] {
    "A movie that you'll either love or hate...and I loved it!",
    "BEST MOVIE EVER!!! This movie willkeep you guessing til the end. Tear will full your eyes. :,)",
    "If you dislike this movie in any way, you are just that, overly critical",
    "Good for what it is. This movie has jaw dropping graphics but some of the story does get a little dry",
    "No words for how good this movie is."
  };

  @Autowired
  protected PurchaseRepository pRepo;

  @Autowired
  protected MovieRepository mRepo;

  @Override
  public void initData() {
    //only customers who have made a purchase can leave a review
    //gather all reviews and create review based on customer of purchase
    List<Purchase> purchases = pRepo.findAll();

    for(Purchase p : purchases) {
      Movie m = mRepo.findByTitle(p.getMovie().getTitle()); //needed to be done this way in order to avoid duplicate reviews when saving the movie
      Customer c = p.getCustomer();

      int rating = rand.nextInt(5) + 1;
      String desc = null;
      switch(rating) {
        case 5:
        case 4:
          desc = getRandomElement(GOOD_REVIEWS);
          break;
        case 3:
          desc = getRandomElement(OKAY_REVIEWS);
          break;
        default:
          desc = getRandomElement(BAD_REVIEWS);
      }

      m.addReview(new Review(c, rating, desc));
      mRepo.save(m);
    }
  }

  private <E> E getRandomElement(E[] list) {
    int max = list.length;
    return list[ rand.nextInt(max) ];
  }

}
