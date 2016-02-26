/**
 * 
 */
package deb.spark.java;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import deb.spark.process.KeyWordCounter;

/**
 * @author debmalyajash
 *
 */
@SuppressWarnings("restriction")
public class UIFX extends Application {

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

		primaryStage.setTitle("Hello Spark!");
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);

		// Add data into Chart from keyword tuple <String,Integer>
		KeyWordCounter kwC = new KeyWordCounter();
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		
		
		kwC.getKeywordMap().forEach((key, value) -> data.add(new PieChart.Data(key, value)));
				
		
		final PieChart chart = new PieChart(data);
		chart.setTitle("Keyword");
		
		((StackPane) scene.getRoot()).getChildren().add(chart);
		
		primaryStage.setScene(scene); 
		primaryStage.show();

	}
}
