import java.util.Objects;

public class Play {

  public String name;
  public String type;

// Création des variables statiques pour les types de pièces de théâtre
  public static String COMEDY="comedy" ;
  public static String TRAGEDY="tragedy" ;

  public Play(String name, String type) {
    this.name = name;
    Objects.requireNonNull(checkType(type),"type must not be null ");
    this.type = type;
  }

  //and as shown above: the true power of this idea unfolds in conjunction with final fields.
  // Because now any other code in your class can safely assume that bar isn't null -
  // and thus you do not need any if (bar == null) checks in other places!

  public String checkType(String type) {
    if (type.equals("comedy") || type.equals("tragedy")) return type;
    return null;
  }

  public double thisAmount(int audience, String type){
    double amount = 0 ;
    if (type.equals(TRAGEDY)){
      amount = 400;
      if (audience > 30){
        amount += 10 * (audience - 30);
      }
    }

    if (type.equals(COMEDY)){
      amount =  300 ;
      if (audience > 20){
        amount += 100 + 5*(audience - 20);
      }
      amount += 3*audience ;
    }
    return amount;
  }
}
