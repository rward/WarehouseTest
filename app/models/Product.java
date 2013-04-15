package models;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
@Entity
public class Product extends Model{

  /**
   * 
   */
  private static final long serialVersionUID = 1765615104131291592L;

  public Product(String name, String description) {
   
    this.name = name;
    this.description = description;
  }
  
  public static Finder<Long,Product> find() {
    
    return new Finder<Long,Product>(Long.class,Product.class);
  }

  @Id
  public long id;
  public String name;
  public String description;
  
  @ManyToMany(cascade=CascadeType.ALL)
  public List<Tag> tags = new ArrayList<>();
  
  @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
  public List<StockItem> stockItems = new ArrayList<>();

}
