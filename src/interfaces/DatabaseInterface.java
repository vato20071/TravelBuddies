package interfaces;

import java.util.List;

import core.Account;
import core.Announcement;
import core.Dislike;

public interface DatabaseInterface {

	public int addAccount(Account account);
	public int addAnnouncement(Announcement announce);
	public int addDislikes(Dislike dislike);
	public int addRoute(Announcement announce, List<String> cities);
	
	public Account getAccountByID(int accountID);
	public Announcement getAnnounceByID(int announceID);
	public Dislike getDislike(int accountID);
	public List<String> getSideRoutes(int announceID);
}
