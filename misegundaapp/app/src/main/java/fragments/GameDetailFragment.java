package fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import Interactors.GameFirebaseInteractor;
import Interactors.GamesInteractor;
import Interactors.GamesInteractorCallback;
import miprimeraapp.android.teaching.com.misegundaapp.R;
import miprimeraapp.android.teaching.com.misegundaapp.WebViewActivity;
import model.GameModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameDetailFragment extends Fragment {
private GameFirebaseInteractor gameFirebaseInteractor;

    public GameDetailFragment() {
        // Required empty public constructor
    }

    // crear un fragment este parametro es para que coja el game id
    public static GameDetailFragment newInstance(int gameId) {
        GameDetailFragment fragment = new GameDetailFragment();
        Bundle myBundle = new Bundle();
        myBundle.putInt("gameId", gameId);
        fragment.setArguments(myBundle);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_game_detail, container, false);

        //Obtener game model de game interactor
        final int gameId = getArguments().getInt("gameId", 0);
        gameFirebaseInteractor = new GameFirebaseInteractor();
        gameFirebaseInteractor.getGames(new GamesInteractorCallback() {
            @Override
            public void OnGamesAviable() {
                final GameModel game = gameFirebaseInteractor.getGameWithId(gameId);

                // UPDATE VIEW WITH GAME MODEL DATA
                ImageView icono = getView().findViewById(R.id.game_icon);
                Glide.with(getView()).load(
                        game.getIcon())
                        .into(icono);
                //       icono.setImageResource(game.getIcono());

                //1. CAMBIAR IMAGEN DE FONDO
                ImageView fondolayout = getView().findViewById(R.id.fornitefondo);
                Glide.with(getView()).load(
                        game.getBackground())
                        .into(fondolayout);
                //   fondolayout.setImageResource(game.getBackground());

                //2. CAMBIAR DESCRIPCION
                TextView descriptionTextview = getView().findViewById(R.id.descripcion);
                descriptionTextview.setText(game.getDescription());

                //3 DEFINIR ACCION PARA EL BOTTON
                Button boton = getView().findViewById(R.id.website_button);
                boton.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        Intent oficial = new Intent(getContext(), WebViewActivity.class);
                        oficial.putExtra("url", game.getOfficialwebsiteurl());
                        startActivity(oficial);
                    }
                });
            }
        });






        return fragmentView;
    }


}

