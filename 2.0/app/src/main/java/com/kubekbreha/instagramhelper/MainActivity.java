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

public class MainActivity extends AppCompatActivity implements
        DiscreteScrollView.ScrollStateChangeListener<CardAdapter.ViewHolder>,
        DiscreteScrollView.OnItemChangedListener<CardAdapter.ViewHolder>{

    private List<ListItem> lists;

    private CardView cardListVIew;
    private DiscreteScrollView listPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        cardListVIew = findViewById(R.id.forecast_view);

        lists = UsersList.get().getForecasts();
        listPicker = findViewById(R.id.forecast_city_picker);
        listPicker.setSlideOnFling(true);
        listPicker.setAdapter(new CardAdapter(lists));
        listPicker.addOnItemChangedListener(this);
        listPicker.addScrollStateChangeListener(this);
        listPicker.scrollToPosition(2);
        listPicker.setItemTransitionTimeMillis(150);
        listPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        cardListVIew.setForecast(lists.get(0));

    }

    @Override
    public void onCurrentItemChanged(@Nullable CardAdapter.ViewHolder holder, int position) {
        //viewHolder will never be null, because we never remove items from adapter's list
        if (holder != null) {
            cardListVIew.setForecast(lists.get(position));
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
        ListItem current = lists.get(currentIndex);
        if (newIndex >= 0 && newIndex < listPicker.getAdapter().getItemCount()) {
            ListItem next = lists.get(newIndex);
            cardListVIew.onScroll(1f - Math.abs(position), current, next);
        }
    }



    @Override
    public void onScrollEnd(@NonNull CardAdapter.ViewHolder holder, int position) {

    }
}
