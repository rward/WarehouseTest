package models;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
@Entity
public class StockItem extends Model{

  public StockItem(Warehouse warehouse, Product product,String stockItemId, long quantity) {
   
    this.warehouse = warehouse;
    this.product = product;
    this.quantity = quantity;
    this.stockItemId = stockItemId;
  }

  
  public StockItem(String warehouseId, Product product, String stockItemId,  long quantity) {
    
    this.warehouse = Warehouse.find().where("warehouseId = " + warehouseId).findUnique();
    this.product = product;
    this.quantity = quantity;
    this.stockItemId = stockItemId;
  }
  
  /**
   * 
   */
  private static final long serialVersionUID = 1765615104131291592L;
  @Id
  public long primaryKey;
  @Required
  @Column(unique=true, nullable=false)
  private String stockItemId;
  
  @Required
  private String productId;
   
  /**
   * @return the productId
   */
  public String getProductId() {
    return productId;
  }


  /**
   * @param productId the productId to set
   */
  public void setProductId(String productId) {
    this.productId = productId;
  }

  @ManyToOne(cascade=CascadeType.ALL)
  public Warehouse warehouse;
  
  @ManyToOne(cascade=CascadeType.ALL)
  public Product product;
  
  public long quantity;
  
  
  public List<Tag> tags = new ArrayList<>();
  
  
  
  
  public static Finder<Long,StockItem> find() {
    
    return new Finder<Long,StockItem>(Long.class,StockItem.class);
  }




  /**
   * @return the primaryKey
   */
  public long getPrimaryKey() {
    return primaryKey;
  }




  /**
   * @param primaryKey the primaryKey to set
   */
  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }




  /**
   * @return the stockItemId
   */
  public String getStockItemId() {
    return stockItemId;
  }




  /**
   * @param stockItemId the stockItemId to set
   */
  public void setStockItemId(String stockItemId) {
    this.stockItemId = stockItemId;
  }




  /**
   * @return the warehouse
   */
  public Warehouse getWarehouse() {
    return warehouse;
  }




  /**
   * @param warehouse the warehouse to set
   */
  public void setWarehouse(Warehouse warehouse) {
    this.warehouse = warehouse;
  }




  /**
   * @return the product
   */
  public Product getProduct() {
    return product;
  }




  /**
   * @param product the product to set
   */
  public void setProduct(Product product) {
    this.product = product;
  }




  /**
   * @return the quantity
   */
  public long getQuantity() {
    return quantity;
  }




  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }




  /**
   * @return the tags
   */
  public List<Tag> getTags() {
    return tags;
  }




  /**
   * @param tags the tags to set
   */
  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

 

}
