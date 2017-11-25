package uk.ac.herts.odeon.service.mock;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.herts.odeon.model.Customer;
import uk.ac.herts.odeon.repo.CustomerRepository;
import uk.ac.herts.odeon.service.IDataInitializer;

@Service
public class MockCustomerService implements IDataInitializer {
  private List<String> surnames = new LinkedList<String>(Arrays.asList(new String[] {
    "Prothero", "Abdous", "Kozlova", "Augustine", "Moiamedi",
    "Lundell", "Wiedersheim", "Shropshire", "Darabi", "Baboolal",
    "Mauger", "Tracy", "Garman", "Bluestein", "Lindberg",
    "Krolewski", "Puri", "Toomey", "Burpee", "Zahn"
  }));

  private List<String> names = new LinkedList<String>(Arrays.asList(new String[] {
    "Patrick", "Donella", "Gal", "Loretta", "Gaile",
    "Kassi", "Vernen", "Vivie", "Ole", "Monah",
    "Forest", "Melodee", "Montague", "Cassy", "Archaimbaud",
    "Emogene", "Farris", "Melanie", "Kaspar", "Shawna"
  }));

  @Autowired
	protected CustomerRepository cRepo;

  @Override
  public void initData() {
    Random rand = new Random();

    for(int i=0; i<20; i++) {
      int fnIndex = rand.nextInt(names.size());
      String fn = names.remove(fnIndex);

      int snIndex = rand.nextInt(surnames.size());
      String ln = surnames.remove(snIndex);

      //generate random age between 15 and 65
      int age = rand.nextInt(50) + 15;

      cRepo.save(new Customer(fn, ln, age));
    }
  }


}
