package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=CinemaManagement;encrypt=false";
	private static final String USER = "sa";
	private static final String PASSWORD = "22639301";

	private static ConnectDB instance;
	private Connection connection;

	private ConnectDB() {
		// Khởi tạo kết nối trong constructor
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Sử dụng double-checked locking để đảm bảo thread safety
	public static ConnectDB getInstance() {
		if (instance == null) {
			synchronized (ConnectDB.class) {
				if (instance == null) {
					instance = new ConnectDB();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	// Đảm bảo kết nối được thiết lập trước khi trả về connection
	public Connection connect() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	// Đóng kết nối
	public void disconnect() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Đóng tất cả các tài nguyên
	public void close(PreparedStatement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
