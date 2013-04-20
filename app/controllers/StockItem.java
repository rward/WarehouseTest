package controllers;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;
import javax.persistence.criteria.Fetch;
import models.Product;
import static play.data.Form.form;


public class StockItem extends Controller {

  
 public static Result newStockItem() {
    
    Form<models.StockItem> stockItemForm = form(models.StockItem.class).bindFromRequest();
    if(stockItemForm.hasErrors()) {
      
      return badRequest("The Product ID and name is required.");
    }
    models.StockItem stockItem = stockItemForm.get();
    
    
    //System.out.println("Query"+ stockItem.getProductId());
    
    
    Product product = Product.find().where().eq("productId", stockItem.getProductId()).findUnique();
    
    if(product == null) {
      
      return badRequest("Not a valid Product ID.");
    }
    System.out.println(product);
    stockItem.setProduct(product);
    stockItem.save();
    return ok(stockItem.toString());
     
    
  }
 public static Result index() {
   
   List<models.StockItem> stockItems = models.StockItem.find().findList();
   return ok(stockItems.isEmpty() ? "No StockItems" : stockItems.toString());

 }
 
 public static Result details(String stockItemId) {
   
   models.StockItem stockItem = models.StockItem.find().where().eq("stockItemId", stockItemId).findUnique();
   return (stockItem == null) ? notFound("No stockItems found") : ok(stockItem.toString());
   
 }
 public static Result delete(String stockItemId) {
   
   models.StockItem stockItem = models.StockItem.find().where().eq("stockItemId", stockItemId).findUnique();
   if (stockItem != null) {
     stockItem.delete();
   }
   return ok();
 }
 
}
