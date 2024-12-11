import question7and8Extra.Block;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// Shares Tax Calculations (Queue)

/*
    Will repeatedly ask the user to enter the commands in the format
    buy qty price
    or
    sell qty price
    or
    quit
     */
public class Question7 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String command;
//        declared Queue<Block> shares;
        Queue<Block> shares = new ArrayDeque<>();

        do {
            System.out.print(">");
            command = in.next();
            if (command.equalsIgnoreCase("buy")) {
                System.out.println("Quantity to buy:");
                int qty = in.nextInt();
                System.out.println("Price:");
                double price = in.nextDouble();
                shares.add(new Block(qty, price));

            } else if (command.equals("sell")) {
                System.out.println("Quantity to sell:");
                int qty = in.nextInt();
                System.out.println("Sell price:");
                double price = in.nextDouble();
                double profit = 0.0;
                int sold = 0;
                Block toSell;
                double localProfit = 0;
                do {
                    /*inside a do-wile loop program in each iteration if queue is not empty,
                     program peeks toSell Block to retrieve and modify element, but not remove it from queue*/
                    if (!shares.isEmpty()) {
                        toSell = shares.peek();
                        // if difference between shares to be sold and shares inside object > 0 we count the local profit and remove element
                        if ((qty - toSell.getQuantity()) > 0) {
                            qty -= toSell.getQuantity();
                            sold += toSell.getQuantity();
                            localProfit = toSell.getQuantity() * price - toSell.getCost();

                            shares.poll();
                        } else {
                            // else local profit should be calculated as a cost of shares left to be sold  - their cost with old price
                            localProfit = qty * price - qty * toSell.getPrice();
                            int oldQty = toSell.getQuantity();
                            toSell.setQuantity(toSell.getQuantity() - qty);
                            int newQty = toSell.getQuantity();
                            sold += oldQty - newQty;
                            qty = 0;
                        }
                        profit += localProfit;

                    } else {
                        System.out.println("No stocks left");
                        break;
                    }

                } while (qty > 0);
                System.out.println("Sold " + sold + " shares");
                System.out.println("Profit " + profit);
            }
        } while (!command.equalsIgnoreCase("quit"));
    }
}