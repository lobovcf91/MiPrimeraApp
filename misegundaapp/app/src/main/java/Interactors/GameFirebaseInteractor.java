package Interactors;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import model.GameModel;

public class GameFirebaseInteractor {
    private ArrayList<GameModel> games = new ArrayList<>();

//creamos una copia de games interacor con estos metodos practica
    public void getGames(final GamesInteractorCallback callback) {
        //1- Llamar a firebase, de esta llamamos a firebasse
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myReference = firebaseDatabase.getReference("games");
        myReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //2- Obtener la lista de gamemolde , de esta maneras conseguimos la lista de game model o cualquier otro .java
                for (DataSnapshot nodojuego : dataSnapshot.getChildren()) {
                    GameModel model = nodojuego.getValue(GameModel.class);
                    Log.d("Firebase Interactor", "Game: "+ model.getName());
                    games.add(model);
                }
                //3- Notificar a callback.ongameaviable.
                callback.OnGamesAviable();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//algun error a ocurrido
            }
        });

    }

    //**
    //* constructtor

    //       public GameFirebaseInteractor() {
    //si no tengo juegos, los creo

    //          if (games == null) {

    //             GameModel LiverpoolGameModel = new GameModel(0, "Liverpool", "Liverpool",
    //                    "https://www.liverpoolfc.com/welcome-to-liverpool-fc",
    //                   "","");
    //           GameModel MalagacfGameModel = new GameModel(1, "Malaga_cf", "Descripcion Malaga_cf",
    //                  "https://www.malagacf.com/",
    //                   "","");
//
    //       GameModel BocaJuniorGameModel = new GameModel(2, "BocaJunior", "Bocajunior",
    //                     "http://www.bocajuniors.com.ar/",
    //                      "","");
    //           GameModel ValenciaGameModel = new GameModel(3, "Valencia_cf", "Descripcion Valencia_cf",
    //                   "http://www.valenciacf.com/",
    //                  "", "");
    //        GameModel ManchesterUnitedGameModel = new GameModel(4, "ManchesterUnited", "Manchester United",
    //                  "http://www.manutd.com/",
    //             "", "");


    //         games = new ArrayList<>();
    //          games.add(LiverpoolGameModel);
    //          games.add(MalagacfGameModel);
    //         games.add(BocaJuniorGameModel);
    //       games.add(ValenciaGameModel);
    //          games.add(ManchesterUnitedGameModel);
    //         }

    //     }

    public  ArrayList<GameModel> getGames() {
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

