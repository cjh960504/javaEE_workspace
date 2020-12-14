package common.db;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolManager2 {
	InitialContext context;
	DataSource ds;
	private static PoolManager2 instance;
	private PoolManager2() {
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/myoracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public static PoolManager2 getInstance() {
		if(instance==null) {
			return instance = new PoolManager2();
		}
		return instance;
	}
}
