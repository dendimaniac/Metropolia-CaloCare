package NonActivityClasses;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class InputFilterMinMax implements InputFilter {
    private int min, max;
    private Context context;

    public InputFilterMinMax(int min, int max, Context newContext) {
        this.min = min;
        this.max = max;
        this.context = newContext;
    }

    public InputFilterMinMax(String min, String max, Context newContext) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
        this.context = newContext;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
            else if (!isInRange(min, max, input)) {
                Toast newToast = Toast.makeText(context, "Invalid, limit: " + min + " - " + max, Toast.LENGTH_SHORT);
                View v = newToast.getView();

                v.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                TextView text = v.findViewById(android.R.id.message);
                text.setTextColor(Color.WHITE);

                newToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

                newToast.show();
            }
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}