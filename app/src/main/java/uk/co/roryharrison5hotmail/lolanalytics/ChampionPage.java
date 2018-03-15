package uk.co.roryharrison5hotmail.lolanalytics;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import uk.co.roryharrison5hotmail.lolanalytics.Web.APICommunicator;
import uk.co.roryharrison5hotmail.lolanalytics.Web.JsonFormats.ChampionBasics;
import uk.co.roryharrison5hotmail.lolanalytics.Web.JsonFormats.ChampionStats;
import uk.co.roryharrison5hotmail.lolanalytics.Web.VolleyCallback;

import static uk.co.roryharrison5hotmail.lolanalytics.R.id.winRate;


public class ChampionPage extends AppCompatActivity {

    TextView titleView;
    TextView winRateText;
    TextView pickRateText;
    TextView banRateText;
    TextView loreText;
    TextView tierText;
    ProgressBar winRateBar;

    int champID;
    String champSquare;
    String champName;

    ChampionBasics championBasics;

    APICommunicator api;
    VolleyCallback resultCallback;

    int CHAMPION_BASICS = 0;
    int CHAMPION_STATS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_page);

        //Initialise Abstract Methods
        initVolleyCallback();

        //Manage extras
        Bundle extras = getIntent().getExtras();
        this.champID = extras.getInt("id");
        this.champName = extras.getString("name");
        this.champSquare = extras.getString("square");

        //Instantiate Views
        titleView = (TextView) findViewById(R.id.titleTextView);
        loreText = (TextView) findViewById(R.id.loreText);
        tierText = (TextView) findViewById(R.id.tierText);
        winRateBar = (ProgressBar) findViewById(winRate);
        winRateText = (TextView) findViewById(R.id.winRateText);
        pickRateText  = (TextView) findViewById(R.id.pickRateText);
        banRateText = (TextView) findViewById(R.id.banRateText);

        api = new APICommunicator(resultCallback,this);
        //NOTE: I have applied for a better API key to allow for more requests but haven't heard back yet
        api.getDataVolley("GETCALL","https://na1.api.riotgames.com/lol/static-data/v3/champions/"+champID+"?locale=en_US&champData=lore&api_key=RGAPI-7a0f3501-4daa-4c24-8d78-bd571009ce06", CHAMPION_BASICS);
        api.getDataVolley("GETCALL", "http://api.champion.gg/v2/champions/"+champID+"?elo=PLATINUM&api_key=bdeae1f47c18bbfab9bef58168c6d2d6", CHAMPION_STATS);

        ChampionPage.this.setTitle(champName);
        ImageView squareView = (ImageView) findViewById(R.id.squareView);
        int resID = getResources().getIdentifier(champSquare, "drawable", getPackageName());
        squareView.setImageResource(resID);
    }

    private void setProgressBar(double wr){
        int i = (int)Math.round(wr*100);
        winRateBar.setProgress(i);
        winRateText.setText(Integer.toString(i)+"%");
    }
    private void setPickBanText(double pr, double br){
        int p = (int)Math.round(pr*100);
        int b = (int)Math.round(br*100);

        if(p>=1){
            pickRateText.setText(Integer.toString(p)+"%");
        }else{
            pickRateText.setText(">1%");
        }

        if(b>=1){
            banRateText.setText(Integer.toString(b)+"%");
        }else{
            banRateText.setText(">1%");
        }
    }

    private void setTier(double wr) {
        int i = (int)Math.round(wr*100);
        if(i>=53){
            tierText.setText("S");
            tierText.setTextColor(ContextCompat.getColor(this, R.color.tierGold));
        }else if(i>=50&&i<53){
            tierText.setText("A");
            tierText.setTextColor(ContextCompat.getColor(this, R.color.winRateGreen));
        }else if(i<50&&i>46){
            tierText.setText("B");
            tierText.setTextColor(ContextCompat.getColor(this, R.color.tierOrange));
        }else if(i<47){
            tierText.setText("C");
            tierText.setTextColor(ContextCompat.getColor(this, R.color.tierRed));
        }else{
            tierText.setText("");
            tierText.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        }
    }

    void initVolleyCallback() {
        resultCallback = new VolleyCallback() {
            @Override
            public void notifySuccess(String requestType,String response, int call) {
                switch(call){
                    case 0:
                        championBasics = championBasics.parseJSON(response);
                        String title = championBasics.getTitle();
                        String lore = championBasics.getLore();
                        title = title.toUpperCase().charAt(0)+title.substring(1,title.length());
                        titleView.setText(title);
                        loreText.setText(lore);
                        break;
                    case 1:
                        Gson gson = new GsonBuilder().create();
                        List<ChampionStats> championStats = Arrays.asList(gson.fromJson(response, ChampionStats[].class));
                        ChampionStats champStats = championStats.get(0);
                        setProgressBar(champStats.getWinRate());
                        setPickBanText(champStats.getPlayRate(), champStats.getBanRate());
                        setTier(champStats.getWinRate());
                        break;
                }
            }

            @Override
            public void notifyError(String requestType,VolleyError error) {
            }
        };
    }
}