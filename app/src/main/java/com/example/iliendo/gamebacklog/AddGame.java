package com.example.iliendo.gamebacklog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddGame extends AppCompatActivity {

    private EditText title;
    private EditText platform;
    private Spinner statusSelection;
    private Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        initToolbar();
        initFAB();

        title = findViewById(R.id.titleEditor);
        platform = findViewById(R.id.platformEditor);
        statusSelection = findViewById(R.id.statusSpinner);

        game = getIntent().getParcelableExtra(MainActivity.UPDATE_GAME);
        if (game != null){
            title.setText(game.getTitle());
            platform.setText(game.getPlatform());
            statusSelection.setSelection(((ArrayAdapter)statusSelection.getAdapter()).getPosition(game.getStatus()));
            this.setTitle(R.string.title_activity_add_game_update);
        }
        else
            this.setTitle(R.string.title_activity_add_game_new);
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initFAB(){
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText = title.getText().toString();
                String platformText = platform.getText().toString();
                String statusText = statusSelection.getSelectedItem().toString();

                if (!TextUtils.isEmpty(titleText) && !TextUtils.isEmpty(platformText)) {
                    if (game != null){
                        game.setTitle(titleText);
                        game.setPlatform(platformText);
                        game.setStatus(statusText);

                        FinishActivity(MainActivity.UPDATE_GAME, game);
                    }
                    else{
                        game = new Game(titleText, platformText, statusText);

                        FinishActivity(MainActivity.NEW_GAME, game);
                    }

                }
                else
                    Snackbar.make(view, R.string.emptyTextfieldMessage, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void FinishActivity(String activityType, Game someGame){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(activityType, someGame);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
