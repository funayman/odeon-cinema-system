package uk.ac.herts.odeon.service.mock;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.herts.odeon.model.Customer;
import uk.ac.herts.odeon.model.Screen;
import uk.ac.herts.odeon.model.Purchase;
import uk.ac.herts.odeon.model.Showing;
import uk.ac.herts.odeon.model.SeatType;
import uk.ac.herts.odeon.model.ScreeningType;
import uk.ac.herts.odeon.model.PaymentType;
import uk.ac.herts.odeon.repo.PurchaseRepository;
import uk.ac.herts.odeon.repo.CustomerRepository;
import uk.ac.herts.odeon.repo.ScreenRepository;
import uk.ac.herts.odeon.service.IDataInitializer;

@Service
public class MockPaymentService implements IDataInitializer {
  private Random rand = new Random();
  private final int MINIMUM_DATA_COUNT = 15;
  private final int MINIMUM_VIP_SEAT_COUNT = 5;
  private final int MAX_TICKET_PURCHASE = 4;

  @Autowired
  protected CustomerRepository cRepo;

  @Autowired
  protected ScreenRepository sRepo;

  @Autowired
  protected PurchaseRepository pRepo;

  @Override
  public void initData() {
    List<Screen> screens = sRepo.findAll();
    List<Customer> customers = cRepo.findAll();

    for(int currentVipCount=0, currentDataCount=0; currentVipCount < MINIMUM_VIP_SEAT_COUNT || currentDataCount < MINIMUM_DATA_COUNT;) {
      Customer customer = getRandomElement(customers);
      Screen screen = getRandomElement(screens);
      SeatType seat = getRandomElement(SeatType.values());
      ScreeningType screening = getRandomElement(ScreeningType.asList());
      PaymentType payment = getRandomElement(PaymentType.values());
      int numberOfTickets = rand.nextInt(MAX_TICKET_PURCHASE) + 1; //in random scenerio max of 4 tickets can be purchased

      try {
        newPurchase( customer, screen, seat, screening, payment, numberOfTickets );

        if(SeatType.VIP == seat) { currentVipCount++; }
        currentDataCount++;
      } catch(IllegalArgumentException e) {
        //do nothing, seats were full, try again
      }

    }
  }

  public Purchase newPurchase(Customer customer, Screen screen, SeatType seat, ScreeningType screening, PaymentType payment, int numberOfTickets) {
    Showing wantedShowing = screen.getShowings().get(screening);

    //CUSTOMER WANTS VIP
    if(SeatType.VIP == seat) {
      if(wantedShowing.getVipSeats().size() + numberOfTickets > Showing.MAX_VIP_SEAT_CAPACITY) {
        throw new IllegalArgumentException(
          String.format("Sorry there are only %d VIP seats available", Showing.MAX_VIP_SEAT_CAPACITY - wantedShowing.getVipSeats().size())
        );
      }
    //CUSTOMER WANTS REGULAR SEAT
    } else {
      if(wantedShowing.getRegSeats().size() + numberOfTickets > Showing.MAX_REG_SEAT_CAPACITY) {
        throw new IllegalArgumentException(
          String.format("Sorry there are only %d regular seats available", Showing.MAX_REG_SEAT_CAPACITY - wantedShowing.getRegSeats().size())
        );
      }
    }

    //seats are available, add the seat(s)
    for(int i=0; i<numberOfTickets; i++) {
      if(SeatType.VIP == seat) {
        wantedShowing.getVipSeats().add(customer);
      } else {
        wantedShowing.getRegSeats().add(customer);
      }
    }

    //make a purchase
     double pricePerTicket = (SeatType.VIP == seat) ? wantedShowing.getVipPrice() : wantedShowing.getPrice();
     double total = pricePerTicket * numberOfTickets;
	   Purchase p = new Purchase(customer, screen.getMovie(), seat, screening, payment, total, numberOfTickets);

    //save and return new purchase
    return pRepo.save(p);
  }

  private <E> E getRandomElement(List<E> list) {
    int max = list.size();
    return list.get( rand.nextInt(max) );
  }

  private <E> E getRandomElement(E[] list) {
    return getRandomElement(Arrays.asList(list));
  }
}
