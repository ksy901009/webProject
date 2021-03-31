package comment.model.dto;

public class BoardCommentDTO {
	private int comment_no; //댓글 일련번호
	private int board_no; //게시물 번호
	private String writer;
    private String content;
    private String passwd;
    private int memberNo;
    private String ip;
    private String regiDate;
    
    public BoardCommentDTO() {
    	
	}

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}

	@Override
	public String toString() {
		return "BoardCommentDTO [comment_no=" + comment_no + ", board_no=" + board_no + ", writer=" + writer
				+ ", content=" + content + ", passwd=" + passwd + ", memberNo=" + memberNo + ", ip=" + ip
				+ ", regiDate=" + regiDate + "]";
	}
}