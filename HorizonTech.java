import java.util.ArrayList;
import java.util.Scanner;

public class HorizonTech {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HorizonTechInventory one = new HorizonTechInventory();

        int acontinue;
        int totalpurchase = 0;

        do{

            System.out.println("Which operation do you want to perform");
            System.out.println("1. Purchase 2.Sell 3.Display Status");
            int operation = sc.nextInt();
            if(operation == 1){
                int tocontinue;
                int totalcost = 0;
                do {
                    System.out.println("1. Laptop 2.Phone");
                    int type = sc.nextInt();
                    System.out.println("Type the model name:");
                 String model = sc.next();
                 System.out.println("At what price to buy:");
                 int price = sc.nextInt();
                 System.out.println("Whats the qty");
                 int nos = sc.nextInt();
                 if(type==1){
                 one.purchase("Laptop",price,model,nos);}
                 else{
                     one.purchase("Phone",price,model,nos);

                 }
                 totalcost += (nos *price);
                    System.out.println("Do you wish to continue buying 1. yes");
                 tocontinue = sc.nextInt();
                 if(tocontinue != 1){
                     System.out.println("Your total cost is : "+ totalcost);
                     totalpurchase += totalcost;
                 }






            }
            while(tocontinue == 1);
            }
            else if(operation == 2){
                int tocontinue;

                do {
                    if(HorizonTechInventory.HorizonProduct.prodcount == 0){
                        System.out.println("There are no products in the inventory to sell");
                        break;
                    }

                    System.out.println("whats the type 1. Laptop 2.Phone ");
                    int type = sc.nextInt();

                    if (type == 1) {
                        if(HorizonTechInventory.Laptop.lapcount == 0){
                            System.out.println("There are no laptops available");
                            break;
                        }
                        System.out.println("the following are the products of the laptop available");
                        String newlapitem = "";
                        for (int i = 0; i < HorizonTechInventory.store.size(); i++) {
                            if (HorizonTechInventory.store.get(i).type.equals("Laptop")) {
                                if(! HorizonTechInventory.store.get(i).model.equals(newlapitem)){
                                    newlapitem = HorizonTechInventory.store.get(i).model;
                                    System.out.println(newlapitem);
                                }
                            }
                        }
                    }
                    else if (type == 2) {
                        if(HorizonTechInventory.Phone.phonecount == 0){
                            System.out.println("There are no phones available");
                            break;
                        }
                        System.out.println("the following are the products of the Phone available");
                        String newphoneitem = "";
                        for (int i = 0; i < HorizonTechInventory.store.size(); i++) {
                            if (HorizonTechInventory.store.get(i).type.equals("Phone")) {
                                if(! HorizonTechInventory.store.get(i).model.equals(newphoneitem)){
                                    newphoneitem = HorizonTechInventory.store.get(i).model;
                                    System.out.println(newphoneitem);
                                }
                            }
                        }
                    }
                    System.out.println("Type the model name:");
                    String model = sc.next();
                    System.out.println("At what price to sell:");
                    int price = sc.nextInt();
                    System.out.println("Whats the qty");
                    int nos = sc.nextInt();
                    if(type ==1 ){
                    one.sell("Laptop", price, model, nos);}
                    else{one.sell("Phone", price, model, nos);}
                    System.out.println("Do you wish to continue with selling? 1. Yes  2.No");
                    tocontinue =sc.nextInt();
                    if(tocontinue != 1){
                        System.out.println("The total profit made on this sale : "+ HorizonTechInventory.totalprofit);
                    }


                }
                while(tocontinue ==1);

            }
            else{
                one.dispstatus();
            }
            System.out.println("do you wish to continue with the inventory ? 1.Yes");
            acontinue = sc.nextInt();

        }
        while(acontinue == 1);
        if(acontinue != 1){
            System.out.println("Your total purchase cost is :"+ totalpurchase);
            System.out.println("The total profit made is :"+HorizonTechInventory.totalprofit);
        }
    }
}

class HorizonTechInventory {
     static ArrayList<HorizonProduct> store= new ArrayList<>();
     static int totalprofit = 0;




    void sell(String type,int price,String model,int nos){
        // iterate loop till removed is less than nos and
        // check for type and model.if present then remove from the specific list
        // print profit.



        int removed = 0;
        int TotalCostprice = 0;
        int i = 0;
        while (removed < nos && i < store.size()) {
            if(store.get(i).type.equals(type) && store.get(i).model.equals(model)){
                TotalCostprice = TotalCostprice + store.get(i).costprice;
                removed ++;
                store.remove(i);
                if(type.equals("Laptop")){Laptop.lapcount --;}
                else {Phone.phonecount --;}
                HorizonProduct.prodcount --;

            } else {
                i++;
            }

        }
        if(removed!=nos){
            System.out.println("Insufficient items. Total Products that could be sold: " + removed);
            System.out.println((nos-removed)+ " items are missing of type "+ type + " and model "+ model);
        }
        int profit = (price * removed) - TotalCostprice;
        totalprofit += profit;
        System.out.println("Items sold : "+ removed + " of type "+type +" and model "+ model);
        System.out.println("the profit for the sold item is " + profit);


    }






    void dispstatus(){
        //include purchase
        System.out.println("total products in inventory :" + HorizonProduct.prodcount);
        System.out.println("total Laptops :" + Laptop.lapcount);
        System.out.println("total Phones :"+ Phone.phonecount);

    }

    void purchase(String type,int price, String model, int nos){
         if(type == "Laptop") {
             for (int i = 0; i < nos; i++) {
                 Laptop laptop = new Laptop(model,price);
                 store.add(laptop);
             }
         }

         else if(type == "Phone") {
             for (int i = 0; i < nos; i++) {
                 Phone phone = new Phone(model, price);
                 store.add(phone);
             }

         }
        System.out.println(nos+" "+ type + " of model " + model +" purchased for price " + price );
         dispstatus();
     }

    public abstract class AbHorizonProduct{
        String model;
        String type;
        int costprice;

    }
    class HorizonProduct extends AbHorizonProduct{
        static int prodcount = 0;
    }
    class Laptop extends HorizonProduct{
         static int lapcount = 0;

         Laptop(String model, int price){

            Laptop.lapcount ++;
            HorizonProduct.prodcount ++;
            this.type = "Laptop";
            this.model = model;
            this.costprice = price;

        }
    }

    class Phone extends HorizonProduct{
         static int phonecount;

        Phone (String model, int price) {

            Phone.phonecount ++;
            HorizonProduct.prodcount ++;
            this.type = "Phone";
            this.model = model;
            this.costprice = price;
        }
//        remove is used to check if remove is possible and then product count is subtracted but
//        the product still needs to be removed from the arraylist.
    }
}






