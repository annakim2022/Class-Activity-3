package com.example.classactivity3;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<Weather> weathers;

    public WeatherAdapter(List<Weather> weathers) {
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View weatherView = inflater.inflate(R.layout.item_weather, parent, false);
        ViewHolder viewHolder = new ViewHolder(weatherView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Weather weather = weathers.get(position);
        holder.textView_dt.setText((weather.getDt()));
        holder.textView_main.setText(weather.getMain());
        holder.textView_weather.setText(weather.getWeather());

    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_dt;
        TextView textView_weather;
        TextView textView_main;

        public ViewHolder (View itemView) {
            super (itemView);

            textView_dt = itemView.findViewById(R.id.textView_dt);
            textView_weather = itemView.findViewById(R.id.textView_weather);
            textView_main = itemView.findViewById(R.id.textView_main);
        }
    }

}
