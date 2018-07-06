package com.kubekbreha.instagrambot.indicatorDots;

import android.content.Context;
import android.util.TypedValue;

import com.kubekbreha.instagrambot.R;

public class UiUtils {
  public static int getThemePrimaryColor(final Context context) {
    final TypedValue value = new TypedValue();
    context.getTheme().resolveAttribute(R.attr.colorPrimary, value, true);
    return value.data;
  }
}
