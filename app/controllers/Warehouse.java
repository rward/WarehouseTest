package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Warehouse extends Controller {

public static Result newWarehouse() {
    
    Form<models.Warehouse> warehouseForm = form(models.Warehouse.class).bindFromRequest();
    if(warehouseForm.hasErrors()) {
      
      return badRequest("The Product ID and name is required.");
    }
    models.Warehouse warehouse = warehouseForm.get();
    warehouse.save();
    return ok(warehouse.toString());
     
    
  }
 public static Result index() {
   
   List<models.Warehouse> warehouses = models.Warehouse.find().findList();
   if(warehouses.isEmpty()) {
     return ok("No Warehouses");
   }
   else
   {
     return ok(warehouses.toString());
     
   }
 }
 
 public static Result details(String warehousesId) {
   
   models.Warehouse warehouses = models.Warehouse.find().where().eq("warehouseId", warehousesId).findUnique();
   return (warehouses == null) ? notFound("No Warehouses found") : ok(warehouses.toString());
   
 }
 public static Result delete(String warehousesId) {
   
   models.Warehouse warehouse = models.Warehouse.find().where().eq("warehouseId", warehousesId).findUnique();
   if (warehouse != null) {
     warehouse.delete();
   }
   return ok();
 }
 
  
  
}
