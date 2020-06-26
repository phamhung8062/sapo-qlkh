package warehouseManagement.dto;

public class 	UserDTO  extends AbstractDTO{
	private String userName;
	private String passWord;
	private String fullName;
	private String email;
	private String phone;
	private String checked;
	private String address;
	private Long[] listroles= new Long[]{};
	public Long[] getListroles() {
		return listroles;
	}
	public void setListroles(Long[] listroles) {
		this.listroles = listroles;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
