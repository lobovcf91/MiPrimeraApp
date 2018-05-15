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

import Interactors.GamesInteractor;
import miprimeraapp.android.teaching.com.misegundaapp.R;
import model.GameModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameDetailFragment extends Fragment {


    public GameDetailFragment() {
        // Required empty public constructor
    }
// crear un fragment este parametro es para que coja el game id
    public static GameDetailFragment newInstance(int gameId){
        GameDetailFragment fragment = new GameDetailFragment();
        Bundle myBundle = new Bundle();
        myBundle.putInt("gameId", gameId);
        fragment.setArguments(myBundle);
        return  fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_game_detail, container, false);

        //Obtener game model de game interactor
        int gameId = getArguments().getInt("gameId",0);
        final GameModel game= new GamesInteractor().getGameWithId(gameId);

        // UPDATE VIEW WITH GAME MODEL DATA
        ImageView icono = fragmentView.findViewById(R.id.game_icon);
        icono.setImageResource(game.getIconoDrawable());

        //1. CAMBIAR IMAGEN DE FONDO
        ImageView fondolayout = fragmentView.findViewById(R.id.fornitefondo);
        fondolayout.setImageResource(game.getBackgroundDrawable());

        //2. CAMBIAR DESCRIPCION
        TextView descriptionTextview = fragmentView.findViewById(R.id.descripcion);
        descriptionTextview.setText(game.getDescription());

        //3 DEFINIR ACCION PARA EL BOTTON
        Button boton =  fragmentView.findViewById(R.id.website_button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(game.getOfficialwebsiteurl()));
                startActivity(webIntent);
            }
        });

        return fragmentView;

    }
    }


