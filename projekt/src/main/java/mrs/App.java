package mrs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private String error="0";
    private Solution solution;
    private Stage stage;
    @Override
    public void init(){
        String[]args = getParameters().getRaw().toArray(new String[0]);
        this.solution=new Solution();
    }
    public void start(Stage primaryStage){
        this.stage=primaryStage;
        primaryStage.setTitle("Liczba kroków");
        Button button = new Button("Wprowadź liczbę kroków");
        GridPane gp = new GridPane();
        gp.add(new Label(""),0,0);
        TextField input=new TextField();
        VBox box = new VBox(button,input, gp);
        Scene scene=new Scene(box, 180, 50);
        button.setOnAction(event ->{
            String text=input.getText();
            int result =Integer.parseInt(text);
            solution.setN(result);
            solution.run();
            error= solution.getError();
            changeStage();
        });
        primaryStage.setScene(scene);
        stage.show();
    }

    private void changeStage() {
        stage.setTitle("Błąd Globalny");
        GridPane gp=new GridPane();
        gp.setGridLinesVisible(true);
        gp.getColumnConstraints().add(new ColumnConstraints(200));
        gp.add(new Label("Błąd Globalny"),0,0);
        gp.add(new Label(this.error),0,1);
        Scene scene1=new Scene(gp, 200, 40);
        stage.setScene(scene1);
    }
}
