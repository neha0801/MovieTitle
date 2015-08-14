import java.sql.SQLException;
import java.util.Scanner;

public class MovieTitleGen
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String description="",choice="y";
		// open the database connection
		MovieDB.openConnection();
		MovieDB database = new MovieDB();
		
		String movie = "";
		System.out.println("Myxyllplyk's Random Movie Title Generator\n");

		

		// added code to get the random adjectives and noun for the movie name
		while(choice.equalsIgnoreCase("y"))
		{
			movie = database.generateMovieName();
			
			System.out.print("Choosing randomly from " + database.getCount() + " adjectives ");
			System.out.println("and " + database.getCount() + " nouns ("
						+ (database.getCount()*database.getCount()) + " combinations).");
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
			e.printStackTrace();
		}
		// close the scanner
		sc.close();
	}

}