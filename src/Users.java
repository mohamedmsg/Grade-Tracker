import java.io.Serializable;


public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1356975364499664726L;
	private String username, password, name;


	public Users(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean checkPassword(String password){
		return this.password.equals(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
