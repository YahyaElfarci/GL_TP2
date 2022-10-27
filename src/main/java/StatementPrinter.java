import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {

  // creation d'une methode print
  public String print(Invoice invoice, Map<String, Play> plays) {
    int totalAmount = 0;
    int volumeCredits = 0;
    String result = String.format("Statement for %s\n", invoice.customer);

    StringBuffer res = new StringBuffer(result);


    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    // boucle sur une liste de performannces
    for (Performance perf : invoice.performances) {
      // Retourne la valeur associée à la cle : perf.playID
      Play play = plays.get(perf.playID);
      int thisAmount = 0;

      switch (play.type) {
        case "tragedy":
          thisAmount = 40000;
          if (perf.audience > 30) {
            thisAmount += 1000 * (perf.audience - 30);
          }
          break;
        case "comedy":
          thisAmount = 30000;
          if (perf.audience > 20) {
            thisAmount += 10000 + 500 * (perf.audience - 20);
          }
          thisAmount += 300 * perf.audience;
          break;
        default:
          throw new Error("unknown type: ${play.type}");
      }
      // volume adeed just in case audience is over 30 , so all clients benefits from this credit in case audience >30

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      // why devided by 100
      result = String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
      res.append(result);

      totalAmount += thisAmount;


    }
    result = String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
    res.append(result);
    result = String.format("You earned %s credits\n", volumeCredits);
    res.append(result);
    //return result;
    return res.toString();
  }

}
