package models;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
@Entity
public class Warehouse extends Model{
  
  /**
   * 
   */
  private static final long serialVersionUID = -5901301729351151823L;

  public Warehouse (String warehouseId, String name  ) {
  
    this.warehouseId = warehouseId;
    this.name = name;
  }

  @Id
  public long primaryKey;
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
   * @return the warehouseId
   */
  public String getWarehouseId() {
    return warehouseId;
  }

  /**
   * @param warehouseId the warehouseId to set
   */
  public void setWarehouseId(String warehouseId) {
    this.warehouseId = warehouseId;
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
   * @return the address
   */
  public Address getAddress() {
    return address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  @Required
  @Column(unique=true, nullable=false)
  private String warehouseId;
  
  @OneToMany(mappedBy="warehouse", cascade=CascadeType.ALL)
  public List<StockItem> stockItems = new ArrayList<>();
  
  public String name;
 
  @OneToOne @MapsId
  public Address address;
  
  public static Finder<Long,Warehouse> find() {
    
    return new Finder<Long,Warehouse>(Long.class,Warehouse.class);
  }

  public String toString() {
    
    return String.format("[warehouseId %s name %s]", warehouseId, name);
  }

}
