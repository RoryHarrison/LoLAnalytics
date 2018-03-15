package uk.co.roryharrison5hotmail.lolanalytics.Web.JsonFormats;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by roryh on 14/03/2018.
 */

public class ChampionStats {

    /**
     * _id : {"championId":103,"role":"MIDDLE"}
     * elo : PLATINUM
     * patch : 8.5
     * championId : 103
     * winRate : 0.5175814751286449
     * playRate : 0.04682826523566914
     * gamesPlayed : 23320
     * percentRolePlayed : 0.7721087309207695
     * banRate : 0.002651325880481496
     * role : MIDDLE
     */

    private IdBean _id;
    private String elo;
    private String patch;
    private int championId;
    private double winRate;
    private double playRate;
    private int gamesPlayed;
    private double percentRolePlayed;
    private double banRate;
    private String role;

    public ChampionStats(){
    }

    public static ChampionStats parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        ChampionStats championStats = gson.fromJson(response, ChampionStats.class);
        return championStats;
    }

    public IdBean get_id() {
        return _id;
    }

    public String getElo() {
        return elo;
    }

    public String getPatch() {
        return patch;
    }

    public int getChampionId() {
        return championId;
    }

    public double getWinRate() {
        return winRate;
    }

    public double getPlayRate() {
        return playRate;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public double getPercentRolePlayed() {
        return percentRolePlayed;
    }

    public double getBanRate() {
        return banRate;
    }

    public String getRole() {
        return role;
    }

    public static class IdBean {
        /**
         * championId : 103
         * role : MIDDLE
         */

        private int championId;
        private String role;

        public int getChampionId() {
            return championId;
        }

        public String getRole() {
            return role;
        }
    }
}
