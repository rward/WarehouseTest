package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import play.db.ebean.Model;

@Entity
public class Address extends Model {
  
  
  @Id
  public long id;
  
  @OneToOne(mappedBy="address", cascade=CascadeType.ALL)
  public Warehouse warehouse;
  
  public String name;
 
 
  public String street;
  
  public String zipcode;
  
  public String city;
  
  /** 
   * 
   */
  private static final long serialVersionUID = 6468953896014319700L;


  public Address (String name,String street, String city, String zipcode  ) {
    
    this.zipcode = zipcode;
    this.name = name;
    this.street = street;
    this.city = city;
  }


  
  
  public static Finder<Long,Address> find() {
    
    return new Finder<Long,Address>(Long.class,Address.class);
  }

}
