import java.util.Objects;

public class Play {

  public String name;
  public String type;

// Création des variables statiques pour les types de pièces de théâtre
  public final static String COMEDY="comedy" ;
  public final static String TRAGEDY="tragedy" ;

  public Play(String name, String type) {
    this.name = name;
    Objects.requireNonNull(checkType(type),"type must not be null ");
    this.type = type;
  }

  //and as shown above: the true power of this idea unfolds in conjunction with final fields.
  // Because now any other code in your class can safely assume that bar isn't null -
  // and thus you do not need any if (bar == null) checks in other places!

  public String checkType(String type) {
    if (type == "comedy" || type == "tragedy") return type;
    return null;
  }
}
