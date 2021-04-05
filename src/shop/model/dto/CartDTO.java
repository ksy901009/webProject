package shop.model.dto;

import java.sql.Timestamp;

public class CartDTO {
	private int no;
	private int memberNo;
	private int productNo;
	private int amount;
	private Timestamp regi_date; //import java.sql.Timestamp;
	private String chkNo;
	
	private String product_name;
	private int product_price;
	private String product_description;
	private String product_img;
	
	private int buy_money;
	
	public CartDTO() {
		
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getRegi_date() {
		return regi_date;
	}

	public void setRegi_date(Timestamp regi_date) {
		this.regi_date = regi_date;
	}

	public String getChkNo() {
		return chkNo;
	}

	public void setChkNo(String chkNo) {
		this.chkNo = chkNo;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public int getBuy_money() {
		return buy_money;
	}

	public void setBuy_money(int buy_money) {
		this.buy_money = buy_money;
	}

	@Override
	public String toString() {
		return "CartDTO [no=" + no + ", memberNo=" + memberNo + ", productNo=" + productNo + ", amount=" + amount
				+ ", regi_date=" + regi_date + ", chkNo=" + chkNo + ", product_name=" + product_name
				+ ", product_price=" + product_price + ", product_description=" + product_description + ", product_img="
				+ product_img + ", buy_money=" + buy_money + "]";
	}
}