import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;
import static play.test.Helpers.status;
import java.util.HashMap;
import java.util.Map;
import models.Product;
import models.Tag;
import models.Warehouse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;

public class ControllerTest {
   
  
  private FakeApplication application;
  
  
  @Before
  public void startApp() {
    application = fakeApplication(inMemoryDatabase());
    start(application);
     
  }
  
  @After
  public void stopApp() {
    
    stop(application);
  }
  @Test
  public void TestProductController() {
    
    //Test Get 
    Result result = callAction(controllers.routes.ref.Product.index());
    assertTrue("Empty Products", contentAsString(result).contains("No Products"));
    
    //Test GET /product/on a database containing a single product
    String productId = "Product-01";
    Product product = new Product(productId, "French Press", "Coffee Maker");
    product.save();
    result = callAction(controllers.routes.ref.Product.index());
    assertTrue("One Product", contentAsString(result).contains(productId));
    
    //Test GET /product/Product-01
    result = callAction(controllers.routes.ref.Product.details(productId));
    assertTrue("Product detail", contentAsString(result).contains(productId));
    
    //Test GET /product/BadProductId  and make sure we get a 404
    result = callAction(controllers.routes.ref.Product.details("BadProductId"));
    assertEquals("Product detail (bad)", NOT_FOUND ,status(result));
    
    Map<String,String> productData = new HashMap<String, String>();
    productData.put("productId", "Product-02");
    productData.put("name", "Baby Gaggia");
    productData.put("description", "Espresso machine");
    
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(productData);
    result = callAction(controllers.routes.ref.Product.newProduct(),request);
    assertEquals("Create new product",OK, status(result));
    
    //request duplicate addition
    //request = fakeRequest();
    //request.withFormUrlEncodedBody(productData);
    //result = callAction(controllers.routes.ref.Product.newProduct(),request);
    //assertEquals("Create duplicate product fails",BAD_REQUEST, status(result));
    
    // Test POST /products (with simulated, invalid form data
    request = fakeRequest();
    result = callAction(controllers.routes.ref.Product.newProduct(),request);
    assertEquals("Create bad product fails",BAD_REQUEST, status(result));
    
    
    // Test DELETE /products/Product-01(a valid ProductId)
    result = callAction(controllers.routes.ref.Product.delete(productId));
    assertEquals("Delete current product OK",OK, status(result));
    result = callAction(controllers.routes.ref.Product.details(productId));
    assertEquals("Delete current product OK",NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.Product.delete(productId));
    assertEquals("Delete current product OK",OK, status(result));
    
    
  }
  @Test
  public void TestStockItemController() {
    
    Result result = callAction(controllers.routes.ref.StockItem.index());
    assertTrue("Empty StockItem", contentAsString(result).contains("No StockItems"));
  //Test GET /product/on a database containing a single product
    String productId = "Product-01";
    Product product = new Product(productId, "French Press", "Coffee Maker");
    product.save();
    
    Map<String,String> stockItemData = new HashMap<String, String>();
    stockItemData.put("productId", "Product-01");
    stockItemData.put("stockItemId", "Product-01SKU01");
   
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(stockItemData);
    result = callAction(controllers.routes.ref.StockItem.newStockItem(),request);
    assertEquals("Create new stockItem",OK, status(result));
    
    stockItemData = new HashMap<String, String>();
    stockItemData.put("productId", "Product-02");
    stockItemData.put("stockItemId", "Product-02SKU01");
   
    request = fakeRequest();
    request.withFormUrlEncodedBody(stockItemData);
    result = callAction(controllers.routes.ref.StockItem.newStockItem(),request);
    assertEquals("BAD_REQUEST invalid Product Id",BAD_REQUEST, status(result));
    
    
    
  }
  @Test
  public void TestTagController() {
    
    Result result = callAction(controllers.routes.ref.Tag.index());
    assertTrue("Empty Tag", contentAsString(result).contains("No Tags"));
    
    //Test GET /product/on a database containing a single product
    String tagId = "Tag-01";
    Tag tag = new Tag(tagId);
    tag.save();
    result = callAction(controllers.routes.ref.Tag.index());
    assertTrue("One Tag", contentAsString(result).contains(tagId));
    
    //Test GET /product/Product-01
    result = callAction(controllers.routes.ref.Tag.details(tagId));
    assertTrue("tag detail", contentAsString(result).contains(tagId));
    
    //Test GET /product/BadProductId  and make sure we get a 404
    result = callAction(controllers.routes.ref.Tag.details("BadTagId"));
    assertEquals("Tag detail (bad)", NOT_FOUND ,status(result));
    
    Map<String,String> tagData = new HashMap<String, String>();
    tagData.put("tagId", "Tag-02");
        
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(tagData);
    result = callAction(controllers.routes.ref.Tag.newTag(),request);
    assertEquals("Create new tag",OK, status(result));
    
    //request duplicate addition
    //request = fakeRequest();
    //request.withFormUrlEncodedBody(productData);
    //result = callAction(controllers.routes.ref.Product.newProduct(),request);
    //assertEquals("Create duplicate product fails",BAD_REQUEST, status(result));
    
    // Test POST /products (with simulated, invalid form data
    request = fakeRequest();
    result = callAction(controllers.routes.ref.Tag.newTag(),request);
    assertEquals("Create bad product fails",BAD_REQUEST, status(result));
    
    
    // Test DELETE /products/Product-01(a valid ProductId)
    result = callAction(controllers.routes.ref.Tag.delete(tagId));
    assertEquals("Delete current product OK",OK, status(result));
    result = callAction(controllers.routes.ref.Tag.details(tagId));
    assertEquals("Delete current product OK",NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.Tag.delete(tagId));
    assertEquals("Delete current product OK",OK, status(result));
  
  }
  
