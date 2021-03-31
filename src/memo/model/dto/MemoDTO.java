package memo.model.dto;

public class MemoDTO {
	private int no;
	private int memberNo;
	private String writer;
	private String content;
	private String regiDate;
	
	public MemoDTO() {
		
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}

	@Override
	public String toString() {
		return "MemoDTO [no=" + no + ", memberNo=" + memberNo + ", writer=" + writer + ", content=" + content
				+ ", regiDate=" + regiDate + "]";
	}
}