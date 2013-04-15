package models;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;
@Entity
public class StockItem extends Model{

  public StockItem(Warehouse warehouse, Product product, long quantity) {
   
    this.warehouse = warehouse;
    this.product = product;
    this.quantity = quantity;
  }

  /**
   * 
   */
  private static final long serialVersionUID = 1765615104131291592L;
  @Id
  public long id;
 
  @ManyToOne(cascade=CascadeType.ALL)
  public Warehouse warehouse;
  
  @ManyToOne(cascade=CascadeType.ALL)
  public Product product;
  
  public long quantity;
  
  
  public List<Tag> tags = new ArrayList<>();
  
  
  
  
  public static Finder<Long,StockItem> find() {
    
    return new Finder<Long,StockItem>(Long.class,StockItem.class);
  }

 

}
