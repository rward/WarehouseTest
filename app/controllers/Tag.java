package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Tag extends Controller {

  
  public static Result newTag() {
    
    Form<models.Tag> tagForm = form(models.Tag.class).bindFromRequest();
    if(tagForm.hasErrors()) {
      
      return badRequest("The Product ID and name is required.");
    }
    models.Tag warehouse = tagForm.get();
    warehouse.save();
    return ok(warehouse.toString());
     
    
  }
  public static Result index() {
    
    List<models.Tag> tags = models.Tag.find().findList();
    return ok(tags.isEmpty() ? "No Tags" : tags.toString());

  }
  
  public static Result details(String tadId) {
    
    models.Tag tag = models.Tag.find().where().eq("tagId", tadId).findUnique();
    return (tag == null) ? notFound("No tag found") : ok(tag.toString());
    
  }
  public static Result delete(String tadId) {
    
    models.Tag tag = models.Tag.find().where().eq("tagId", tadId).findUnique();
    if (tag != null) {
      tag.delete();
    }
    return ok();
  }
}
