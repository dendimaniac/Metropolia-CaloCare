package NonActivityClasses;

import android.widget.EditText;

public class AppControl {
    private static final AppControl appInstance = new AppControl();

    public static final String USER_PREF = "userData";

    public static final String FOOD_PREF = "foodData";

    public static AppControl getInstance() {
        return appInstance;
    }

    public static String getText(EditText input) {
        return input.getText().toString().trim();
    }
}
