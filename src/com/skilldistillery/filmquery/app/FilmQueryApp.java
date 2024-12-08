package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();

		app.launch();
	}

	
	private void launch() {
		Scanner sc = new Scanner(System.in);

		boolean keepGoing = true;
		try {
			while (keepGoing) {
				displayUserMenu();
				seperator();
				System.out.println("Please choose an option from the Menu: ");
				System.out.println();
				String input = sc.nextLine();
				System.out.println();
				int i = Integer.valueOf(input);
				switch (i) {
				case 1:
					try {
					System.out.println(
							"We have 1000 films in our collection! Pick a number between 1-1000 to see each ones information!");
					String filmChoice = sc.nextLine();
					int option = Integer.valueOf(filmChoice);

					if (option > 1000) {
						System.err.println("Please choose an appropriate film number");
						break;
					}
					if (option < 0) {
						System.err.println("Please choose an appropriate film number");
						break;
					} else {

						Film film = db.findFilmById(option);
						seperator();
						System.out.println("\n");
						System.out.println("| Film        : " + film.getTitle() + "\n|------------------------------------------------------\n| Release Year: "
								+ film.getReleaseYear() + "\n|------------------------------------------------------\n| Rated       : " + film.getRating() + "\n|------------------------------------------------------\n| Description : "
								+ film.getDescription()+ "\n|------------------------------------------------------\n| Language    : "+ film.getLanguage() +"\n|------------------------------------------------------\n| Actors      : " + film.getActor());
						System.out.println("\n");
						seperator();
						break;
					}
					}
						catch (InputMismatchException ime) {
							System.out.println("Invalid input.\n");
							sc.nextLine();

						}
					
				case 2:
					try {
						System.out.println("Enter in a word you would like to search our database with! :");
						String filmString = sc.nextLine();
						

						
							List<Film> film = db.findFilmByKeyword(filmString);
							seperator();
							for (Film filmz : film) {
								
							seperator();
							System.out.println("\n");
							System.out.println("| Film        : " + filmz.getTitle() + "\n|------------------------------------------------------\n| Release Year: "
									+ filmz.getReleaseYear() + "\n|------------------------------------------------------\n| Rated       : " + filmz.getRating() + "\n|------------------------------------------------------\n| Description : "
									+ filmz.getDescription() + "\n|------------------------------------------------------\n| Language    : "+ filmz.getLanguage() +"\n|------------------------------------------------------\n| Actors      : " + filmz.getActor());
							System.out.println("\n");
							seperator();
							}
							break;
						}
						
							catch (InputMismatchException ime) {
								System.out.println("Invalid input.\n");
								sc.nextLine();
							}
				case 3:
					
					seperator();
					seperator();
					System.out.println("▓▓▓▓▓▓ ▓▓▓▓▓▓▓ ▓▓▓▓▓▓▓ ▓▓▓▓▓▓     ▓▓▓▓▓   ▓▓    ▓▓  ▓▓▓▓▓\n"
							         + "▓▓     ▓▓   ▓▓ ▓▓   ▓▓ ▓▓   ▓▓    ▓▓  ▓▓   ▓▓  ▓▓   ▓▓   \n"
							         + "▓▓  ▓▓ ▓▓   ▓▓ ▓▓   ▓▓ ▓▓    ▓▓   ▓▓▓▓▓▓▓   ▓▓▓▓    ▓▓▓▓ \n"
							         + "▓▓   ▓ ▓▓   ▓▓ ▓▓   ▓▓ ▓▓   ▓▓    ▓▓  ▓▓     ▓▓     ▓▓   \n"
							         + "▓▓▓▓▓▓ ▓▓▓▓▓▓▓ ▓▓▓▓▓▓▓ ▓▓▓▓▓▓     ▓▓▓▓▓▓     ▓▓     ▓▓▓▓▓\n"
							         + "");
					seperator();
					seperator();
					
					keepGoing = false;
					break;
					default:
						System.err.println("Please enter in a valid option!");
						return;
				}

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		sc.close();
	}

	private void displayUserMenu() {
		System.out.println("╔══════════════════════════════════════════════════════════╗");
		System.out.println("║                         MENU                             ║▒");
		System.out.println("╠══════════════════════════════════════════════════════════╣▒");
		System.out.println("║                                                          ║▒");
		System.out.println("║ 1.) Look up a film by its ID.                            ║▒");
		System.out.println("║                                                          ║▒");
		System.out.println("║ 2.) Look up a film by a search keyword.                  ║▒");
		System.out.println("║                                                          ║▒");
		System.out.println("║ 3.) Exit the application.                                ║▒");
		System.out.println("║                                                          ║▒");
		System.out.println("╚══════════════════════════════════════════════════════════╝▒");
		System.out.println(" ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
	}

	private void seperator() {
		System.out.println("\n▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n");
	}
}
