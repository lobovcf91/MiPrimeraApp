package Interactors;

import java.util.ArrayList;

import miprimeraapp.android.teaching.com.misegundaapp.R;
import model.GameModel;

public class GamesInteractor {
    private static ArrayList<GameModel> games;

    //**
    //* constructtor

    public GamesInteractor() {
        //si no tengo juegos, los creo

        if (games == null) {

            GameModel SpyroGameModel = new GameModel(0, "Spyro", "EL DRAGONSITO",
                    "http://www.spyrothedragon.com/es",
                    R.drawable.spyro, R.drawable.spyrofondo);
            GameModel MalagacfGameModel = new GameModel(1, "Malaga_cf", "Descripcion Malaga_cf",
                    "https://www.malagacf.com/",
                    R.drawable.malagaicono, R.drawable.malagacf);

            GameModel rachetGameModel = new GameModel(2, "Rachet", "Rachet y clanc",
                    "https://www.playstation.com/es-es/games/ratchet-and-clank-ps4/",
                    R.drawable.rachet, R.drawable.rachetandclanck);
            GameModel ValenciaGameModel = new GameModel(3, "Valencia_cf", "Descripcion Valencia_cf",
                    "http://www.valenciacf.com/",
                    R.drawable.valenciacficono, R.drawable.externas);
            GameModel ForniteGameModel = new GameModel(4, "Fornite", "Descripcion Fornite",
                    "http://www.epicgames.com/fortnite/es-ES/battle-pass/season-4",
                    R.drawable.fornitelogo, R.drawable.fornite);


            games = new ArrayList<>();
            games.add(SpyroGameModel);
            games.add(MalagacfGameModel);
            games.add(rachetGameModel);
            games.add(ValenciaGameModel);
            games.add(ForniteGameModel);
        }

    }

    public static ArrayList<GameModel> getGames() {
        return games;
    }

    public GameModel getGameWithId(int id) {
        //Obtener de games el juego con el identificador id
        for (GameModel game : games) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;

    }

}