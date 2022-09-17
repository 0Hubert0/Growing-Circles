package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        List<CircleNew> circles = new ArrayList<>();
        final boolean[] expand = {true};

        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 900, 600);
        scene.setFill(Color.BLACK);

        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            CircleNew c = new CircleNew(0, Math.random()*scene.getWidth(), Math.random()*scene.getHeight(),
                    Color.rgb((int) (Math.random()*256), (int) (Math.random()*256), (int) (Math.random()*256)));
                circles.add(c);
                root.getChildren().add(c);
        }));

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(30), event -> {
            for (int i = 0; i < circles.size(); i++) {
                if(circles.get(i).isExpandable()) {
                    expand[0] =true;
                    for (int j = 0; j < circles.size(); j++) {
                        if(i!=j && calculateDistance(circles.get(i), circles.get(j)))
                        {
                            if(circles.get(i).getRadius()==0){
                                circles.remove(circles.get(i));
                                i--;
                            }
                            else {
                                expand[0] = false;
                                circles.get(i).setExpandable(false);
                                if (circles.get(j).isExpandable()) circles.get(j).setExpandable(false);
                                break;
                            }
                        }
                    }
                    if(expand[0])
                    {
                        circles.get(i).expand();
                    }
                }
            }
        }));

        timeline1.setCycleCount(-1);
        timeline1.play();

        timeline2.setCycleCount(-1);
        timeline2.play();

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public static boolean calculateDistance(CircleNew c1, CircleNew c2)
    {
        double x1 = c1.getCenterX();
        double x2 = c2.getCenterX();
        double y1 = c1.getCenterY();
        double y2 = c2.getCenterY();
        double distance = (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);
        return (c1.getRadius()+c2.getRadius())*(c1.getRadius()+c2.getRadius())>distance;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
