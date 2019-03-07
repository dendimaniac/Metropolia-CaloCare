package NonActivityClasses;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class AlarmResetFoodAdded extends BroadcastReceiver {
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        pref = context.getSharedPreferences(AppControl.PREF, Activity.MODE_PRIVATE);
        prefEditor = pref.edit();

        prefEditor.putInt("foodAdded", 0);
        prefEditor.putInt("foodRemain", pref.getInt("foodGoal", 0) - 0);
    }
}
