package uk.co.roryharrison5hotmail.lolanalytics;

/**
 * Created by roryh on 08/03/2018.
 */

public class ChampionData {

    private int id;
    private String name;
    private String role;
    private int winRate;
    private int kda;

    ChampionData(int championID){
        this.id=championID;

    }

    public String getName(){

        return this.name;
    }
}
