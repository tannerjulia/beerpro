package ch.beerpro.presentation.details;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceInputFilter implements InputFilter {
    private Pattern mPattern;

    PriceInputFilter() {
        mPattern = Pattern.compile("(0|[1-9]+[0-9]*)?(\\.[0-9]{0,1})?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher = mPattern.matcher(dest);
        if (!matcher.matches())
            return "";
        return null;
    }
}
