import java.util.ArrayList;
import java.util.List;

public class ClothingSite {
	public static void main(String[] args) {
		
		ShirtItem shirtItem = new ShirtItem();
		JacketItem jacketItem = new JacketItem();
		
		ShirtItem shirtItem1 = new ShirtItem(); // one more shirtItem
		//checkOutItem(shirtItem);
		
		List<ClothingItem> clothingItems = new ArrayList<>();
		
		//clothingItems.add(shirtItem);
		//clothingItems.add(jacketItem);
		
		clothingItems.add(shirtItem1); // will get error
		
		checkOutAllItem(clothingItems);
	}
	
	//public static void checkOutAllItem(List<ClothingItem> clothingItems){
	public static void checkOutAllItem(List<? extends ClothingItem> clothingItems){ // what is mean by ? here, wind-card
		for(ClothingItem clothingItem: clothingItems) {
			checkOutItem(clothingItem);
		}
	}
	
	public static void checkOutItem(ClothingItem clothingItem){
		System.out.println("Item purchased:" + clothingItem.getName() + " for price :" + clothingItem.getPrice());
	}
}