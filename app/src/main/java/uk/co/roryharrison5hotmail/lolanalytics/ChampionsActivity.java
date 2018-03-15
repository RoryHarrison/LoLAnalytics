package uk.co.roryharrison5hotmail.lolanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import uk.co.roryharrison5hotmail.lolanalytics.Adapters.ImageAdapter;

public class ChampionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.championsactivity);

        final int[] championIDs = getResources().getIntArray(R.array.championIDs);
        final String[] championNames = getResources().getStringArray(R.array.championsNames);
        final String[] championSquares = getResources().getStringArray(R.array.championSquares);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent championPage = new Intent(ChampionsActivity.this, ChampionPage.class);

                Bundle extras = new Bundle();
                extras.putString("name", championNames[position]);
                extras.putInt("id", championIDs[position]);
                extras.putString("square", championSquares[position]);
                championPage.putExtras(extras);
                startActivity(championPage);
            }
        });
    }
}
