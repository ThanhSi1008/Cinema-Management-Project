package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
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
				int duration = resultSet.getInt("duration");
				movieList.add(new Movie(name, status, duration));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.close(statement, resultSet);
		}

		return movieList;
	}

	public void addNewMovie(Movie newMovie) {
		Connection connection = connectDB.getConnection();
		String insertSQL = "INSERT INTO Movie (MovieName, Description, Genre, Director, Duration, ReleasedDate, Language, Country, Trailer, StartDate, Status, ImportPrice, ImageSource)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, newMovie.getMovieName());
            preparedStatement.setString(2, newMovie.getDescription());
            preparedStatement.setString(3, newMovie.getGenre());
            preparedStatement.setString(4, newMovie.getDirector());
            preparedStatement.setInt(5, newMovie.getDuration());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            preparedStatement.setString(6, newMovie.getReleasedDate().format(formatter));
            preparedStatement.setString(7, newMovie.getLanguage());
            preparedStatement.setString(8, newMovie.getCountry());
            preparedStatement.setString(9, newMovie.getTrailer());
            preparedStatement.setString(10, newMovie.getStartDate().format(formatter));
            preparedStatement.setString(11, newMovie.getStatus());
            preparedStatement.setDouble(12, newMovie.getImportPrice());
            preparedStatement.setString(13, newMovie.getImageSource());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
