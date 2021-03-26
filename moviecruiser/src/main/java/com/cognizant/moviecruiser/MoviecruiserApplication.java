package com.cognizant.moviecruiser;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.service.MovieService;
import com.cognizant.moviecruiser.util.DateUtil;



@SpringBootApplication
public class MoviecruiserApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MoviecruiserApplication.class);
	private static MovieService movieService;

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(MoviecruiserApplication.class, args);
		movieService=context.getBean(MovieService.class);
		LOGGER.info("Inside main");
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
						testGetMovie(1);  // Since movie 1 is modified details of movie id->1 is retrieving
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
				LOGGER.debug("****Thank YOU****");
				break;
			}
			case "2": {
				LOGGER.debug("Customer Movie List");
				LOGGER.debug("****************************************");
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
		LOGGER.debug("****Thank YOU****");
		sc.close();		
	
	}
	
	private static void testGetMovieListAdmin()
	{
		LOGGER.info("Start");
		List<Movie> list=movieService.getMenuListAdmin();
		LOGGER.debug(String.format("%-3s %-20s %-15s %-8s %-30s %-18s " + "%-15s", "Id", "Title",
				"Bos Office", "Active", "Date of Launch", "Genre", "Has Teaser"));
		LOGGER.debug("____________________________________________________________________________________________________________________");
		list.forEach(e->System.err.println(e));
		LOGGER.info("End");
	}
	private static void testGetMovieListCustomer()
	{   
		LOGGER.info("Start");
		List<Movie> list = movieService.getMovieListCustomer();
		LOGGER.debug(String.format("%-3s %-20s %-15s %-8s %-30s %-18s %-15s", "Id", "Title", "Bos Office",
				"Active", "Date of Launch", "Genre", "Has Teaser"));
		list.forEach(e->System.out.println(e));
		LOGGER.info("End");
	}
	
	private static void testModifyMovie()
	{
		LOGGER.info("Start");
		Movie movie=new Movie(1, "The Queen", "$2,514,512,988", true, DateUtil.convertToDate("16/08/2022"),"Fiction", false);
		movieService.modifyMovie(movie);
		LOGGER.info("End");
	}
	private static void testGetMovie(long id)
	{
		LOGGER.info("Start");
		Movie update=movieService.getMovie(id);
		LOGGER.debug(String.format("%-3s %-20s %-15s %-8s %-30s %-18s" + " %-15s", "Id", "Title",
				"Bos Office", "Active", "Date of Launch", "Genre", "Has Teaser"));
		System.out.println(update);
		LOGGER.info("End");
		
	}

}
