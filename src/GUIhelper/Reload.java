package GUIhelper;

import java.awt.Window;

public class Reload {
    public static void refreshPage(Window window, String windowClassName) {
        window.dispose(); // Close the current window
        
        try {
            Class<?> cls = Class.forName(windowClassName);
            Window newWindow = (Window) cls.getDeclaredConstructor().newInstance();
            newWindow.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}