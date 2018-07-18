package com.kubekbreha.instagramhelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;


import com.kubekbreha.instagramhelper.discretescrollview.DiscreteScrollView;
import com.kubekbreha.instagramhelper.discretescrollview.transform.ScaleTransformer;

import java.util.List;

/**
 * Created by yarolegovich on 08.03.2017.
 */

public class WeatherActivity extends AppCompatActivity implements
        DiscreteScrollView.ScrollStateChangeListener<CardAdapter.ViewHolder>,
        DiscreteScrollView.OnItemChangedListener<CardAdapter.ViewHolder>{

    private List<ListItem> forecasts;

    private CardView forecastView;
    private DiscreteScrollView cityPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        forecastView = (CardView) findViewById(R.id.forecast_view);

        forecasts = UsersList.get().getForecasts();
        cityPicker = (DiscreteScrollView) findViewById(R.id.forecast_city_picker);
        cityPicker.setSlideOnFling(true);
        cityPicker.setAdapter(new CardAdapter(forecasts));
        cityPicker.addOnItemChangedListener(this);
        cityPicker.addScrollStateChangeListener(this);
        cityPicker.scrollToPosition(2);
        cityPicker.setItemTransitionTimeMillis(150);
        cityPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        forecastView.setForecast(forecasts.get(0));

    }

    @Override
    public void onCurrentItemChanged(@Nullable CardAdapter.ViewHolder holder, int position) {
        //viewHolder will never be null, because we never remove items from adapter's list
        if (holder != null) {
            forecastView.setForecast(forecasts.get(position));
            holder.showText();
        }
    }

    @Override
    public void onScrollStart(@NonNull CardAdapter.ViewHolder holder, int position) {
        holder.hideText();
    }

    @Override
    public void onScroll(
            float position,
            int currentIndex, int newIndex,
            @Nullable CardAdapter.ViewHolder currentHolder,
            @Nullable CardAdapter.ViewHolder newHolder) {
        ListItem current = forecasts.get(currentIndex);
        if (newIndex >= 0 && newIndex < cityPicker.getAdapter().getItemCount()) {
            ListItem next = forecasts.get(newIndex);
            forecastView.onScroll(1f - Math.abs(position), current, next);
        }
    }



    @Override
    public void onScrollEnd(@NonNull CardAdapter.ViewHolder holder, int position) {

    }
}
