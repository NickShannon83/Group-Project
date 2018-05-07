/*****************************************************
 * This class creates a pop-up window giving the player a summary of their score
 * and saves it to the high scores file. It is used to close the game at the end.
 * @author Jared Crouse
 * 
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EndStage extends Stage
{
	String endName = "Jared Crouse"; // Default high score name
	int endScore = 200;					// Default high score
	
	// The EndStage constructor
	public EndStage(String name, int score)
	{
		super ( );
		this.endName = name;
		this.endScore = score;
	}
	
	public void start( Stage endStage )
	{
		StackPane root = new StackPane ( );
		Scene scene = new Scene ( root, 480, 270 ); // Screen size
		Image image = new Image ( "endScreen.jpg" ); // Background image

		// End title with dropshadow
		DropShadow shadow = new DropShadow ( );
		shadow.setRadius ( 10.0 );
		shadow.setOffsetX ( 5.0 );
		shadow.setOffsetY ( 5.0 );
		Text congrats = new Text ( );
		congrats.setEffect ( shadow );
		congrats.setCache ( true );
		congrats.setTranslateY ( -110 );
		congrats.setFill ( Color.GREEN );
		congrats.setText ( "Congratulations!" );
		congrats.setFont ( Font.font ( null, 50 ) );

		// Defining the end game button
		Button end = new Button ( "End game" );
		end.setTranslateY ( 110 );
		end.setOnAction ( new EventHandler<ActionEvent> ( ) // Close game when "end game" button is clicked
		{
			@Override
			public void handle( ActionEvent event )
			{
				Platform.exit ( );
			}// end action
		} );
		
		// Score summary window
		Text summary = new Text ( );
		summary.setFill ( Color.YELLOW );
		summary.setText ( endName + " scored " + endScore + " points!" );
		summary.setFont ( Font.font ( null, FontWeight.BOLD, 25 ) );		

		root.getChildren ( ).add ( new ImageView ( image ) );
		root.getChildren ( ).add ( congrats );
		root.getChildren ( ).add ( end );
		root.getChildren ( ).add ( summary );
		
		endStage.setTitle ( "Congratulations!" ); // Set the stage title
		endStage.setScene ( scene ); // Place the scene in the stage
		endStage.initStyle(StageStyle.UNDECORATED); // Remove window controls
		endStage.show ( ); // Display the stage
	}
}