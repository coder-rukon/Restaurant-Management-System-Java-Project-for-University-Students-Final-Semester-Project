package customer;

public final class CustomerSession {
	public static String userId,userName;

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		CustomerSession.userId = userId;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		CustomerSession.userName = userName;
	}
}
