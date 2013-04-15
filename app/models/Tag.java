package models;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
@Entity
public class Tag extends Model{

  /**
   * 
   */
  private static final long serialVersionUID = -6461616402248408385L;







  @Id
  public long id;
  public String name;
  
  @ManyToMany(mappedBy="tags", cascade=CascadeType.ALL)
  public List<Product> products = new ArrayList<>();
  
 


  public Tag (String product  ) {

    this.name = product;
   
  }

  
  
  
  
  
  public static Finder<Long,Tag> find() {
    
    return new Finder<Long,Tag>(Long.class,Tag.class);
  }

 

}
