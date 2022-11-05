import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PrintArray {
    public static void main(String[] args) {

        Map<String, Play> plays = Map.of(
                "hamlet",  new Play("Hamlet", "tragedy"),
                "as-like", new Play("As You Like It", "comedy"),
                "othello", new Play("Othello", "tragedy"));

        Invoice invoice = new Invoice(new Customer("BigCo",1,155), List.of(
                new Performance(plays.get("hamlet"), 55),
                new Performance(plays.get("as-like"), 35),
                new Performance(plays.get("othello"), 40)));


        StatementPrinter statementPrinter = new StatementPrinter();
        System.out.println(statementPrinter.toText(invoice));
        System.out.println(statementPrinter.toHtml(invoice));

        try {
            File file = new File("Tp2.html");


            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(statementPrinter.toHtml(invoice));
            bw.close();


        }catch (IOException e) {
            e.printStackTrace();

        }

    }

}
