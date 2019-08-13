package com.perscholas.dealfinder.main;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.apache.jasper.tagplugins.jstl.core.Set;

import com.perscholas.dealfinder.entities.Product;
import com.perscholas.dealfinder.entities.Store;
import com.perscholas.dealfinder.entities.TransactionDetail;
import com.perscholas.dealfinder.entities.User;
import com.perscholas.dealfinder.services.ProductService;
import com.perscholas.dealfinder.services.StoreService;
import com.perscholas.dealfinder.services.TransactionDetailService;
import com.perscholas.dealfinder.services.UserService;

/**
 * Uses the UserService, ProductService, and StoreService to load Initial Data
 * into the Database
 *
 */
public class DealFinderDataLoader {

	// Create UserService, ProductService, and StoreService instances
	static UserService userService = new UserService();
	static ProductService productService = new ProductService();
	static StoreService storeService = new StoreService();

	/* Main Method runs the LoadData method */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadData();
	}

	/* Runs the methods to load all the Initial Data into the Database */
	private static void loadData() {
		// TODO Auto-generated method stub
		loadProducts();
		loadStores();
		loadUsers();
		loadStoreProducts();

	}

	/* Loads initial Products into the Database */
	private static void loadProducts() {
		String[] manufacturers = { "Store Brand", "Walmart", "Target", "GenericBrand", "Sharp", "Brand X", "Post",
				"General", "Kraft", "Sportsman" };
		String[] manufacturerSites = { "www.storebrand.com", "www.walmart.com", "www.target.com",
				"www.genericbrand.com", "www.sharp.com", "www.brandx.com", "www.post.com", "www.general.com",
				"www.kraft.com", "www.sportsman.com" };

		List<Product> productList = new ArrayList<>();
		productList.add(new Product("Apple Juice", 1.69));
		productList.add(new Product("Apple Sauce", 1.29));
		productList.add(new Product("Apples", 2.99));
		productList.add(new Product("Aluminum Foil", 1.99));
		productList.add(new Product("Bacon", 2.99));
		productList.add(new Product("Bagles, plain", 1.49));
		productList.add(new Product("Baguette/French Bread", 1.49));
		productList.add(new Product("Barbeque Sauce", 0.89));
		productList.add(new Product("Beef Stew Meat", 4.19));
		productList.add(new Product("Beer, bottle, pilsener, import", 5.99));
		productList.add(new Product("Bleach", 1.19));
		productList.add(new Product("Brats, 4-6 pack", 1.99));
		productList.add(new Product("Bread Crumbs", 0.89));
		productList.add(new Product("Bread, 12-grain/multi-grain", 1.89));
		productList.add(new Product("Breakfast Sausage, frozen", 0.89));
		productList.add(new Product("Butter, real, unsalted/salted", 2.29));
		productList.add(new Product("Carrots, whole", 1.29));
		productList.add(new Product("Cereal, Müsli style", 2.29));
		productList.add(new Product("Cheery Pie Filling", 1.79));
		productList.add(new Product("Cheese Singles (imitation)", 0.99));
		productList.add(new Product("Cheese, block (regular)", 1.99));
		productList.add(new Product("Cheese, Boursin", 2.99));
		productList.add(new Product("Cheese, Brie 1", 2.99));
		productList.add(new Product("Cheese, Brie 2", 2.39));
		productList.add(new Product("Cheese, Parmesan, jar", 2.39));
		productList.add(new Product("Cheese, shredded, Mexican style", 2.79));
		productList.add(new Product("Cheese, shredded, Mozarella", 2.99));
		productList.add(new Product("Cheese, sliced, deli", 1.99));
		productList.add(new Product("Cheese, sliced, regular", 1.99));
		productList.add(new Product("Cheese, wedge, deli", 2.79));
		productList.add(new Product("Chicken Breasts, frozen", 5.49));
		productList.add(new Product("Chicken Nuggets, frozen", 3.99));
		productList.add(new Product("Chicken Tenderloins, frozen", 5.99));
		productList.add(new Product("Chips, potato chips", 1.49));
		productList.add(new Product("Chocolate Chip Cookie Dough", 1.99));
		productList.add(new Product("Cocoa Rice Cereal", 1.69));
		productList.add(new Product("Coconut Milk", 2.49));
		productList.add(new Product("Coffee", 4.99));
		productList.add(new Product("Cold Cuts, ham or chicken", 3.29));
		productList.add(new Product("Cold Cuts, turkey variety", 3.29));
		productList.add(new Product("Corn Flakes, regular", 1.69));
		productList.add(new Product("Corn Flakes, sugar frosted", 1.79));
		productList.add(new Product("Cottage Cheese", 1.99));
		productList.add(new Product("Cream Cheese", 1.19));
		productList.add(new Product("Cream, heavy whipping", 1.79));
		productList.add(new Product("Croissants", 2.49));
		productList.add(new Product("Diapers", 0.79));
		productList.add(new Product("Dish soap", 0.39));
		productList.add(new Product("Duct tape ", 2.89));
		productList.add(new Product("Eggs, cage free", 1.19));
		productList.add(new Product("Eggs, regular", 1.09));
		productList.add(new Product("Fish 1, frozen (flounder)", 3.99));
		productList.add(new Product("Fish 2, frozen (tilapia)", 4.49));
		productList.add(new Product("Flour, all purpose", 1.59));
		productList.add(new Product("Flour, self rising", 1.59));
		productList.add(new Product("French Fries, frozen", 1.89));
		productList.add(new Product("Fruit 1, frozen (strawberries)", 1.99));
		productList.add(new Product("Fruit 2, frozen (cherries and/or other)", 2.99));
		productList.add(new Product("Granola Bars, high fiber", 1.89));
		productList.add(new Product("Granola Bars, regular", 1.59));
		productList.add(new Product("Grapes, red", 2.99));
		productList.add(new Product("Green Beans, canned", 0.59));
		productList.add(new Product("Ground Beef (chuck, 80/20)", 2.89));
		productList.add(new Product("Ground Beef (sirloin, 90/10)", 3.49));
		productList.add(new Product("Ground Beef, frozen", 2.79));
		productList.add(new Product("Ground Turkey", 2.99));
		productList.add(new Product("Ground Turkey, frozen", 1.69));
		productList.add(new Product("Ham, boneless", 2.99));
		productList.add(new Product("Hand soap", 0.89));
		productList.add(new Product("Honey", 4.49));
		productList.add(new Product("Honey Crunch Oats", 1.89));
		productList.add(new Product("Hot Dog Buns", 0.99));
		productList.add(new Product("Hot Dogs, beef", 2.29));
		productList.add(new Product("Ice Cream, premium", 2.49));
		productList.add(new Product("Ice Cream, simple", 1.99));
		productList.add(new Product("Jalopeño Peppers, canned", 1.19));
		productList.add(new Product("Ketchup", 1.49));
		productList.add(new Product("Kielbasa", 1.99));
		productList.add(new Product("Lemons", 1.99));
		productList.add(new Product("Lettuce, iceberg", 0.99));
		productList.add(new Product("Mandarin Oranges, canned", 0.49));
		productList.add(new Product("Margarine/Spread", 0.99));
		productList.add(new Product("Mayonnaise", 2.19));
		productList.add(new Product("Measuring spoons", 1.19));
		productList.add(new Product("Milk", 1.99));
		productList.add(new Product("Milk Chocolate Bar", 1.49));
		productList.add(new Product("Milk, organic", 2.19));
		productList.add(new Product("Mushrooms, Button, whole", 0.99));
		productList.add(new Product("Mushrooms, stems/pieces", 0.59));
		productList.add(new Product("Mustard, dijon", 0.99));
		productList.add(new Product("Mustard, yellow", 0.69));
		productList.add(new Product("Nutella", 3.49));
		productList.add(new Product("Oat Meal", 1.49));
		productList.add(new Product("Oil, Canola, 100% pure", 2.69));
		productList.add(new Product("Oil, Olive, 100% pure", 2.99));
		productList.add(new Product("Oil, Olive, extra virgin", 3.49));
		productList.add(new Product("Onions, yellow", 1.69));
		productList.add(new Product("Orange Juice, not from concentrate", 2.39));
		productList.add(new Product("Oranges", 2.99));
		productList.add(new Product("Pancetta or Ham, diced", 2.39));
		productList.add(new Product("Paper Towles", 5.99));
		productList.add(new Product("Pasta, Egg Noodles", 0.99));
		productList.add(new Product("Pasta, Ellbow Maccaroni", 1.69));
		productList.add(new Product("Pasta, Penne Rigate", 0.99));
		productList.add(new Product("Pasta, Rotini/Rotelle/Fusilli", 1.49));
		productList.add(new Product("Pasta, Spaghetti", 1.59));
		productList.add(new Product("Peanut Butter", 2.29));
		productList.add(new Product("Peanuts, roasted", 2.69));
		productList.add(new Product("Pears", 1.99));
		productList.add(new Product("Pepper, black, ground", 1.99));
		productList.add(new Product("Pizza Dough", 1.99));
		productList.add(new Product("Pizza, frozen (premium)", 3.89));
		productList.add(new Product("Pizza, frozen (simple)", 1.99));
		productList.add(new Product("Plastic Wrap", 1.49));
		productList.add(new Product("Pork Tenderloin, small", 3.99));
		productList.add(new Product("Potatoes, gold (Yukon)", 2.99));
		productList.add(new Product("Potatoes, red", 2.49));
		productList.add(new Product("Potatoes, Russet ", 2.89));
		productList.add(new Product("Preserves, strawberry", 1.59));
		productList.add(new Product("Pretzels", 1.29));
		productList.add(new Product("Red Cabbage", 3.49));
		productList.add(new Product("Ribs, Baby Back (pork, unseasoned)", 3.49));
		productList.add(new Product("Ribs, Spare Back (pork, unseasoned)", 3.49));
		productList.add(new Product("Rice, instant, white", 2.49));
		productList.add(new Product("Rice, regular", 1.69));
		productList.add(new Product("Salad Dressing", 1.39));
		productList.add(new Product("Salami, sliced", 2.79));
		productList.add(new Product("Salami, whole (1)", 3.49));
		productList.add(new Product("Salsa, medium", 1.49));
		productList.add(new Product("Salt, iodized", 0.35));
		productList.add(new Product("Soda, bottle", 0.69));
		productList.add(new Product("Soda, bottle (Coke product)", 2.29));
		productList.add(new Product("Soda, can", 2.29));
		productList.add(new Product("Soda, can (Coke product)", 1.39));
		productList.add(new Product("Soup, Chicken Noodle, condensed", 0.59));
		productList.add(new Product("Soup, Chicken Noodle, regular", 1.39));
		productList.add(new Product("Soup, organic", 1.39));
		productList.add(new Product("Soup, Tomato, condensed", 0.49));
		productList.add(new Product("Soup, Tomato, regular", 1.29));
		productList.add(new Product("Sour Cream", 1.29));
		productList.add(new Product("Spaghetti Sauce", 1.19));
		productList.add(new Product("Spatula", 1.79));
		productList.add(new Product("Sugar, brown", 1.49));
		productList.add(new Product("Sugar, powdered", 1.49));
		productList.add(new Product("Sugar, white", 2.19));
		productList.add(new Product("Syrup, breakfast", 1.59));
		productList.add(new Product("Tea, green, bags", 1.19));
		productList.add(new Product("Tissues", 2.89));
		productList.add(new Product("Toilet paper", 1.99));
		productList.add(new Product("Tomato Paste", 0.39));
		productList.add(new Product("Tomato Sauce", 0.29));
		productList.add(new Product("Tomatoes, slicer", 1.59));
		productList.add(new Product("Tuna, chunky, in water", 0.59));
		productList.add(new Product("Tuna, solid, in water", 1.15));
		productList.add(new Product("Vanilla Extract, pure (not imitation)", 1.99));
		productList.add(new Product("Veggies 1, frozen (peas)", 0.89));
		productList.add(new Product("Veggies 2, frozen (other)", 0.65));
		productList.add(new Product("Waffles, frozen", 1.29));
		productList.add(new Product("Water, regular/drinking", 2.49));
		productList.add(new Product("Water, Sparkling/Mineral", 2.49));
		productList.add(new Product("White Bread/Toast, enriched", 0.79));
		productList.add(new Product("Wine, table, Cabernet", 2.89));
		productList.add(new Product("Wine, table, Chardonnay", 2.89));
		productList.add(new Product("Wine, table, White Zinfandel", 2.89));
		productList.add(new Product("Wipes", 2.89));
		productList.add(new Product("Yoghurt, Dannon (etc.)", 1.79));
		productList.add(new Product("Yogurt, Fage, 2%, plain", 1.19));
		productList.add(new Product("Yogurt, Fage, w/ flavor", 1.19));
		productList.add(new Product("Yogurt, flavored, cup", 0.39));
		productList.add(new Product("Yogurt, greek style, plain", 0.89));
		productList.add(new Product("Yogurt, plain", 1.99));

		for (Product product : productList) {
			int manufacturInt = (int) (Math.random() * 10);
			product.setManufacturer(manufacturers[manufacturInt]);
			product.setManufacturerSite(manufacturerSites[manufacturInt]);

			System.out.println(productService.createProduct(product) ? "Success" : "uh oh");
		}

	}

	/* Loads Stores into the Database */
	private static void loadStores() {

		System.out.println(storeService.createStore(new Store("Walmart", "Anywhere", "Double coupons")));
		System.out.println(storeService.createStore(new Store("Wegmans", "Westwood", "Triple coupons")));
		System.out.println(storeService.createStore(new Store("Target", "Anywhere", "Double coupons")));
		System.out.println(
				storeService.createStore(new Store("Stop and Shop", "Massachusetts", "Double coupons on Thursdays")));
		System.out.println(storeService.createStore(new Store("Shaws", "Massachusetts", "Triple coupons on Fridays")));
		System.out.println(storeService.createStore(new Store("Roche Bros.", "Newton", "No coupon policy")));
		System.out.println(storeService
				.createStore(new Store("Amazon", "Anywhere", "Double coupons, Triple coupons on Tuesdays")));
		System.out
				.println(storeService.createStore(new Store("Market Basket", "Weymouth", "Double coupons on Mondays")));
	}

	/* Loads Users into the Database */
	private static void loadUsers() {
		userService.createUser(new User("guest@gmail.com", "Guest", "Guest", "Password123"));
		userService.createUser(new User("dan@gmail.com", "Dan", "Aaaa", "Pw123"));
		userService.createUser(new User("jim@gmail.com", "Jim", "Bbbb", "Pw123"));
		userService.createUser(new User("gary@gmail.com", "Gary", "Kkkkk", "Pw123"));
		userService.createUser(new User("steve@gmail.com", "Steve", "Lllll", "Pw123"));
		userService.createUser(new User("khanh@gmail.com", "Khanh", "Nnnnnn", "Pw123"));
		userService.createUser(new User("kasidit@gmail.com", "Kasidit", "BBbbbb", "Pw123"));
		userService.createUser(new User("hitha@gmail.com", "Hitha", "Aaaaa", "Pw123"));
		userService.createUser(new User("simon@gmail.com", "Simon", "Yyyy", "Pw123"));
		userService.createUser(new User("ken@gmail.com", "Ken", "LlllLl", "Pw123"));
		userService.createUser(new User("antonio@gmail.com", "Antonio", "Mmmmm", "Pw123"));
	}

	/* Loads Products randomly into the Stores */
	private static void loadStoreProducts() {

		List<Store> storeList = storeService.getAllStores();

		for (Store store : storeList) {

			System.out.println(store);
			int prodListSize = (int) (Math.random() * 3 + 1) * 57;
			System.out.println(prodListSize);
			int index = 0;
			TreeSet<Integer> myProdIds = new TreeSet<>();
			while (index <= prodListSize) {
				int prodID = (int) (Math.random() * 171 + 1);
				if (myProdIds.add(prodID)) {
					storeService.addProductToStore(store, productService.getProductByID(prodID));
					index++;
				}
			}
		}

	}

}
