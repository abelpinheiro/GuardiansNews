package abelpinheiro.github.io.guardiansnews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    //Url que estabelecerá a conexão HTTP
    private String mUrl;

    /**
     *
     * Instancia um novo {@link NewsLoader}
     *
     * @param context contexto atual do app
     * @param url link que será feito a requisição HTTP
     */
    NewsLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    //Vai ser executado em outra thread para realizar a conexão com a internet
    @Nullable
    @Override
    public List<News> loadInBackground() {
        if (mUrl == null){
            return null;
        }

        List<News> news = QueryUtils.fetchNewsData(mUrl);
        return news;
    }
}
