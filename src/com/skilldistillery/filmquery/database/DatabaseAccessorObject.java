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
						         //1    2         3          4                5           6                7          8          9             10         11
		String sqlText = " SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features from film where id = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sqlText)) {

			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					film = new Film();
					
					film.setId(rs.getInt(1));
					film.setTitle(rs.getString(2));
					film.setDescription(rs.getString(3));
					film.setReleaseYear(rs.getInt(4));
					film.setLanguageId(rs.getInt(5));
					film.setRentalDuration(rs.getInt(6));
					film.setRentalRate(rs.getDouble(7));
					film.setRentalLength(rs.getInt(8));
					film.setReplacementCost(rs.getDouble(9));
					film.setRating(rs.getString(10));
					film.setSpecialFeatures(rs.getString(11));
					
					film.setActor(findActorsByFilmId(filmId));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		

		String sqlText = "Select id, first_name, last_name from actor where id = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sqlText)) {

			stmt.setInt(1, actorId);
			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {
					
					int id = rs.getInt(1);
					String firstName = rs.getString(2);
					String lastName = rs.getString(3);
					actor = new Actor(id, firstName, lastName);
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
		String sqlText = "SELECT id, title, description, release_year, language_id, rental_duration, ";
		sqlText += " rental_rate, length, replacement_cost, rating, special_features "
				+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sqlText)) {
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				findActorById(filmId);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}
}
