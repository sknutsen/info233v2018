package info233v2018.labuke2;

public class OnlineShopper {
	public static void main(String[] args) {
		Item[] items = {new Item("Bird feeder", 2050),
						new Item("Bird feeder", 2050),
						new Item("Bird feeder", 2050),
						new Item("Bird feeder", 2050),};
		BagInterface<Item> shoppingCart = new Bag<>();
		int totalCost = 0;
		
		//statements that add selected items to the shopping cart
		for (int i = 0; i < items.length; i++) {
			Item nextItem = items[i];
			shoppingCart.add(nextItem);
			totalCost = totalCost + nextItem.getPrice();
		}
		
		//simulate checkout
		while (!shoppingCart.isEmpty()) {
			System.out.println(shoppingCart.remove());
		}
		
		System.out.println("Total cost: " + "\t$" + totalCost / 100 + "." + totalCost % 100);
	}
	
	
}
