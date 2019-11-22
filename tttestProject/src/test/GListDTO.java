package test;

import java.sql.Date;

public class GListDTO {
	private String no; //제품번호
	private String local; //제품을 소유한 회사
	private String name; //제품이름
	private String company; //제조회사
	private Date redate; //입고일자
	private int price; //단가
	private int quant; //수량
	private int total; //총금액
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Date getRedate() {
		return redate;
	}
	public void setRedate(Date redate) {
		this.redate = redate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuant() {
		return quant;
	}
	public void setQuant(int quant) {
		this.quant = quant;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "GListDTO [no=" + no + ", local=" + local + ", name=" + name + ", company=" + company + ", redate="
				+ redate + ", price=" + price + ", quant=" + quant + ", total=" + total + "]";
	}
	public GListDTO(String no, String local, String name, String company, Date redate, int price, int quant) {
		super();
		this.no = no;
		this.local = local;
		this.name = name;
		this.company = company;
		this.redate = redate;
		this.price = price;
		this.quant = quant;
		
		total = price*quant;
	}

}
