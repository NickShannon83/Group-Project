import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

	
public class HighScores
{
	
	
	public void highscore() 
			  throws IOException {
			    String str = "Hello";
			   
			    BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"));
			    writer.write(str);
			     
			    writer.close();
			}

	
}
