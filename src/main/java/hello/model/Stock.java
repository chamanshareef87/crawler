package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stock")
public class Stock {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String nseprice;
	private String bseprice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNseprice() {
		return nseprice;
	}
	public void setNseprice(String nseprice) {
		this.nseprice = nseprice;
	}
	public String getBseprice() {
		return bseprice;
	}
	public void setBseprice(String bseprice) {
		this.bseprice = bseprice;
	}
	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", nseprice=" + nseprice + ", bseprice=" + bseprice + "]";
	}


}
