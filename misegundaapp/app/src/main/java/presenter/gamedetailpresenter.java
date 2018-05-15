package presenter;

import java.util.ArrayList;

import Interactors.GamesInteractor;
import model.GameModel;
import view.gamedetailview;

public class gamedetailpresenter {
    private GamesInteractor interactor;
    private gamedetailview view;

    public void startPresenting(gamedetailview view){
        this.view = view;
        interactor = new GamesInteractor();
    }
    public void loanGameWithId (int id){
       GameModel game = interactor.getGameWithId(id);
       view.onGameLoaded(game);
    }

    public ArrayList<GameModel> getGames () {
        return interactor.getGames();
    }
}
