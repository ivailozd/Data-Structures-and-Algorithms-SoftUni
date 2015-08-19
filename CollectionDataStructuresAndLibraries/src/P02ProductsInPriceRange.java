import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public class P02ProductsInPriceRange {
	
	private static final SortedMap<Integer, List<Product>> PRODUCTS = new TreeMap<>();
	private static final Random RANDOM = new Random();
	private static final P02ProductsInPriceRange x = new P02ProductsInPriceRange();
	private static final int TOTAL_ITEMS = 500000;
	private static final int PRODUCTS_COUNT = 20;
	private static final int UPPER_BOUND = 600;
	private static final int DOWN_BOUND = 400;
	
	public static void main(String[] args) {
		int price = 0;
		List<Product> newList = null;
		for (int i = 0; i < TOTAL_ITEMS; i++) {
			price = RANDOM.nextInt(100000);
			if (PRODUCTS.containsKey(price)) {
				PRODUCTS.get(price).add(x.new Product("Product#" + i, price));
			} else {
				newList = new LinkedList<>();
				newList.add(x.new Product("Product#" + i, price));
				PRODUCTS.put(price, newList);
			}
		}
		
		final long startTime = System.currentTimeMillis();
		SortedMap<Integer, List<Product>> subRange = PRODUCTS.subMap(DOWN_BOUND, UPPER_BOUND);
		SortedMap<Integer, Product> firstNth = new TreeMap<>();
		int counter = 0;
		for (Entry<Integer, List<Product>> entry : subRange.entrySet()) {
			if (counter < PRODUCTS_COUNT) {
				for (Product product : entry.getValue()) {
					firstNth.put(product.getPrice(), product);
					
				}
				
				counter++;
			} else {
				break;
			}
		}
		
		final long endTime = System.currentTimeMillis();
		System.out.println("Total items: " + TOTAL_ITEMS);
		System.out.println("First " + PRODUCTS_COUNT + " items in range [" + DOWN_BOUND + ":" + UPPER_BOUND + "):");
		for (Map.Entry<Integer, Product> entry : firstNth.entrySet()) {
			System.out.println(entry.getValue().getName() + ":" + entry.getKey());
		}
		
		System.out.println("Search execution time: " + (endTime - startTime) + " milliseconds" );
	}
	
	public class Product {
		private int price;
		private String name;
		
		public Product(String name, int price) {
			this.name = name;
			this.price = price;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
