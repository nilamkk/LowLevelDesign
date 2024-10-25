package LLD.InventoryManagementSystem;

// Can't remove this error :(

import java.util.HashMap;
import java.util.Map;



class InventorySystem{
	
	static Map<String, Product> productMap = new HashMap<String, Product>();
	static Map<Location, Unit> locationMap = new HashMap<>();
	
	public static void addProduct( Product product ) {
		productMap.put( product.id, product );
	}
	
	public static Product getProduct( String productId ) {
		return productMap.get( productId );
	}
	
	public static void placeUnit(Unit unit) {
		for( Map.Entry<Location, Unit> entry: locationMap.entrySet() ) {
			// get lock on entry.getKey()
			if( entry.getValue() == null ) {
				unit.locationId = entry.getKey().id;
			}
			// release lock 
		}
	}
	
	public static void removeUnit(Product product) {
		for( Map.Entry<Location, Unit> entry: locationMap.entrySet() ) {
			// get lock on entry.getKey()
			if( entry.getValue() != null && product.id.equals( entry.getValue().productId ) ) {
				locationMap.remove( entry.getKey() );
			}
			// release lock 
		}
	}
	
	public static Map<Location, Unit> getShelvesStatus(){
		return locationMap;
	} 
	
	public static void updateStatus(Unit unit, Status status) {
		unit.status = status;
	}
}

class Unit{
	String id;
	String productId;
	String locationId;
	Status status;
}

class Product{
	String id;
	Double price;
	String description;
	double weight;
	Size size;
	
	public Product(String id, Double price, String description, double weight, Size size) {
		this.id = id;
		this.price = price;
		this.description = description;
		this.weight = weight;
		this.size = size;
	}
}

class Location{
	String id;
	Size type;
}

class User{
	
	public void addProduct() {
		InventorySystem.addProduct(new Product( "1", 3.0, "Don't mind", 9, Size.L ));
	}
	
	public void executeOrder(Order order) {
		for( Map.Entry<Product, Integer> item: order.productCount.entrySet() ) {
			for(int i=0; i<item.getValue(); i++) {
				InventorySystem.removeUnit( item.getKey() );							
			}
		}

	}
	
}

class Order{
	Map<Product, Integer> productCount = new HashMap<>();
}

enum Status{
	INVENTORY, TRANSIT, DELIVERY;
}

enum Size{
	S,M,L;
}




public class Demo {

	public static void main(String[] args) {

		System.out.println("o9iuytfgbnm,.");
		
	}

};