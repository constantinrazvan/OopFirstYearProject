import View.CheckPropertiesFrame;

public class Main {

    public static void main(String[] args) {
        CheckPropertiesFrame propertiesFrame = new CheckPropertiesFrame("Check properties");
        propertiesFrame.pack(); 
        propertiesFrame.setSize(700, 400);
        propertiesFrame.setVisible(true);
    }
}