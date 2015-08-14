import java.sql.SQLException;
import java.util.Scanner;

public class MovieTitleGen
{
	public static void main(String[] args)
	{

		//String[] adjectives = arrayFromUrl("https://cs.leanderisd.org/txt/adjectives.txt");
		//String[] nouns      = arrayFromUrl("https://cs.leanderisd.org/txt/nouns.txt");
		Scanner sc = new Scanner(System.in);
		String description="",choice="y";
		MovieDB.openConnection();
		MovieDB database = new MovieDB();
		String movie = "";
		System.out.println("Myxyllplyk's Random Movie Title Generator\n");

		//System.out.print("Choosing randomly from " + adjectives.length + " adjectives ");
		//System.out.println("and " + nouns.length + " nouns (" + (adjectives.length*nouns.length) + " combinations).");

		// added code to get the random adjectives and noun for the movie name
		while(choice.equalsIgnoreCase("y"))
		{
			movie = database.generateMovieName();
			// output is formatted to capitalize first letter of all the words
			System.out.println( "Your movie title is: " + movie);
			
			System.out.println("What's the movie about?:");
			description = sc.nextLine();

			database.storeMovieName(movie,description);
			
			System.out.println("Do you want to generate more titles? (y/n)");
			choice = sc.next();
			sc.nextLine();
		}
		try {
			MovieDB.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}