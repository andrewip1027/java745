import java.util.*;
import java.text.*;
import java.io.*;

public class Cashier {
	
	public static void main(String[] args) {

		//variables declared for tax information
		double serviceTax = 0.0;
		double goodTax = 0.0;
		String provinceCode;
		
		//an array declared for shopping cart
		Item[] purchaseItem = new Item[50];
		
		Scanner input = new Scanner(System.in);
		boolean askAgain = true;
		String option;
		String quantity;
		int invoiceNum = 1;
		
		//retrieve tax rates for both goods and services depending on the province code input
		do{
			System.out.print("Please enter the province code: ");
			provinceCode = input.nextLine(); //store provinceCode input 
			
			for(TaxRates rate : TaxRates.values()){
				if(provinceCode.toUpperCase().equals(rate.toString())){  //
					goodTax = Double.parseDouble(rate.getTaxInfo()[0]);
					serviceTax = Double.parseDouble(rate.getTaxInfo()[1]);
					askAgain = false;
				}
			}
		}while(askAgain);
		
		for(int limit = 0; limit < purchaseItem.length; ){
			
			//prompt selection of items available for purchase
			System.out.println("\nPlease enter select the following item for your purchase:\n\n"
					+ "1) garden hose (UPC code 0001), $4.99\n" + "2) grass seed (UPC code 0002), $0.35 per kg\n" 
					+ "3) nails (UPC code 0003), $.02 per nail\n" + "4) lawn cutting service, $100\n" + 
					"5) professional interlock laying $44 per hour\n" + "6) checkout\n" + "7) exit");
			option = input.nextLine();
			
			if(Integer.parseInt(option) == 1){
				//add the input quantity in unit of garden hose item to the shopping cart
				askAgain = true;
				do{
					System.out.print("\nPlease enter the quantity: ");
					quantity = input.nextLine();
				
					if(Integer.parseInt(quantity) > 0){
						for(int x = 0; x < Integer.parseInt(quantity); x++){
							if(limit < purchaseItem.length && purchaseItem[limit] == null){
								purchaseItem[limit] = new Item("0001", "garden hose", 4.99);
								limit++;
							}else{
								System.out.println("\nShopping cart is full. Please go through the checkout process.");
								limit--;
								x = Integer.parseInt(quantity);
							}
							if(limit == purchaseItem.length){
								limit--;
							}
						}
						askAgain = false;
					}
				}while(askAgain);
			}else if(Integer.parseInt(option) == 2){s
				//add the input quantity in weight of grass seed item to the shopping cart
				askAgain = true;
				do{
					System.out.print("\nPlease enter the quantity (at least 1 kg): "); //but showing (at least)
					quantity = input.nextLine();
					
					if(Double.parseDouble(quantity) >= 1.0){
						if(limit < purchaseItem.length && purchaseItem[limit] == null){
							purchaseItem[limit] = new ScaleGood("0002", "grass seed", Double.parseDouble(quantity), 0.35);
							limit++;
						}else{
							System.out.println("\nShopping cart is full. Please go through the checkout process.");
							limit--;
						}
						if(limit == purchaseItem.length){
							limit--;
						}
						askAgain = false;
					}
				}while(askAgain);
			}else if(Integer.parseInt(option) == 3){
				//add the input quantity per pack of nails item to the shopping cart
				askAgain = true;
				do{
					System.out.println("\nPlease select the followings:\n\n" + "1) 50 units per pack\n" + "2) 90 units per pack");
					quantity = input.nextLine();
					
					if(Integer.parseInt(quantity) == 1){
						do{
							System.out.print("Please enter the quantity of 50 units per pack: ");
							quantity = input.nextLine();
						
							if(Integer.parseInt(quantity) > 0){
								for(int x = 0; x < Integer.parseInt(quantity); x++){
									if(limit < purchaseItem.length && purchaseItem[limit] == null){
										purchaseItem[limit] = new BulkGood("0003", "nails", 50, 0.02);
										limit++;
									}else{
										System.out.println("\nShopping cart is full. Please go through the checkout process.");
										limit--;
										x = Integer.parseInt(quantity);
									}
									if(limit == purchaseItem.length){
										limit--;
									}
								}
								askAgain = false;
							}
						}while(askAgain);
					}else if(Integer.parseInt(quantity) == 2){
						do{
							System.out.print("Please enter the quantity of 90 units per pack: ");
							quantity = input.nextLine();
						
							if(Integer.parseInt(quantity) > 0){
								for(int x = 0; x < Integer.parseInt(quantity); x++){
									if(limit < purchaseItem.length && purchaseItem[limit] == null){
										purchaseItem[limit] = new BulkGood("0003", "nails", 90, 0.02);
										limit++;
									}else{
										System.out.println("\nShopping cart is full. Please go through the checkout process.");
										limit--;
										x = Integer.parseInt(quantity);
									}
									if(limit == purchaseItem.length){
										limit--;
									}
								}
								askAgain = false;
							}
						}while(askAgain);
					}
				}while(askAgain);
			}else if(Integer.parseInt(option) == 4){
				//add the input number of requests for lawn cutting service to the shopping cart
				askAgain = true;
				do{
					System.out.print("\nPlease enter the number of service requests: ");
					quantity = input.nextLine();
					
					if(Integer.parseInt(quantity) > 0){
						for(int x = 0; x < Integer.parseInt(quantity); x++){
							if(limit < purchaseItem.length && purchaseItem[limit] == null){
								purchaseItem[limit] = new Service("SID01", "lawn cutting", 100.0, "whole-yard lawn cutting service");
								limit++;
							}else{
								System.out.println("\nShopping cart is full. Please go through the checkout process.");
								limit--;
								x = Integer.parseInt(quantity);
							}
							if(limit == purchaseItem.length){
								limit--;
							}
						}
						askAgain = false;
					}
				}while(askAgain);
			}else if(Integer.parseInt(option) == 5){
				//add the input hours of interlock laying service to the shopping cart
				askAgain = true;
				do{
					System.out.print("\nPlease enter the number of service hours: ");
					quantity = input.nextLine();
					
					if(Integer.parseInt(quantity) > 0){
						if(limit < purchaseItem.length && purchaseItem[limit] == null){
							purchaseItem[limit] = new HourlyService("SID02", "professional interlock laying", "interlock laying service for the whole house", 
									Integer.parseInt(quantity), 44.0);
									limit++;
						}else{
							System.out.println("\nShopping cart is full. Please go through the checkout process.");
							limit--;
						}
						if(limit == purchaseItem.length){
							limit--;
						}
						askAgain = false;
					}
				}while(askAgain);
			}else if(Integer.parseInt(option) == 6){
				//process every single item in the shopping cart at checkout and issue an invoice from the transaction
				askAgain = true;
				try{
					FileWriter writer = new FileWriter("Invoice#" + invoiceNum + ".txt", true);
					BufferedWriter out = new BufferedWriter(writer);
					int lineNumber = 1;
					double grandTotal = 0.0;
					Date date = new Date(System.currentTimeMillis());
					SimpleDateFormat formatter = new SimpleDateFormat("'Issue Date: 'dd-MM-yyyy");
					Locale locale = new Locale("en", "US");
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
					
					out.write("                                   Invoice                         " + formatter.format(date));
					out.newLine();
					out.newLine();
					out.write("Item Number     Item Name     Price Before Tax     Price After Tax     Quantity     Sub Total");
					out.newLine();
					out.newLine();
					
					for(int x = 0; x < purchaseItem.length && purchaseItem[x] != null; x++){
						int itemCount = 0;
						for(int y = 0; y < purchaseItem.length && purchaseItem[y] != null; y++){
							if(!purchaseItem[y].isFlagged){
								if(purchaseItem[x].getItemPrice() == purchaseItem[y].getItemPrice()){
									purchaseItem[y].isFlagged = true;
									itemCount++;
								}
							}
						}
						if(itemCount > 0){
							if(purchaseItem[x].isTaxable && purchaseItem[x] instanceof HourlyService){
								grandTotal += (purchaseItem[x].getPriceAfterTax(serviceTax) * itemCount);
								out.write(lineNumber + ". " + purchaseItem[x].getItemNumber() + "          " + purchaseItem[x].getItemName() + "          " + currencyFormatter.format(purchaseItem[x].getItemPrice()) + "          " + 
										currencyFormatter.format(purchaseItem[x].getPriceAfterTax(serviceTax)) + "          " + itemCount + "          " + currencyFormatter.format(purchaseItem[x].getPriceAfterTax(serviceTax) * itemCount));
								out.newLine();
							}else if(purchaseItem[x].isTaxable){
								grandTotal += (purchaseItem[x].getPriceAfterTax(goodTax) * itemCount);
								out.write(lineNumber + ". " + purchaseItem[x].getItemNumber() + "          " + purchaseItem[x].getItemName() + "          " + currencyFormatter.format(purchaseItem[x].getItemPrice()) + "          " + 
										currencyFormatter.format(purchaseItem[x].getPriceAfterTax(goodTax)) + "          " + itemCount + "          " + currencyFormatter.format(purchaseItem[x].getPriceAfterTax(goodTax) * itemCount));
								out.newLine();
							}else if(!purchaseItem[x].isTaxable){
								grandTotal += (purchaseItem[x].getItemPrice() * itemCount);
								out.write(lineNumber + ". " + purchaseItem[x].getItemNumber() + "          " + purchaseItem[x].getItemName() + "          " + currencyFormatter.format(purchaseItem[x].getItemPrice()) + "                         " + itemCount + 
										 "          " + currencyFormatter.format(purchaseItem[x].getItemPrice() * itemCount));
								out.newLine();
							}
							lineNumber++;
						}
					}
					
					out.newLine();
					out.newLine();
					out.write("Grand Total: " + currencyFormatter.format(grandTotal));
					out.close();
					System.out.println("\nInvoice is issued successfully.");
					do{
						System.out.println("You want to shop again? y/n");
						option = input.nextLine();
						
						if(option.toLowerCase().equals("y")){
							limit = 0;
							invoiceNum++;
							for(int x = 0; x < purchaseItem.length; x++){
								purchaseItem[x] = null;
							}
							askAgain = false;
						}else if(option.toLowerCase().equals("n")){
							System.out.println("\nThank you. Have a nice day");
							limit = purchaseItem.length;
							askAgain = false;
						}
					}while(askAgain);
				}catch(Exception e){
					System.out.println("\nInvoice cannot be issued. Please try again.");
					limit--;
				}
			}else if(Integer.parseInt(option) == 7){
				//prevent the user from leaving the store application if the shopping cart is non-empty
				if(purchaseItem[0] == null){
					System.out.println("\nThank you. Please come again.");
					limit = purchaseItem.length;
				}else{
					System.out.println("\nShopping cart is not empty. Please go through the checkout process.");
					limit--;
				}
			}
		}
		
		purchaseItem = null;
		input.close();
	}

}
