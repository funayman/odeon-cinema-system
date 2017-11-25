package uk.ac.herts.odeon.service.mock;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.herts.odeon.model.Movie;
import uk.ac.herts.odeon.model.Purchase;
import uk.ac.herts.odeon.model.Report;
import uk.ac.herts.odeon.repo.PurchaseRepository;
import uk.ac.herts.odeon.service.IReporter;

@Service
public class MockReportService implements IReporter {

  @Autowired
  protected PurchaseRepository pRepo;

  @Override
  public Report generate() {
    Map<Movie, Integer> totalSpectators = new HashMap<Movie, Integer>();
    Map<Movie, Double> totalGrossing = new HashMap<Movie, Double>();

    for(Purchase p : pRepo.findAll()) {
      Movie m = p.getMovie();
      //compute out total spectators
      if(totalSpectators.containsKey(m)) {
        int n = totalSpectators.get(m) + p.getNumberOfTickets();
        totalSpectators.put(m, n);
      } else {
        totalSpectators.put(m, p.getNumberOfTickets());
      }

      //compute top grossing movie
      if(totalGrossing.containsKey(m)) {
        double n = totalGrossing.get(m) + p.getTotal();
        totalGrossing.put(m, n);
      } else {
        totalGrossing.put(m, p.getTotal());
      }
    }

    return new Report(totalSpectators, totalGrossing);
  }
}
