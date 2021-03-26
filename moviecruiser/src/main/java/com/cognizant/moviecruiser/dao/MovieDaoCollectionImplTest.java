package com.cognizant.moviecruiser.dao;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognizant.moviecruiser.MoviecruiserApplication;
import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.util.DateUtil;

public class MovieDaoCollectionImplTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieDaoCollectionImplTest.class);
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String choice;

		do {
			LOGGER.debug("Menu");
			LOGGER.debug("****************************************");
			LOGGER.debug("1. Admin");
			LOGGER.debug("2. Customer");
			LOGGER.debug("3. Exit");
			LOGGER.debug("****************************************");

			choice = sc.nextLine();
			LOGGER.debug("****************************************");

			switch (choice) {
			case "1": {
				String adminChoice;
				do {
					LOGGER.debug("Admin Menu");
					LOGGER.debug("****************************************");
					LOGGER.debug("1. Get Movie List");
					LOGGER.debug("2. Modify Movie");
					LOGGER.debug("3. Get Movie");
					LOGGER.debug("4. Main Menu");
					LOGGER.debug("****************************************");

					adminChoice = sc.nextLine();
					LOGGER.debug("****************************************");

					switch (adminChoice) {
					case "1": {
						LOGGER.debug("Admin Movie List");
						LOGGER.debug("****************************************");
						LOGGER.debug(String.format("%-3s %-20s %-15s %-8s %-30s %-18s " + "%-15s", "Id", "Title",
								"Bos Office", "Active", "Date of Launch", "Genre", "Has Teaser"));
						LOGGER.debug("\n");

						testGetMovieListAdmin();
						LOGGER.debug("****************************************");
						break;
					}
					case "2": {
						testModifyMovie();
						LOGGER.debug("Movie 1 is modified !! Enter 3 to display the changes.");
						LOGGER.debug("****************************************");
						break;
					}
					case "3": {
						LOGGER.debug("Movie 1 is displayed !!");
						LOGGER.debug("****************************************");
						LOGGER.debug(String.format("%-3s %-20s %-15s %-8s %-30s %-18s" + " %-15s", "Id", "Title",
								"Bos Office", "Active", "Date of Launch", "Genre", "Has Teaser"));
						LOGGER.debug("\n");

						testGetMovie();
						LOGGER.debug("****************************************");
						break;
					}
					case "4": {
						break;
					}
					default: {
						LOGGER.debug("Enter valid choice");
						LOGGER.debug("****************************************");
					}
					}
				} while (!adminChoice.equals("4"));
				break;
			}
			case "2": {
				LOGGER.debug("Customer Movie List");
				LOGGER.debug("****************************************");
				LOGGER.debug(String.format("%-3s %-20s %-15s %-8s %-30s %-18s %-15s", "Id", "Title", "Bos Office",
						"Active", "Date of Launch", "Genre", "Has Teaser"));
				LOGGER.debug("\n");

				testGetMovieListCustomer();
				LOGGER.debug("****************************************");
				break;
			}
			case "3": {
				break;
			}
			default: {
				LOGGER.debug("Enter valid choice");
				LOGGER.debug("****************************************");
			}
			}
		} while (!choice.equals("3"));

		sc.close();
	}

	public static void testGetMovieListAdmin() {
		MovieDao movieDao = new MovieDaoCollectionImpl();
		List<Movie> movieList = movieDao.getMovieListAdmin();

		for (int i = 0; i < movieList.size(); i++) {
			System.out.println(movieList.get(i));
		}
	}

	public static void testGetMovieListCustomer() {
		MovieDao movieDao = new MovieDaoCollectionImpl();
		List<Movie> movieList = movieDao.getMovieListCustomer();

		for (int i = 0; i < movieList.size(); i++) {
			System.out.println(movieList.get(i));
		}
	}

	public static void testModifyMovie() {
		Movie movie = new Movie(1, "The Martian", "$2,514,512,988", true, DateUtil.convertToDate("16/08/2022"),
				"Fiction", false);
		MovieDao movieDao = new MovieDaoCollectionImpl();
		movieDao.modifyMovie(movie);
	}

	public static void testGetMovie() {
		MovieDao movieDao = new MovieDaoCollectionImpl();
		System.out.println(movieDao.getMovie(1));
	}



}
