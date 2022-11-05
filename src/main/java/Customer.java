public class Customer {

    public String name ;
    public int clientNumber;
    public int solde;



    public Customer(String name,int clientNumber,int solde){
        this.name = name ;
        this.clientNumber = clientNumber;
        this.solde = solde ;

    }

    public double reduction(double totalAmount){
        if (this.solde >= 150){
            this.solde -= 150 ;
            totalAmount -= 15;
        }
        return totalAmount;
    }

    public int updateSolde(int clientNumber,int volumeCredits){
        if (this.clientNumber == clientNumber) {
            volumeCredits += this.solde;
            return volumeCredits;
        }
        else return volumeCredits;

    }}
