import java.awt.*;
import javax.swing.*;

public class PizzaGUIRunner {
    public static void main(String[] args) {
        int width = 500;
        int height = 500;

        JFrame frame = new PizzaGUIFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