  @Test
  public void TestAddessController() {
    
    Result result = callAction(controllers.routes.ref.Address.index());
    assertTrue("Empty Address", contentAsString(result).contains("No Addresses"));
  }
  @Test
  public void TestWarehouseController() {
    
    Result result = callAction(controllers.routes.ref.Warehouse.index());
    assertTrue("Empty Warehouses", contentAsString(result).contains("No Warehouses"));
    
    //Test GET /product/on a database containing a single product
    String warehouseId = "Warehouse-01";
    Warehouse warehouse = new Warehouse(warehouseId,"warehouseName");
    warehouse.save();
    result = callAction(controllers.routes.ref.Warehouse.index());
    
    
    assertTrue("One Warehouse", contentAsString(result).contains(warehouseId));
    
    //Test GET /product/Product-01
    result = callAction(controllers.routes.ref.Warehouse.details(warehouseId));
    assertTrue("warehouse detail", contentAsString(result).contains(warehouseId));
    
    //Test GET /product/BadProductId  and make sure we get a 404
    result = callAction(controllers.routes.ref.Warehouse.details("BadWarehouseId"));
    assertEquals("warehouse detail (bad)", NOT_FOUND ,status(result));
    
    Map<String,String> warehouseData = new HashMap<String, String>();
    warehouseData.put("warehouseId", "Warehouse-02");
    warehouseData.put("name", "warehouseName");   
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(warehouseData);
    result = callAction(controllers.routes.ref.Warehouse.newWarehouse(),request);
    assertEquals("Create new tag",OK, status(result));
    
    //request duplicate addition
    //request = fakeRequest();
    //request.withFormUrlEncodedBody(productData);
    //result = callAction(controllers.routes.ref.Product.newProduct(),request);
    //assertEquals("Create duplicate product fails",BAD_REQUEST, status(result));
    
    // Test POST /products (with simulated, invalid form data
    request = fakeRequest();
    result = callAction(controllers.routes.ref.Warehouse.newWarehouse(),request);
    assertEquals("Create bad product fails",BAD_REQUEST, status(result));
    
    
    // Test DELETE /products/Product-01(a valid ProductId)
    result = callAction(controllers.routes.ref.Warehouse.delete(warehouseId));
    assertEquals("Delete current product OK",OK, status(result));
    result = callAction(controllers.routes.ref.Warehouse.details(warehouseId));
    assertEquals("Delete current product OK",NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.Warehouse.delete(warehouseId));
    assertEquals("Delete current product OK",OK, status(result));
    
  }
}
