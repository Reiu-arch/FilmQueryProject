package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:8889/sdvid";
	private static final String USER = "student";
	private static final String PASS = "student";

	@Override
	public Film findFilmById(int filmId) {
	    Film film = null;

	    String sqlText = "SELECT film.id, title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating, special_features, "
	    		+ "language.name AS language_name "
	    		+ "FROM film "
	    		+ "JOIN language ON film.language_id = language.id "
	    		+ "WHERE film.id = ?";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
	         PreparedStatement stmt = conn.prepareStatement(sqlText)) {

	        stmt.setInt(1, filmId); // Corrected parameter index and value
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            film = new Film();

	            // Using column names for better readability
	            film.setId(rs.getInt("id"));
	            film.setTitle(rs.getString("title"));
	            film.setDescription(rs.getString("description"));
	            film.setReleaseYear(rs.getInt("release_year"));
	            film.setLanguage(rs.getString("language_name"));
	            film.setRentalDuration(rs.getInt("rental_duration"));
	            film.setRentalRate(rs.getDouble("rental_rate"));
	            film.setRentalLength(rs.getInt("length"));
	            film.setReplacementCost(rs.getDouble("replacement_cost"));
	            film.setRating(rs.getString("rating"));
	            film.setSpecialFeatures(rs.getString("special_features"));

	            // Assuming findActorsByFilmId works correctly
	            film.setActor(findActorsByFilmId(filmId));
	        }

	    } catch (SQLException e) {
	        System.err.println("Error finding film by ID: " + filmId);
	        e.printStackTrace();
	    }

	    return film;
	}


	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;

		String sqlText = " id, first_name, last_name from actor where id = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sqlText)) {

			stmt.setInt(1, actorId);
			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					actor = new Actor();
					actor.setId(rs.getInt("id"));
					actor.setFirstName(rs.getString("first_name"));
					actor.setLastName(rs.getString("last_name"));

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String sqlText = "SELECT id, first_name, last_name FROM actor JOIN film_actor ON actor.id = film_actor.film_id WHERE actor_id = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sqlText)) {
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int actorId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);

				Actor actor = new Actor(actorId, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}


	@Override
	public List<Film> findFilmByKeyword(String wordle) {
		Film film = null;
		List<Film> films = new ArrayList<>();
		wordle = "%" + wordle + "%";
		String sqlText = "SELECT film.id, title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating, special_features,"
				+ "language.name AS language_name "
				+ "FROM film "
				+ "JOIN language ON film.language_id = language.id "
				+ "WHERE title LIKE ? OR description LIKE ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sqlText)) {
			stmt.setString(1, wordle);
			stmt.setString(2, wordle);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				film = new Film();

	            // Using column names for better readability
	            film.setId(rs.getInt("id"));
	            film.setTitle(rs.getString("title"));
	            film.setDescription(rs.getString("description"));
	            film.setReleaseYear(rs.getInt("release_year"));
	            film.setLanguage(rs.getString("language_name"));
	            film.setRentalDuration(rs.getInt("rental_duration"));
	            film.setRentalRate(rs.getDouble("rental_rate"));
	            film.setRentalLength(rs.getInt("length"));
	            film.setReplacementCost(rs.getDouble("replacement_cost"));
	            film.setRating(rs.getString("rating"));
	            film.setSpecialFeatures(rs.getString("special_features"));
	            film.setActor(findActorsByFilmId(film.getId()));
	            films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}
}
