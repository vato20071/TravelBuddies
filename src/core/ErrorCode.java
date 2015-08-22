package core;

public class ErrorCode {
	public static final int OK = 0;
	public static final int ACCOUNT_REGISTERED = 1;
	public static final int ANNOUNCEMENT_REGISTERED = 2;
	public static final int ROUTE_ERROR = 3;
	public static final int SQL_ERROR = 4;
	public static String getErrorMessage(int error) {
		switch(error) {
			case OK: return "OK";
			case ACCOUNT_REGISTERED: return "Such account already exists";
			case ANNOUNCEMENT_REGISTERED: return "Such Announcement already exists";
			case ROUTE_ERROR: return "Route error occured"; 
			case SQL_ERROR: return "MySQL error occured";
			default: return "Incorrect error code";
		}
	}
}
