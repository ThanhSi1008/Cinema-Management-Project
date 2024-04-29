package gui.application.form.other;

import java.time.Duration;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.MovieDAO;
import entity.Movie;

public class MovieTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Movie> movieList;
	private String[] columnNames = { "Name", "Status", "Duration" };
	private MovieDAO movieDAO;	

	public MovieTableModel() {
		movieDAO = new MovieDAO();
		movieList = movieDAO.getAllMovie();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return movieList.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Movie movie = movieList.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return movie.getMovieName();
			case 1:
				return movie.getStatus();
			case 2:
				int durationInt = movie.getDuration();
				Duration duration = Duration.ofMinutes(durationInt);
				long hours = duration.toHours();
				long minutes = duration.toMinutes() % 60;
				return String.format("%01dh%02dm", hours, minutes);
		}
		return null;
	}

	public void refresh() {
		movieList = movieDAO.getAllMovie();
		this.fireTableDataChanged();
	}

}
