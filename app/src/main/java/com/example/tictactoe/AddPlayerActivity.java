package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tictactoe.databinding.ActivityAddPlayerBinding;

import java.util.Locale;

public class AddPlayerActivity extends AppCompatActivity {
    ActivityAddPlayerBinding binding;
    public static final String PLAYER_ONE = "PlayerOne";
    public static final String PLAYER_TWO = "PlayerTwo";
    String playerOneName;
    String  playerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddPlayerBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.addPlayer, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 playerOneName = binding.playerOneTextName.getText().toString().trim();
                 playerTwoName = binding.playerTwoTextName.getText().toString().trim();
                if (playerOneName.isEmpty() && playerTwoName.isEmpty() ){
                    binding.playerOneTextName.setError("Please Enter Player One Name");
                    binding.playerTwoTextName.setError("Please Enter Player Two Name");
                }
                else if (playerOneName.isEmpty()){
                    binding.playerTwoTextName.setError(null);
                    binding.playerOneTextName.setError("Please Enter Player One Name");
               } else if (playerTwoName.isEmpty()){
                    binding.playerOneTextName.setError(null);
                    binding.playerTwoTextName.setError("Please Enter Player Two Name");
               } else{
                   Intent intent = new Intent(AddPlayerActivity.this, MainActivity.class);
                   intent.putExtra(PLAYER_ONE,playerOneName);
                   intent.putExtra(PLAYER_TWO,playerTwoName);
                   startActivity(intent);
               }
            }
        });
    }
}