package miprimeraapp.android.teaching.com.misegundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar myToolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        WebView myWebView = (WebView) findViewById(R.id.web_view);
      myWebView.setWebViewClient(new WebViewClient());

      String urlToload = getIntent().getStringExtra("url");
      myWebView.loadUrl(urlToload);










    }


}
