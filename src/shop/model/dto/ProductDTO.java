package shop.model.dto;

public class ProductDTO {
	private int no; //상품코드
    private String name; //상품이름
    private int price; //단가
    private String description; //상품설명
    private String product_img; //상품이미지 파일이름
    private String regi_date;
    
    private int cart_counter; // 장바구니 갯수
    
    public ProductDTO() {
    	
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public String getRegi_date() {
		return regi_date;
	}

	public void setRegi_date(String regi_date) {
		this.regi_date = regi_date;
	}

	public int getCart_counter() {
		return cart_counter;
	}

	public void setCart_counter(int cart_counter) {
		this.cart_counter = cart_counter;
	}

	@Override
	public String toString() {
		return "ProductDTO [no=" + no + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", product_img=" + product_img + ", regi_date=" + regi_date + ", cart_counter=" + cart_counter + "]";
	}
}