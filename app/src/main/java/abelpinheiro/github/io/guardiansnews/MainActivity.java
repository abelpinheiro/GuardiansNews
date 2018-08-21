package abelpinheiro.github.io.guardiansnews;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    //URL com que se fará a requisição
    private final String mUrl = "https://content.guardianapis.com/search?order-by=newest&show-tags=contributor&api-key=cad70b6c-a54e-4540-8452-9fe723f82359";

    //Atributo do adapter
    private NewsAdapter mAdapter;

    //TextView que será exibida caso a lista de noticias esteja vazia
    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recebe as referências da Lista e do TextView no layout e seta o TextView
        // para quando a lista esteja vazia
        ListView listView = findViewById(R.id.list);
        mEmptyTextView = findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyTextView);

        //Instanciação do adapter e o setando para a lista
        mAdapter = new NewsAdapter(this, new ArrayList<News>());
        listView.setAdapter(mAdapter);

        //Listener para abrir a url do objeto News de uma certa posição no browser
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = mAdapter.getItem(position);
                String url = currentNews.getUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        //Checagem de conexão com a internet do celular
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //Se tiver conexão, iniciar o Loader para estabelecer conexão em outra thread
        //se não encontrar, esconder o ProgressBar e setar o TextView de Erro para "sem internet"
        if (networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = LoaderManager.getInstance(this);
            loaderManager.initLoader(0, null, this);
        }else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyTextView.setText(R.string.no_internet_found);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Instancia um novo Loader para a url
    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsLoader(this, mUrl);
    }

    //É carregado quando o Loader termina sua execução
    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> news) {
        ProgressBar loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyTextView.setText(R.string.no_news_found);

        if (news != null && !news.isEmpty()){
            mAdapter.addAll(news);
        }
    }

    // Resetar o loader, limpando o adapter com os dados existentes nele
    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        mAdapter.clear();
    }
}