package abelpinheiro.github.io.guardiansnews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

public class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(@NonNull Context context, List<News> newsArrayList) {
        super(context, 0, newsArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        if (rootView == null){
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_noticia, parent, false);
        }

        News currentNews = getItem(position);

        TextView tituloView = (TextView) rootView.findViewById(R.id.titulo);
        tituloView.setText(currentNews.getTitle());

        TextView secaoView = (TextView) rootView.findViewById(R.id.secao);
        secaoView.setText(currentNews.getSection());

        TextView dataView = (TextView) rootView.findViewById(R.id.data);
        dataView.setText(currentNews.getDate());

        return rootView;
    }

    /*private String formatDate(String dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        return dateFormat.format(dateObject);
    }*/
}