package de.Jodu555.TokenSystem.objects;

public class Token {

	private String token;
	private String userUUID;
	private String rang;

	public Token(String token, String userUUID, String rang) {
		super();
		this.userUUID = userUUID;
		this.token = token;
		this.rang = rang;
	}

	@Override
	public String toString() {
		return "Token [userUUID=" + userUUID + ", token=" + token + ", rang=" + rang + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rang == null) ? 0 : rang.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((userUUID == null) ? 0 : userUUID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (rang == null) {
			if (other.rang != null)
				return false;
		} else if (!rang.equals(other.rang))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (userUUID == null) {
			if (other.userUUID != null)
				return false;
		} else if (!userUUID.equals(other.userUUID))
			return false;
		return true;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRang() {
		return rang;
	}

	public void setRang(String rang) {
		this.rang = rang;
	}

}
