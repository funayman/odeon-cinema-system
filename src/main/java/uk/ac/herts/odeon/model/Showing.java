package uk.ac.herts.odeon.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class Showing {

  public static final int MAX_REG_SEAT_CAPACITY = 40;
  public static final int MAX_VIP_SEAT_CAPACITY = 10;
  public static final double VIP_SEAT_SURCHARGE = 7.00;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;
  @Transient
  private List<Customer> regSeats = new ArrayList<Customer>();
  @Transient
  private List<Customer> vipSeats = new ArrayList<Customer>();
  @Column
  private double price = 0.0;

  public Showing() { } //empty constructor
  public Showing(double price) {
    this.price = price;
  }

  //getters and setters
	public List<Customer> getRegSeats() {
		return this.regSeats;
	}

	public void setRegSeats(List<Customer> regSeats) {
		this.regSeats = regSeats;
	}

	public List<Customer> getVipSeats() {
		return this.vipSeats;
	}

	public void setVipSeats(List<Customer> vipSeats) {
		this.vipSeats = vipSeats;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getVipPrice() {
		return this.price + VIP_SEAT_SURCHARGE;
	}

	@Override
	public String toString() {
		return "Showing [id=" + id + ", regSeats=" + regSeats + ", vipSeats=" + vipSeats + ", price=" + price + "]";
	}
}
