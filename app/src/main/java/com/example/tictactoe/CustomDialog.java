package com.example.tictactoe;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog extends DialogFragment{
    private static final String ARG_PARAM_WINNER = "Winner";
    private String winnerPlayerName;
    Button startGameAgainButton;
    TextView winnerTextView;

    public CustomDialog() {}   // Required empty public constructor
    public static CustomDialog newInstance(String winner) {
        CustomDialog fragment = new CustomDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_WINNER, winner);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            winnerPlayerName = getArguments().getString(ARG_PARAM_WINNER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_custom_dialog, container, false);
        startGameAgainButton = view.findViewById(R.id.start_game_again_btn);
        winnerTextView = view.findViewById(R.id.winner_player_name);
        winnerTextView.setText(winnerPlayerName);
        startGameAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).resetGame();
            }

        });
        return view;
    }

}