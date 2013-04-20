package models;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
@Entity
public class Product extends Model{

  /**
   * 
   */
  private static final long serialVersionUID = 1765615104131291592L;

  @Id
  public long primaryKey;
  
  @Required
  @Column(unique=true, nullable=false)
  private String productId;
  
  @Required
  private String name;
  
  private String description;
  
  @ManyToMany(cascade=CascadeType.ALL)
  private List<Tag> tags = new ArrayList<>();
  
  @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
  private List<StockItem> stockItems = new ArrayList<>();
  
  
  public Product(String productId, String name, String description)  {
   
    this.name = name;
    this.description = description;
    this.productId = productId;
  }
  
  public static Finder<Long,Product> find() {
    
    return new Finder<Long,Product>(Long.class,Product.class);
  }
  public String toString() {
    
    return String.format("[Product %s %s %s]", productId, name, description);
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

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
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
  /**
   * @param tags the tags to set
   */
  public void addTag(Tag tag) {
    this.tags.add(tag);
  }

  /**
   * @return the stockItems
   */
  public List<StockItem> getStockItems() {
    return stockItems;
  }

  /**
   * @param stockItems the stockItems to set
   */
  public void setStockItems(List<StockItem> stockItems) {
    this.stockItems = stockItems;
  }
 

}
