import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


// Multi-company (Queue)
/*
   Will repeatedly ask the user to enter the commands in the format
   buy company qty price
   or
   sell company qty price
   or
   quit
    */

static Scanner in = new Scanner(System.in);

        public static void main(String[] args) {
            Map<String, Queue<Block>> map = new HashMap<>();
            String command;
            do {
                System.out.print(">");
                command = in.next();
                if (command.equalsIgnoreCase("buy")) {
                    String company = in.next();
                    map.putIfAbsent(company, new ArrayDeque<>());
                    Queue<Block> shares = buy(map.get(company));
                    map.put(company, shares);
                } else if (command.equals("sell")) {
                    displayCompanies(map);
                    System.out.println("Company: ");
                    String company = in.next();
                    Queue<Block> shares = map.get(company);
                    sell(shares);
                }
            } while (!command.equalsIgnoreCase("quit"));
        }

        public static Queue<Block> buy(Queue<Block> companyShares) {
            System.out.println("Quantity to buy:");
            int qty = in.nextInt();
            System.out.println("Price:");
            double price = in.nextDouble();
            companyShares.add(new Block(qty, price));
            return companyShares;
        }

        public static void sell(Queue<Block> shares) {
            System.out.println("Quantity to sell:");
            int qty = in.nextInt();
            System.out.println("Sell price:");
            double price = in.nextDouble();
            double profit = 0.0;
            int sold = 0;
            Block toSell;
            double localProfit;
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

        public static void displayCompanies(Map<String, Queue<Block>> map){
            map.forEach((company, shares)->{
                AtomicInteger total = new AtomicInteger();
                shares.forEach(block -> {
                    total.addAndGet(block.getQuantity());
                });
                System.out.println("Company: " + company + " Shares: " + total);
            });
        }