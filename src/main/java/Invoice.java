import java.math.BigDecimal;
import java.util.*;

public class Invoice {

  public String customer;
  public List<Performance> performances;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  public double TotalAmount() {

    double totalAmount = 0;
    double res = 0;

    for (Performance perf : performances) {

      res = perf.play.thisAmount(perf.audience);
      totalAmount += res ;

    }
    return totalAmount;
  }

  public int  TotalCredits() {

    int volumeCredits = 0;
    for (Performance perf : performances) {

      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (perf.play.type.equals(perf.play.COMEDY)) volumeCredits += Math.floor(perf.audience / 5);
    }
    return volumeCredits;
  }


}
