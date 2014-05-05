package org.EM.Hander;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHander {

	private static String DATABASE_URL = "jdbc:mysql://localhost:3306/db_equiptmentmanagement";

	/*
	 * 获取数据库连接
	 * @return 数据库连接
	 */
	public static Connection provideConnection() {

		Connection connection = null;

		try {
			// 注册类
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接
			connection = DriverManager.getConnection(DATABASE_URL, "root",	"123456");

			return connection;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	/*
	 * 执行SQL语句并且返回影响的行数
	 * @paras query SQL语句， args可能存在的参数
	 */
	public static int ExecuteNonQuery(String query, Object... args) {

		// 获取数据库连接
		Connection connection = provideConnection();

		try {

			PreparedStatement pstmt = connection.prepareStatement(query);

			// 将参数依次放入Statement中
			for (int i = 0; i < args.length; i++) {

				pstmt.setObject(i + 1, args[i]);

			}

			int affectRows = pstmt.executeUpdate();

			// 关闭Statement
			pstmt.close();

			// 关闭数据库连接
			connection.close();

			return affectRows		;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	// 返回有返回值的SQL语句
	public static ResultSet ExecuteQuery(String query, Object... args) {

		// 获取数据库连接
		Connection connection = provideConnection();

		try {

			PreparedStatement pstmt = connection.prepareStatement(query);

			// 将参数放入Statement中
			for (int i = 0; i < args.length; i++) {

				pstmt.setObject(i + 1, args[i]);

			}

			// 返回ResultSet
			ResultSet rs = pstmt.executeQuery();
			
			return rs;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
}
