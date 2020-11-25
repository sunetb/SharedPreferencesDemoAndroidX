package com.example.sharedpreferencesdemoandroidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView tekst;
    int tæller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tekst = findViewById(R.id.tv);

        //Hvis der er en streg gennem "PreferenceManager.getDefaultSharedPreferences(this)" bruger du den gamle preferencemanager. Tjek at du har en afhængighed i build.gradle(module):
        //implementation 'androidx.preference:preference:1.1.1'
        // (april 2020. Tjek om der en en nyere version her: https://developer.android.com/jetpack/androidx/releases/preference)
        //Efter at have tilføjet i build.gradle, tjek linje 7: import androidx.preference.PreferenceManager; IKKE import android.preference.PreferenceManager;
        SharedPreferences gemmeobjekt = PreferenceManager.getDefaultSharedPreferences(this);


        //Læs fra SharedPreferences
        tæller = gemmeobjekt.getInt("tal", 0); //Læs værdien "tal" på disken. Hvis den ikke findes, brug defaultværdien 0

        if (tæller == 0) {
            tekst.setText("Velkommen! Dette er første gang appen nogensinde er startet. \n (Med mindre brugeren har valgt \"Slet data\" i telefonens indstillinger)");
        }
        else {
            tekst.setText("Appen har været startet " + tæller + " gang(e) før");
        }

        tæller++;

        //Skriv til SharedPreferences
        gemmeobjekt.edit().putInt("tal",tæller).commit();




        //andre eksempeler:

        //Læs streng
        String brugernavn = gemmeobjekt.getString("navn", "Sune"); //igen: "Sune" returneres som defaultværdi, hvis der ikke tidligere er blevet lagret noget under nøglen "navn".


        //Skriv streng
        gemmeobjekt.edit().putString("navn", "Spiderman").commit();

        tekst.append("\n\n Prøv nu at lukke appen og starte den igen.\n\n Hilsen "+brugernavn);




    }
}