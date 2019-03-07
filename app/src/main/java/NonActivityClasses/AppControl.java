package NonActivityClasses;

import android.widget.EditText;

public class AppControl {
    private static final AppControl appInstance = new AppControl();

    public static final String PREF = "data";

    public static AppControl getInstance() {
        return appInstance;
    }

    public static String getText(EditText input) {
        return input.getText().toString().trim();
    }
}
