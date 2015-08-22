package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import core.Account;
import core.Account.AccountSex;
import core.Account.AccountType;
import core.Account.showItems;
import core.Announcement;
import core.Dislike;
import core.ErrorCode;
import interfaces.DatabaseInterface;

public class Database implements DatabaseInterface{

	private BasicDataSource ds;
	public static final int MAX_CONNECTIONS = 500;
	private static final int MAX_WAIT_TIME = 60000;
	
	public Database() {
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/travelbuddies?characterEncoding=UTF8");
        ds.setUsername("root");
        ds.setPassword("vatodato");
        ds.setMaxTotal(MAX_CONNECTIONS);
        ds.setMaxConnLifetimeMillis(MAX_WAIT_TIME);
	}

	@Override
	public int addAccount(Account account) {
		try (Connection conn = (Connection) ds.getConnection()) {
			if (containsAccount(conn, account)) {
				return ErrorCode.ACCOUNT_REGISTERED;
			};
			insertNewAccount(conn, account);
		} catch (SQLException e) {
			return ErrorCode.SQL_ERROR;
		}
		return ErrorCode.OK;
	}

	private boolean containsAccount(Connection conn, Account account) throws SQLException {
		
		try (PreparedStatement stmt = conn.prepareStatement("select count(*) as count from Accounts where username = ?")) {
			stmt.setString(1, account.getUserName());
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt("count");
					System.out.println(count);
					if (count == 0) return false;
				}
			}
		}
		return true;
	}

	private void insertNewAccount(Connection conn, Account account) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("insert into Accounts"
				+ "(username, pass, first_name, last_name, birth_date, sex, picture, mobile, mail, reveal, account_type) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
			stmt.setString(1, account.getUserName());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getFirstName());
			stmt.setString(4, account.getLastName());
			Date dt = new Date(account.getBirthDate().getTime());
			stmt.setDate(5, dt);
			stmt.setString(6, "" + account.getSex());
			stmt.setString(7, account.getPicture());
			stmt.setString(8, account.getMobile());
			stmt.setString(9, account.getMail());
			stmt.setString(10, account.getContact());
			stmt.setString(11, "" + account.getType());
			stmt.execute();
		}
	}

	@Override
	public int addAnnouncement(Announcement announce) {
		try (Connection conn = ds.getConnection()) {
			if (containsAnnouncement(conn, announce)) {
				return ErrorCode.ANNOUNCEMENT_REGISTERED;
			}
			insertNewAnnouncement(announce, conn);
		} catch (SQLException e) {
			return ErrorCode.SQL_ERROR;
		}
		return ErrorCode.OK;
	}

	private boolean containsAnnouncement(Connection conn, Announcement announce) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("select count(*) as count from Announcements where "
				+ "author_id = ? and start_city = ? and end_city = ? and start_date = ?")) {
			stmt.setInt(1, announce.getAuthor());
			stmt.setString(2, announce.getRoute().getStartCity());
			stmt.setString(3, announce.getRoute().getEndCity());
			stmt.setDate(4, new Date(announce.getStartDate().getTime()));
			stmt.setDate(5, new Date(announce.getEndDate().getTime()));
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt("count");
					if (count == 0) return false;
				}
			}
		}
		return true;
	}

	private void insertNewAnnouncement(Announcement announce, Connection conn) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("insert into Announcements "
				+ "(author_id, start_city, end_city, announce_date, start_date, end_date, avail_slots) "
				+ "values (?, ?, ?, ?, ?, ?, ?)")) {
			stmt.setInt(1, announce.getAuthor());
			stmt.setString(2, announce.getRoute().getStartCity());
			stmt.setString(3, announce.getRoute().getEndCity());
			stmt.setDate(4, new Date(announce.getAnnounceDate().getTime()));
			stmt.setDate(5, new Date(announce.getStartDate().getTime()));
			stmt.setDate(6, new Date(announce.getEndDate().getTime()));
			stmt.setInt(7, announce.getAvailSlots());
			stmt.execute();
		}		
	}
	
	@Override
	public int addRoute(Announcement announce, List<String> cities) {
		try (Connection conn = ds.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement("delete from Routes where announce_id = ?")) {
				stmt.setInt(1, announce.getId());
				stmt.execute();
			}
			for (int i=0; i<cities.size(); i++) {
				try (PreparedStatement stmt = conn.prepareStatement("insert into Routes "
						+ "(announce_id, city_code, turn) "
						+ "values (?, ?, ?)")) {
					stmt.setInt(1, announce.getId());
					stmt.setString(2, cities.get(i));
					stmt.setInt(3, i+2);
					stmt.execute();
				}
			}
		} catch (SQLException e) {
			return ErrorCode.ROUTE_ERROR;
		}
		return ErrorCode.OK;
	}

	@Override
	public int addDislikes(Dislike dislike) {
		// TODO Auto-generated method stub
		return ErrorCode.OK;	
	}

	@Override
	public Account getAccountByID(int accountID) {
		try (Connection conn = (Connection) ds.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement("select "
					+ "id, username, pass, first_name, last_name, birth_date, sex, picture, mobile, mail, reveal, account_type "
					+ "from Accounts where "
					+ "id = ?")) {
				stmt.setInt(1, accountID);
				return getAccount(stmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Account getAccount(PreparedStatement stmt) throws SQLException {
		Account acc = new Account();
		try (ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				acc.setId(rs.getInt("id"));
				acc.setUserName(rs.getString("username"));
				acc.setPassword(rs.getString("pass"));
				acc.setFirstName(rs.getString("first_name"));
				acc.setLastName(rs.getString("last_name"));
				acc.setBirthDate(new java.util.Date(rs.getDate("birth_date").getTime()));
				acc.setSex(AccountSex.valueOf(rs.getString("sex")));
				acc.setPicture(rs.getString("picture"));
				acc.setMobile(rs.getString("mobile"));
				acc.setMail(rs.getString("mail"));
				acc.setContact(showItems.valueOf(rs.getString("reveal")));
				acc.setType(AccountType.valueOf(rs.getString("account_type")));
				return acc;
			}
		}
		return null;
	}

	@Override
	public Announcement getAnnounceByID(int announceID) {
		try (Connection conn = ds.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement("select "
					+ "id, author_id, start_city, end_city, announce_date, start_date, end_date, avail_slots "
					+ "from Announcements where id = ?")) {
				stmt.setInt(1, announceID);
				return getAnnouncement(stmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Announcement getAnnouncement(PreparedStatement stmt) throws SQLException {
		Announcement announce = new Announcement();
		try (ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				announce.setId(rs.getInt("id"));
				announce.setAuthor(rs.getInt("author_id"));
				announce.setStartCity(rs.getString("start_city"));
				announce.setEndCity(rs.getString("end_city"));
				announce.setAnnounceDate(new java.util.Date(rs.getDate("announce_date").getTime()));
				announce.setStartDate(new java.util.Date(rs.getDate("start_date").getTime()));
				announce.setEndDate(new java.util.Date(rs.getDate("end_date").getTime()));
				announce.setAvailSlots(rs.getInt("avail_slots"));
				return announce;
			}
		}
		return null;
	}

	@Override
	public Dislike getDislike(int accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSideRoutes(int announceID) {
		try (Connection conn = ds.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement("select select city_code, turn "
					+ "from Routes where announce_id = ? order by turn")) {
				stmt.setInt(1, announceID);
				try (ResultSet rs = stmt.executeQuery()) {
					List<String> cities = new ArrayList<String>();
					while(rs.next()) {
						cities.add(rs.getString("city_code"));
					}
					return cities;
				}
			}
		} catch (SQLException e) {
			return null;
		}
	}
	
}
