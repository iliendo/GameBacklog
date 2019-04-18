package com.example.iliendo.gamebacklog;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Responsible for the data manipulation showing in the ui
 */
public class MainViewModel extends AndroidViewModel {

    private GameRepository gameRepository;
    private LiveData<List<Game>> gamesList;

    // Gets the information of the game repository
    public MainViewModel(@NonNull Application application) {
        super(application);
        gameRepository = new GameRepository(application.getApplicationContext());
        gamesList = gameRepository.getAllGames();
    }

    public LiveData<List<Game>> getGamesList(){
        return gamesList;
    }

    public void insert(Game game){
        gameRepository.insert(game);
    }
    public void update(Game game){
        gameRepository.update(game);
    }
    public void delete(Game game){
        gameRepository.delete(game);
    }
    public void deleteAll(List<Game> gameList){
        gameRepository.deleteAll(gameList);
    }
    public void insertAll(List<Game> gameList){
        gameRepository.insertAll(gameList);
    }

}
