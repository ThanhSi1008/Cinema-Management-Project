package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Movie;

public class MovieDAO {

	private ConnectDB connectDB;

	public MovieDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public List<Movie> getAllMovie() {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		Connection connection = connectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement("SELECT MovieName, Status, Duration FROM Movie");
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("MovieName");
				String status = resultSet.getString("Status");
				int durationNum = resultSet.getInt("duration");
				Duration duration = Duration.ofMinutes(durationNum);
				movieList.add(new Movie(name, status, duration));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(statement, resultSet);
		}

		return movieList;
	}
	
}
