package guestBook.model.dto;

public class GuestBookDTO {
	private int no;
	private String name;
	private String email;
	private String passwd;
	private String content;
	private String regi_date;
	
	public GuestBookDTO() {
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegi_date() {
		return regi_date;
	}

	public void setRegi_date(String regi_date) {
		this.regi_date = regi_date;
	}

	@Override
	public String toString() {
		return "GuestBookDTO [no=" + no + ", name=" + name + ", email=" + email + ", passwd=" + passwd + ", content="
				+ content + ", regi_date=" + regi_date + "]";
	}
}