package models;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;
@Entity
public class Warehouse extends Model{

  



  /**
   * 
   */
  private static final long serialVersionUID = -5901301729351151823L;






  public Warehouse (String name  ) {
  
    this.name = name;
   
  }


  @Id
  public long id;
  
  @OneToMany(mappedBy="warehouse", cascade=CascadeType.ALL)
  public List<StockItem> stockItems = new ArrayList<>();
  
  
  public String name;
 
  @OneToOne @MapsId
  public Address address;
  
  public static Finder<Long,Warehouse> find() {
    
    return new Finder<Long,Warehouse>(Long.class,Warehouse.class);
  }

 

}
