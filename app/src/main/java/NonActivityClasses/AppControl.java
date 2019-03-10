package NonActivityClasses;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.EditText;

public class AppControl {
    private static final AppControl appInstance = new AppControl();

    public static final String PREF = "userData";

    public static final String PREF1= "foodData";


    public static AppControl getInstance() {
        return appInstance;
    }

    public static String getText(EditText input) {
        return input.getText().toString().trim();
    }
}
