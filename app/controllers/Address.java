package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Address extends Controller {

  
public static Result newStockItem() {
    
    Form<models.Address> addressForm = form(models.Address.class).bindFromRequest();
    if(addressForm.hasErrors()) {
      
      return badRequest("The Product ID and name is required.");
    }
    models.Address address = addressForm.get();
    address.save();
    return ok(address.toString());
     
    
  }
public static Result index() {
  
  List<models.Address> addresses = models.Address.find().findList();
  return ok(addresses.isEmpty() ? "No Addresses" : addresses.toString());

}

public static Result details(String addressId) {
  
  models.Address address = models.Address.find().where().eq("addressId", addressId).findUnique();
  return (address == null) ? notFound("No addresses found") : ok(address.toString());
  
}
public static Result delete(String addressId) {
  
  models.Address address = models.Address.find().where().eq("addressId", addressId).findUnique();
  if (address != null) {
    address.delete();
  }
  return ok();
}
}
