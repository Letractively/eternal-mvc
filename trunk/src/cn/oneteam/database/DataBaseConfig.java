package cn.oneteam.database;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 数据库配置参数.
 * @author OneTeam K
 */

public class DataBaseConfig {
	private static String database;
	private static String driver;
	private static String login;
	private static String password;
	private static String initialSize;
	private static String minIdle;
	private static String maxIdle;
	private static String maxActive;
	private static String maxWait;
	
	private static DataSource dataSource;

	public String getDatabase() {
		return database;
	}

    /**
     * 数据库url。 
     */
	public void setDatabase(String database) {
		this.database = database;
	}
	
	public String getDriver() {
		return driver;
	}
    /**
     * 数据库驱动。 
     */
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	public String getLogin() {
		return login;
	}
    /**
     * 数据库帐号。 
     */
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
    /**
     * 数据库密码。 
     */
	public void setPassword(String password) {
		this.password = password;
	}

	public static String getInitialSize() {
		return initialSize;
	}
	
    /**
     * 数据库初始化连接数目。 
     */
	public static void setInitialSize(String initialSize) {
		DataBaseConfig.initialSize = initialSize;
	}

	public static String getMinIdle() {
		return minIdle;
	}
    /**
     * 数据库连接池中最少可空闲连接数。 
     */
	public static void setMinIdle(String minIdle) {
		DataBaseConfig.minIdle = minIdle;
	}

	public static String getMaxIdle() {
		return maxIdle;
	}
    /**
     * 数据库连接池中最多可空闲连接数。 
     */
	public static void setMaxIdle(String maxIdle) {
		DataBaseConfig.maxIdle = maxIdle;
	}

	public static String getMaxActive() {
		return maxActive;
	}
    /**
     * 数据库连接池支持的最大连接数。 
     */
	public static void setMaxActive(String maxActive) {
		DataBaseConfig.maxActive = maxActive;
	}

	public static String getMaxWait() {
		return maxWait;
	}
    /**
     * 连接池中连接用完时,新的请求等待时间,毫秒。 
     */
	public static void setMaxWait(String maxWait) {
		DataBaseConfig.maxWait = maxWait;
	}

	/**
	 * 返回数据源实例，如果为空时，创建一个新的实例。
	 */
	public static DataSource getDataSource() {
		if (dataSource == null) {
	        BasicDataSource ds = new BasicDataSource();
	        ds.setDriverClassName(driver);
	        ds.setUsername(login);
	        ds.setPassword(password);
	        ds.setUrl(database);
//	        ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
	        ds.setInitialSize(10);
	        ds.setMaxActive(20);
	        ds.setMaxIdle(20);
	        ds.setMaxWait(5000);
	        dataSource = ds;
		}
		return dataSource;
	}
}
