package interfaces;

import core.Account;
import core.Announcement;
import core.Dislike;

public interface DatabaseInterface {

	public enum showItems {mail, mobile, both};
	public void initConnection();
	public void addAccount(Account account);
	public void addAnnouncement(Announcement announce);
	public void addDislikes(Dislike dislike);
	
	public Account getAccountByID(int ID);
	public Announcement getAnnounceByID(int ID);
	public Dislike getDislike(int ID);
}
