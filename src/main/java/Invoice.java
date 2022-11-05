import java.math.BigDecimal;
import java.util.*;

public class Invoice {

  public Customer customer;
  public List<Performance> performances;

  public Invoice(Customer customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  public double TotalAmount() {

    double totalAmount = 0;
    double res = 0;

    for (Performance perf : performances) {

      res = perf.play.thisAmount(perf.audience,perf.play.type);
      totalAmount += res ;

      totalAmount=customer.reduction(totalAmount);

    }
    return totalAmount;
  }

  public int  TotalCredits() {

    int volumeCredits = 0;
    for (Performance perf : performances) {

      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (perf.play.type.equals("comedy")) volumeCredits += Math.floor(perf.audience / 5);
    }
    //volumeCredits = customer.updateSolde(customer.clientNumber, volumeCredits);
    return volumeCredits;
  }


}
