package uk.ac.herts.odeon.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Purchase {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;

  @ManyToOne(cascade=CascadeType.MERGE)
  @JoinColumn(name="cid")
  private Customer customer;

  @ManyToOne(cascade=CascadeType.MERGE)
  @JoinColumn(name="mid")
  private Movie movie;

  private Date dateCreated;
  private SeatType seat;
  private ScreeningType screening;
  private PaymentType payment;
  private double total;
  private int numberOfTickets;

  public Purchase() { } //empty constructor

	public Purchase(Customer customer, Movie movie, SeatType seat, ScreeningType screening, PaymentType payment, double total, int numberOfTickets) {
		this.customer = customer;
		this.movie = movie;
		this.seat = seat;
		this.screening = screening;
		this.payment = payment;
		this.total = total;
    this.numberOfTickets = numberOfTickets;
	}

  @PrePersist
  public void prePersist() {
    this.dateCreated = new Date();
  }

  //getters and setters
	public int getId() {
		return this.id;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public SeatType getSeat() {
		return this.seat;
	}

	public void setSeat(SeatType seat) {
		this.seat = seat;
	}

	public ScreeningType getScreening() {
		return this.screening;
	}

	public void setScreening(ScreeningType screening) {
		this.screening = screening;
	}

	public PaymentType getPayment() {
		return this.payment;
	}

	public void setPayment(PaymentType payment) {
		this.payment = payment;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getNumberOfTickets() {
		return this.numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", customer=" + customer + ", movie=" + movie + ", dateCreated=" + dateCreated + ", seat=" + seat + ", screening=" + screening + ", payment=" + payment + ", total=" + total + "]";
	}
}
