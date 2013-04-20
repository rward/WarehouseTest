package models;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
@Entity
public class Tag extends Model{

  

  /**
   * 
   */
  private static final long serialVersionUID = -2350979221568963963L;
  
  @Id
  public long primaryKey;
  @Required
  public String tagId;
  
  @ManyToMany(mappedBy="tags", cascade=CascadeType.ALL)
  private List<Product> products = new ArrayList<>();
  
  public Tag (String tagId  ) {

    this.tagId = tagId;
   
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
   * @return the tagId
   */
  public String getTagId() {
    return tagId;
  }


  /**
   * @param tagId the tagId to set
   */
  public void setTagId(String tagId) {
    this.tagId = tagId;
  }


  /**
   * @return the products
   */
  public List<Product> getProducts() {
    return products;
  }


  /**
   * @param products the products to set
   */
  public void setProducts(List<Product> products) {
    this.products = products;
  }


  

 
  public static Finder<Long,Tag> find() {
    
    return new Finder<Long,Tag>(Long.class,Tag.class);
  }

  /**
   * No tag can be named "Tag".
   * Note: Illustrates use of validate method
   * @return null if ok, error string if not ok
   */
   public String validate() {
     
     return ("Tag".equals(this.tagId) ? "Invalid tag name" : null);
   }
   
   public String toString() {
     
     return String.format("[Tag %s]", tagId);
   }

}
