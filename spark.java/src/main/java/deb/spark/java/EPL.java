/**
 * 
 */
package deb.spark.java;

import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;

import deb.spark.process.PlayerCounter;

/**
 * @author debmalyajash
 *
 */
@SuppressWarnings("restriction")
public class EPL extends Application {

	public static void main(String... args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		StackPane root = new StackPane();

		Scene scene = new Scene(root, 300, 250);

		primaryStage.setTitle("Live Reporting Frequency");
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);

		// Add data into Chart from keyword tuple <String,Integer>
		PlayerCounter kwC = new PlayerCounter();
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		
		
		kwC.getKeywordMap().forEach((key, value) -> data.add(new PieChart.Data(key, value)));
				
		
		final PieChart chart = new PieChart(data);
		chart.setTitle("Who has the lion's share?");
		
		((StackPane) scene.getRoot()).getChildren().add(chart);
		
		primaryStage.setScene(scene); 
		primaryStage.show();
		
		WritableImage snapShot = scene.snapshot(null);

        ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("MUNLEI.png"));
        System.out.println("Stored image");
	}
}
