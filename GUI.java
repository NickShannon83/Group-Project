import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GUI extends Application
{
	@Override // Override the start method in the Application class
	public void start( Stage primaryStage )
	{
		StackPane root = new StackPane ( );
		Scene scene = new Scene ( root, 1920, 1080 );
		Image splash = new Image ( "Background.jpeg" );

		// Colonize Mars title with dropshadow
		DropShadow shadow = new DropShadow ( );
		shadow.setRadius ( 10.0 );
		shadow.setOffsetX ( 5.0 );
		shadow.setOffsetY ( 5.0 );
		Text title = new Text ( );
		title.setEffect ( shadow );
		title.setCache ( true );
		title.setTranslateY ( -250 );
		title.setFill ( Color.GREEN );
		title.setText ( "Colonize Mars" );
		title.setFont ( Font.font ( null, 200 ) );

		// Player name text
		Text playerName = new Text ( );
		playerName.setCache ( true );
		playerName.setTranslateX ( -200 );
		playerName.setTranslateY ( -150 );
		playerName.setText ( "Enter your name:" );
		playerName.setFont ( Font.font ( null, FontWeight.BOLD, 35 ) );
		
		// The name text field
		TextField name = new TextField();
		name.setMaxWidth ( 150 );
		//name.getText();
		name.setTranslateX ( 55 );
		name.setTranslateY ( -150 );
		
		//Error label
		Label errorLabel = new Label();
		errorLabel.setTextFill ( Color.YELLOW );
		errorLabel.setFont ( Font.font ( null, 15 ) );
		errorLabel.setTranslateX ( 115 );
		errorLabel.setTranslateY ( -126 );

		// Start button
		Button start = new Button ( "Launch!" );
		start.setOnAction ( new EventHandler<ActionEvent> ( )
		{
			@Override
			public void handle( ActionEvent event )
			{
				if (name.getText().isEmpty())
				{
	            errorLabel.setText( "You must enter a name to continue!" );
				} 
				else 
				{
	            String playerName = name.getText ( );
	            SecondStage playerWindow = new SecondStage ( playerName );
					playerWindow.start ( playerWindow );
					primaryStage.close ( );
				}
			}// end action
		} );
		start.setTranslateX ( 180 );
		start.setTranslateY ( -150 );

		// Add background & buttons here-----------------------------------------------------------
		root.getChildren ( ).add ( new ImageView ( splash ) );
		root.getChildren ( ).add ( title );
		root.getChildren ( ).add ( playerName );
		root.getChildren ( ).add ( name );
		root.getChildren ( ).add ( start );
		root.getChildren ( ).add ( errorLabel );

		primaryStage.setTitle ( "Colonize Mars" ); // Set the stage title
		primaryStage.setScene ( scene ); // Place the scene in the stage
		primaryStage.show ( ); // Display the stage

	}

	/*
	 * @Override public void start(Stage primaryStage) { new FirstStage(); }
	 */

	public static void main( String[ ] args )
	{
		Application.launch ( args );

	}

}