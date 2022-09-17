package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleNew extends Circle {
    boolean expandable=true;


    CircleNew (int r, double x, double y, Color c)
    {
        this.setRadius(r);
        this.setCenterX(x);
        this.setCenterY(y);
        this.setFill(c);
    }

    public void expand()
    {
        this.setRadius(this.getRadius()+1);
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
