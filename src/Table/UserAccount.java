package Table;

public class UserAccount {

	private String usernameAcc;
	private String passwordAcc;

	public UserAccount() {

	}

	public UserAccount(String usernameAcc, String passwordAcc) {
		this.usernameAcc = usernameAcc;
		this.passwordAcc = passwordAcc;
	}

	public String getUsernameAcc() {
		return usernameAcc;
	}

	public void setUsernameAcc(String usernameAcc) {
		this.usernameAcc = usernameAcc;
	}

	public String getPasswordAcc() {
		return passwordAcc;
	}

	public void setPasswordAcc(String passwordAcc) {
		this.passwordAcc = passwordAcc;
	}

}
