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
  
  private String name;
  private String street;
  private String zipcode;
  private String city;
 
  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
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
   * @return the street
   */
  public String getStreet() {
    return street;
  }

  /**
   * @param street the street to set
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * @return the zipcode
   */
  public String getZipcode() {
    return zipcode;
  }

  /**
   * @param zipcode the zipcode to set
   */
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }


  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return the serialversionuid
   */
  public static long getSerialversionuid() {
    return serialVersionUID;
  }




 
  
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
