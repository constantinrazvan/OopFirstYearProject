import View.CheckPropertiesFrame;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        CheckPropertiesFrame propertiesFrame = new CheckPropertiesFrame("Properties Manager");
        propertiesFrame.pack(); 
        propertiesFrame.setVisible(true);
        propertiesFrame.setSize(950, 400);
        propertiesFrame.setResizable(false);
    }
}