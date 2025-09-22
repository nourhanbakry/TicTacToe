package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tictactoe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    String playerOneNameValue;
    String playerTwoNameValue;
    int[] positions = {0,0,0,0,0,0,0,0,0};
    ArrayList<int[]> winningPlaces = new ArrayList<>(){};
    int currentPlayer= 1;   // 1 for x and 2 for o
    int totalSelectedBoxes =1;
    String winnerPlayerName;
    CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playerOneNameValue= getIntent().getStringExtra(AddPlayerActivity.PLAYER_ONE);
        playerTwoNameValue = getIntent().getStringExtra(AddPlayerActivity.PLAYER_TWO);
        binding.playerOneNameValue.setText(playerOneNameValue);
        binding.playerTwoNameValue.setText(playerTwoNameValue);
        addWinningPlacesToList();
        registerButtonsListeners();
    }
    private void addWinningPlacesToList(){
        winningPlaces.add(new int[]{0,1,2});
        winningPlaces.add(new int[]{3,4,5});
        winningPlaces.add(new int[]{6,7,8});
        winningPlaces.add(new int[]{0,3,6});
        winningPlaces.add(new int[]{1,4,7});
        winningPlaces.add(new int[]{2,5,8});
        winningPlaces.add(new int[]{0,4,8});
        winningPlaces.add(new int[]{2,4,6});
    }
    private void registerButtonsListeners(){
        binding.image0.setOnClickListener(this);
        binding.image1.setOnClickListener(this);
        binding.image2.setOnClickListener(this);
        binding.image3.setOnClickListener(this);
        binding.image4.setOnClickListener(this);
        binding.image5.setOnClickListener(this);
        binding.image6.setOnClickListener(this);
        binding.image7.setOnClickListener(this);
        binding.image8.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.image_0){
             if(isBoxSelected(0)){
                  play(binding.image0,0);
             }

        } else if(id == R.id.image_1){
            if(isBoxSelected(1)){
                play(binding.image1,1);
            }

        }else if(id == R.id.image_2){
            if(isBoxSelected(2)){
                play(binding.image2,2);
            }

        }else if(id == R.id.image_3){
            if(isBoxSelected(3)){
                play(binding.image3,3);
            }

        }else if(id == R.id.image_4){
            if(isBoxSelected(4)){
                play(binding.image4,4);
            }

        }else if(id == R.id.image_5){
            if(isBoxSelected(5)){
                play(binding.image5,5);
            }

        }else if(id == R.id.image_6){
            if(isBoxSelected(6)){
                play(binding.image6,6);
            }

        }else if(id == R.id.image_7){
            if(isBoxSelected(7)){
                play(binding.image7,7);
            }


        }else if(id == R.id.image_8){
            if(isBoxSelected(8)){
                play(binding.image8,8);
            }

        }
    }
    private void play(ImageView image,int position){
        positions[position] = currentPlayer;
        if (currentPlayer == 1){
          playerXPlays(image,position);
        }else{
           playerOPlays(image,position);
        }
    }
    private void playerXPlays(ImageView image,int position){
        image.setImageResource(R.drawable.ximage);
        if (checkWin(position)) {
            winnerPlayerName = binding.playerOneNameValue.getText().toString();
            customDialog =  CustomDialog.newInstance(winnerPlayerName);
            customDialog.show(getSupportFragmentManager(),null);
        } else if (totalSelectedBoxes == 9){
             customDialog =  CustomDialog.newInstance("No one");
             customDialog.show(getSupportFragmentManager(),null);
        } else{
            changePlayer(2);
            totalSelectedBoxes++;
        }
    }
    private void playerOPlays(ImageView image,int position){
        image.setImageResource(R.drawable.oimage);
        if (checkWin(position)){
            winnerPlayerName = binding.playerTwoNameValue.getText().toString();
            customDialog =  CustomDialog.newInstance(winnerPlayerName);
            customDialog.show(getSupportFragmentManager(),null);
        } else if (totalSelectedBoxes ==9){
            customDialog =  CustomDialog.newInstance("No one");
            customDialog.show(getSupportFragmentManager(),null);
        } else{
            changePlayer(1);
            totalSelectedBoxes++;
        }
    }
    private boolean checkWin(int position){
        boolean responce= false;
         for (int i=0;i<winningPlaces.size();i++){
             int[] winningPlace = winningPlaces.get(i);
             if(positions[winningPlace[0]]== currentPlayer&& positions[winningPlace[1]]== currentPlayer&& positions[winningPlace[2]]==currentPlayer){
                  responce = true;
             }
         }
        return responce;
    }
    private void changePlayer(int nextPlayerTurn){
        currentPlayer = nextPlayerTurn;
        if (currentPlayer == 1) {
            binding.playerOneBox.setBackgroundResource(R.drawable.black_border);
            binding.playerTwoBox.setBackgroundResource(R.drawable.white_box);
        } else{
            binding.playerTwoBox.setBackgroundResource(R.drawable.black_border);
            binding.playerOneBox.setBackgroundResource(R.drawable.white_box);
        }

    }
    public  void resetGame(){
        positions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        currentPlayer = 1;
        totalSelectedBoxes =1;
        binding.playerTwoBox.setBackgroundResource(R.drawable.white_box);
        binding.playerOneBox.setBackgroundResource(R.drawable.white_box);
        binding.image0.setImageResource(R.drawable.white_box);
        binding.image1.setImageResource(R.drawable.white_box);
        binding.image2.setImageResource(R.drawable.white_box);
        binding.image3.setImageResource(R.drawable.white_box);
        binding.image4.setImageResource(R.drawable.white_box);
        binding.image5.setImageResource(R.drawable.white_box);
        binding.image6.setImageResource(R.drawable.white_box);
        binding.image7.setImageResource(R.drawable.white_box);
        binding.image8.setImageResource(R.drawable.white_box);
        customDialog.dismiss();

   }
    private boolean isBoxSelected(int boxPosition){
        boolean isSelected = false;
        if(positions[boxPosition] ==0){
            isSelected=true;
        }
        return isSelected;
    }
}