package com.example.tempdereaction;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private Chronometer chrono;
    private TextView monTexte;
     Handler h = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button2);
        chrono = findViewById(R.id.chronometer);
        monTexte = findViewById(R.id.affichEssai);

        chrono.setVisibility(View.GONE);
        debutJeu();


    }


// A gerer: Appuis du boutton avant que ca devienne jaune
// Et affiche du alert a la fin

    public void debutJeu(){



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // si on a clique ici , c que forcement on a clique lorsque l'ecran etait gris
                btn.setBackgroundColor(Color.RED);
                btn.setText("Vous avez cliquez le boutton avant qu'il devienne jaune");

                h.postDelayed(new Runnable() {
                    @Override
                    public void run() { // on rendre ici apres que le nb de seconde ait passe!!

                        debutJeu();
                    }

                }, 1500);

            }
        });







        Random random = new Random();
        int nbRand = (random.nextInt(10 - 3 + 1) + 3) *1000;
        Toast.makeText(this, "nb de seconde a attendre "+nbRand /1000, Toast.LENGTH_SHORT).show();
        // Source: https://stackoverflow.com/questions/9973289/how-can-i-run-my-code-after-activity-is-made-visible
        h.postDelayed(new Runnable() {
            @Override
            public void run() { // on rendre ici apres que le nb de seconde ait passe!!
                chrono.setBase(SystemClock.elapsedRealtime()); // reset mon chronometre
                chrono.stop();


                chrono.setVisibility(View.VISIBLE);// rend mon chronometre visible
                chrono.start();
                btn.setBackgroundColor(Color.YELLOW);
                btn.setText("Veuillez cliquez sur le boutton");

                deroulementJeu();
            }

        }, nbRand); // on attend nbRand secondes avant d'executer ce qui est dans le run();



        // Demander a tpeiste pk comme ca ca passe pas comme ca!!

      /*  Random random = new Random();
        int nbRand = random.nextInt(10 - 3 + 1) + 3;
        long startTime = System.currentTimeMillis();
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;

        while(Math.floor(elapsedSeconds)!=nbRand){
             elapsedTime = System.currentTimeMillis() - startTime;
             elapsedSeconds = elapsedTime / 1000;

        }

            Toast.makeText(this, "Mon nombre aleatoire " + nbRand, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Mon nombre de secondes " + elapsedSeconds, Toast.LENGTH_LONG).show();

*/

    }

    public void deroulementJeu(){

            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.stop();
                btn.setBackgroundColor(Color.GREEN);
                btn.setText("Bravo, vous avez cliquez sur le boutton");

                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {



                        String nbEssaiUser = monTexte.getText().toString();

                        String[] array = nbEssaiUser.split(" ");
                        int augmentationEssai = Integer.parseInt(array[1]) +1;


                        if(!(nbEssaiUser.equals("Essai 5 de 5"))){

                            btn.setBackgroundColor(Color.GRAY);
                            monTexte.setText("Essai "+augmentationEssai+" de 5");
                            btn.setText("Attendez que le boutton devienne jaune");
                            debutJeu();

                        }

                    }

                }, 1500); // apres 1.5 secondes j'affiche le message
                // Arrive ici les 5 essais ont ete fait : (Phase final)


            }
        });

    }


}
