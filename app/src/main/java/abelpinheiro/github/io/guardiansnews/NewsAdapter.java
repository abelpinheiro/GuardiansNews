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


    /**
     *
     *  Instancia um novo {@link NewsAdapter}
     *
     * @param context contexto atual do app
     * @param newsArrayList Lista de noticias
     */
    NewsAdapter(@NonNull Context context, List<News> newsArrayList) {
        super(context, 0, newsArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Checa se convertView está vazia. Se sim, infla o layout de uma noticia
        View rootView = convertView;
        if (rootView == null){
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_noticia, parent, false);
        }

        //Recebe um objeto News de uma certa posição da lista
        News currentNews = getItem(position);

        //Obtém o id da TextView do titulo contido no layout e seta o texto para o atributo title do objeto atual
        TextView tituloView = (TextView) rootView.findViewById(R.id.titulo);
        tituloView.setText(currentNews.getTitle());

        //Obtém o id da TextView da seção contido no layout e seta o texto para o atributo section do objeto atual
        TextView secaoView = (TextView) rootView.findViewById(R.id.secao);
        secaoView.setText(currentNews.getSection());

        //Obtém o id da TextView da data contido no layout e seta o texto para o atributo date do objeto atual
        TextView dataView = (TextView) rootView.findViewById(R.id.data);
        dataView.setText(currentNews.getDate());

        //Obtém o id da TextView do autor contido no layout e seta o texto para o atributo author do objeto atual
        TextView authorView = (TextView) rootView.findViewById(R.id.autor);
        authorView.setText(currentNews.getAuthor());

        return rootView;
    }

}