package credentials;

import java.io.*;

public class Account implements Serializable {
	private static String name;
	private String email;
	private String password;
	private String hashedPassword;
	private String salt;

	public Account(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Account(String name, String email, String hashedPassword, String salt) {
		this.name = name;
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
	}

	public static String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public String getSalt() {
		return salt;
	}

	@Override
	public String toString() {
		return getName() + " " + getEmail() + " " + getPassword() + "\n";
	}
	public String toStringSecure() {
		return getName() + " " + getEmail() + " " + getHashedPassword() + " " + getSalt() + "\n";
	}

	public void writeToFileSecure() {
		try {
			Writer output = null;
			String text = this.toStringSecure();
			File file = new File("src/saved/account.txt");
			output = new PrintWriter(new FileWriter(file, true));
			output.append(text);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

