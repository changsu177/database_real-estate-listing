package pm6.tools;

import pm6.dal.*;
import pm6.model.*;

import java.sql.SQLException;



public class locationInserter {

	public static void main(String[] args) throws SQLException {
		PropertyLocationDao locationDao = PropertyLocationDao.getInstance();
		PropertyLocation location = new PropertyLocation("MA", "Needham", "123 Elm St", "AptB", 24940);
		location = locationDao.create(location);
		location = new PropertyLocation("MA", "Boston", "8 Yawkey Way", "AptB", 24920);
		location = locationDao.create(location);
		location = new PropertyLocation("MA", "Woburn", " 235 Wilmington Rd", "AptA", 81010);
		location = locationDao.create(location);
		location = new PropertyLocation("MA", "Waltham", "6432 Main St", "AptC", 25730);
		location = locationDao.create(location);
		location = new PropertyLocation("MA", "Marlborough", "1 Boston Post Rd", "Apt1", 2494);
		location = locationDao.create(location);
		location = new PropertyLocation("MA", "Tewksbury", "17 Winter St", "Apt4", 2494);
		location = locationDao.create(location);
		location = new PropertyLocation("AL", "Huntsville", "4321 University Dr", "", 35755);
		location = locationDao.create(location);
		location = new PropertyLocation("AL", "Madison", "18000 County Line Rd", "", 35756);
		location = locationDao.create(location);
		location = new PropertyLocation("AL", "Athens", "12432 HWY 72", "", 35780);
		location = locationDao.create(location);
		location = new PropertyLocation("RI", "Newport", "5423 Ocean Dr", "AptB", 24810);
		location = locationDao.create(location);
		
	}
}
