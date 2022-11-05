
import java.text.NumberFormat;
import java.util.*;



public class StatementPrinter {

  double thisAmount = 0;

  public String toText(Invoice invoice) {

    String result = String.format("Statement for %s\n", invoice.customer.name);

    StringBuffer res = new StringBuffer(result);


    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);


    for (Performance perf : invoice.performances) {

      thisAmount = perf.play.thisAmount(perf.audience, perf.play.type);
      result = String.format("  %s: %s (%s seats)\n", perf.play.name, frmt.format(thisAmount) , perf.audience);
      res.append(result);

    }

    result = String.format("Amount owed is %s\n", frmt.format(invoice.TotalAmount()));
    res.append(result);
    result = String.format("You earned %s credits\n", invoice.TotalCredits());
    res.append(result);
    result = String.format("Fidelity point solde %s \n", invoice.customer.updateSolde(invoice.customer.clientNumber,invoice.TotalCredits()));
    res.append(result);


    return res.toString();
  }



  public String toHtml(Invoice invoice  ) {

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    String result = String.format(" <html>\n<head><h2> Inovice </h2></head> \n");


    StringBuffer res = new StringBuffer(result);


    result = String.format("<body>\n<p><b>Client name : %s <b></p> \n", invoice.customer.name);
    res.append(result);


    result = String.format("<table border=2>\n<tr><th>Piece</th>\n<th>Seatsold</th>\n<th>Price</th>\n</tr>\n");
    res.append(result);


    for (Performance perf : invoice.performances) {
      thisAmount = perf.play.thisAmount(perf.audience,perf.play.type);
      result =  String.format(" <tr>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n</tr>\n ",perf.play.name,perf.audience , frmt.format(thisAmount));
      res.append(result);
    }

    result= String.format("<tr>\n<td colspan=2><b>Total owed:</b></td ma>\n<td> %s</td>\n</tr>\n", frmt.format(invoice.TotalAmount()));
    res.append(result);

    result= String.format("<tr>\n<td colspan=2><b>Fidelity point earned:</b></td>\n<td> %d</td>\n</tr>\n", invoice.TotalCredits());
    res.append(result);

    result= String.format("<tr>\n<td colspan=2><b>Fidelity point solde:</b></td>\n<td> %s</td>\n</tr>\n</table>\n",invoice.customer.updateSolde(invoice.customer.clientNumber,invoice.TotalCredits()));
    res.append(result);

    result = String.format("\n<p>Payment is required under 30 days. We can break your knees if you don't do so.</p>\n</body>\n</html>");
    res.append(result);



    return res.toString();



  }

}
