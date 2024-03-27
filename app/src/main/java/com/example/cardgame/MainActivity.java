package com.example.cardgame;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView;

        Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12;

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.dolphin);
        images.add(R.drawable.dinosaur);
        images.add(R.drawable.duck);
        images.add(R.drawable.flamingo);
        images.add(R.drawable.penguin);
        images.add(R.drawable.shark);
        images.add(R.drawable.dolphin);
        images.add(R.drawable.dinosaur);
        images.add(R.drawable.duck);
        images.add(R.drawable.flamingo);
        images.add(R.drawable.penguin);
        images.add(R.drawable.shark);

        Button[] buttons = {
                findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3),
                findViewById(R.id.button4), findViewById(R.id.button5), findViewById(R.id.button6),
                findViewById(R.id.button7), findViewById(R.id.button8), findViewById(R.id.button9),
                findViewById(R.id.button10), findViewById(R.id.button11), findViewById(R.id.button12)
        };

        final boolean[] turnOver = {false}; // ???
        final int[] lastClicked = {-1}; //neden?
        final int[] clicked = {0}; // Bu kısmı anlayamadım
        int tersyüz = R.drawable.heart;

        Collections.shuffle(images);

        for (int i = 0; i < 12; i++) {
            buttons[i].setBackgroundResource(tersyüz);
            buttons[i].setText("tersyüz");
            buttons[i].setTextSize((float)0);
            final int i2 = i;
            final boolean[] finalTurnOver = {turnOver[0]};
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (buttons[i2].getText() == "tersyüz" && !finalTurnOver[0] && clicked[0] < 2) {
                        buttons[i2].setBackgroundResource(images.get(i2));
                        buttons[i2].setText(images.get(i2));
                        if (clicked[0] == 0) {
                            lastClicked[0] = i2;
                        }
                        clicked[0]++;
                    } else if (!"tersyüz".equals(buttons[i2].getText())) {
                        buttons[i2].setBackgroundResource(tersyüz);
                        buttons[i2].setText("tersyüz");
                        clicked[0]--;
                    }

                    int sontiklama = lastClicked[0];
                    if (clicked[0] == 2) {
                        turnOver[0] = true;
                        if (buttons[i2].getText() == buttons[lastClicked[0]].getText()) {
                            buttons[i2].setClickable(false);
                            buttons[sontiklama].setClickable(false);
                            turnOver[0] = false;
                            clicked[0] = 0;
                            boolean allMatched = true;
                            for (Button button : buttons) {
                                if (button.isClickable()) {
                                    allMatched = false;
                                    break; //anlamadım
                                }
                            }
                            if (allMatched) {
                                TextView winMessageTextView = findViewById(R.id.winMessageTextView);
                                winMessageTextView.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    buttons[i2].setBackgroundResource(tersyüz);
                                    buttons[i2].setText("tersyüz");
                                    buttons[sontiklama].setBackgroundResource(tersyüz);
                                    buttons[sontiklama].setText("tersyüz");
                                    clicked[0] = 0;
                                    turnOver[0] = false;
                                }
                            }, 1000);
                        }
                    } else if (clicked[0] == 0) {
                        turnOver[0] = false;
                        }
                    }

        });
    }
}
}
