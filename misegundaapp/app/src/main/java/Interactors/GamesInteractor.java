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

            GameModel ValenciaGameModel = new GameModel(0, "Valencia_cf", "Valencia_cf",
                    "www.valenciacf.com",
                    "","");
            GameModel MalagacfGameModel = new GameModel(1, "Malaga_cf", "Descripcion Malaga_cf",
                    "https://www.malagacf.com/",
                    "","");
//
//            GameModel BocaJuniorGameModel = new GameModel(2, "BocaJunior", "BocaJunior",
  //                  "http://www.bocajuniors.com.ar/",
    //                "","");
      //      GameModel ValenciaGameModel = new GameModel(3, "Valencia_cf", "Descripcion Valencia_cf",
        //            "http://www.valenciacf.com/",
          //          "", "");
          //  GameModel ManchesterUnitedGameModel = new GameModel(4, "ManchesterUnited", "ManchesterUnited",
            //        "http://www.manutd.com/",
           //        "", "");


            games = new ArrayList<>();
    //        games.add(LiverpoolGameModel);
            games.add(MalagacfGameModel);
   //         games.add(BocaJuniorGameModel);
            games.add(ValenciaGameModel);
     //       games.add(ManchesterUnitedGameModel);
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