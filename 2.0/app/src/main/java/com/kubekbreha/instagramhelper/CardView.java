package com.kubekbreha.instagramhelper;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * Created by yarolegovich on 08.03.2017.
 */

public class CardView extends LinearLayout {

    private Paint gradientPaint;
    private int[] currentGradient;


    private ArgbEvaluator evaluator;

    public CardView(Context context) {
        super(context);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        evaluator = new ArgbEvaluator();

        gradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setWillNotDraw(false);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        inflate(getContext(), R.layout.view_forecast, this);

    }

    private void initGradient() {
        float centerX = getWidth() * 0.5f;
        Shader gradient = new LinearGradient(
                centerX, 0, centerX, getHeight(),
                currentGradient, null,
                Shader.TileMode.MIRROR);
        gradientPaint.setShader(gradient);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (currentGradient != null) {
            initGradient();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), gradientPaint);
        super.onDraw(canvas);
    }

    public void setForecast(ListItem forecast) {
        int gradient = forecast.getGradient();
        currentGradient = getGradient(gradient);
        if (getWidth() != 0 && getHeight() != 0) {
            initGradient();
        }

        invalidate();


    }

    public void onScroll(float fraction, ListItem oldF, ListItem newF) {
        currentGradient = mix(fraction,
                getGradient(newF.getGradient()),
                getGradient(oldF.getGradient()));
        initGradient();
        invalidate();
    }

    private int[] mix(float fraction, int[] c1, int[] c2) {
        return new int[]{
                (Integer) evaluator.evaluate(fraction, c1[0], c2[0]),
                (Integer) evaluator.evaluate(fraction, c1[1], c2[1]),
                (Integer) evaluator.evaluate(fraction, c1[2], c2[2])
        };
    }

    private int[] getGradient(int number) {
        switch (number) {
            case 0:
                return colors(R.array.gradientPeriodicClouds);
            case 1:
                return colors(R.array.gradientCloudy);
            case 2:
                return colors(R.array.gradientMostlyCloudy);
            case 3:
                return colors(R.array.gradientPartlyCloudy);
            case 4:
                return colors(R.array.gradientClear);
            default:
                throw new IllegalArgumentException();
        }
    }



    private int[] colors(@ArrayRes int res) {
        return getContext().getResources().getIntArray(res);
    }

}
