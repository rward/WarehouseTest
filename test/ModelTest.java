import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import models.Address;
import models.Product;
import models.StockItem;
import models.Tag;
import models.Warehouse;

import play.test.FakeApplication;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;


import java.util.List;

public class ModelTest {

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
    public void testModel() {
      
      // create 1 tag warehouse that's associated with 1 StockItem for 1 product
      Tag tag = new Tag("Tag");
      Product product = new Product("Product", "Description");
      product.tags.add(tag);
      tag.products.add(product);
      
      // Create 1 Warehouse that's associated with 1 StockItem for 1 product
      Warehouse warehouse = new Warehouse("Warehouse");
      
     Address address  = new Address("name", "street", "city", "zipcode");
     warehouse.address = address;
     warehouse.address.warehouse = warehouse;
      
      StockItem stockItem = new StockItem(warehouse, product, 100L) ;
      stockItem.warehouse = warehouse;
      
      //Persist the sample model by saving all the entities
      
      address.save();
      warehouse.save();
      tag.save();
      product.save();
      stockItem.save();
      
      
      //retrieve the entire model from the database
      List<Warehouse> warehouses = Warehouse.find().findList();
      List<Tag> tags = Tag.find().findList();
      List<Product> products = Product.find().findList();
      List<StockItem> stockItems = StockItem.find().findList();
      List<Address> adresses = Address.find().findList();
      // check that we've recovered all our entities.
      assertEquals("Checking warehouse", warehouses.size(), 1);
      assertEquals("Checking tags", tags.size(), 1);
      assertEquals("Checking products", products.size(), 1);
      assertEquals("Checking stock items", stockItems.size(), 1);
      assertEquals("Checking adresses items", adresses.size(), 1);
      
   // check that we've recovered all our entities.
      assertEquals("Warehouse- stockItem", warehouses.get(0).stockItems.get(0),stockItems.get(0));
      assertEquals("StockItem -Warehouse", stockItems.get(0).warehouse,warehouses.get(0));
      
      assertEquals("Product- StockItem", products.get(0).stockItems.get(0),stockItems.get(0));
      assertEquals("StockItem- Product", stockItems.get(0).product,products.get(0));
      
      assertEquals("Product- Tag", products.get(0).tags.get(0),tags.get(0));
      assertEquals("Tag- Product", tags.get(0).products.get(0),products.get(0));
      
      assertEquals("Warehouse - Address", warehouses.get(0).address,adresses.get(0));
      assertEquals("Address - Warehouse ", warehouses.get(0),adresses.get(0).warehouse);
      
      
      //Scan code to illustrate model manipulation with ORM.
      //Start in Java. Delete the tag from the original product instance.
      
      product.tags.clear();
      
      product.save();
      
      assertTrue("Previously reteived product still has tag", !products.get(0).tags.isEmpty());
      
      assertTrue("Fresh Product has no tag", Product.find().findList().get(0).tags.isEmpty());
      
      assertTrue("Fresh tag has no Products", Tag.find().findList().get(0).products.isEmpty());
      
      tag.delete();
      
      assertTrue("No more Tags in database", Tag.find().findList().isEmpty());
      
      
      
    }
    
    
}
