package com.example.weterynarz;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SeekBar seekWiek;
    EditText name;
    EditText cel;
    EditText czas;
    ListView lista;
    TextView textwiek;
    TextView tekstWynik;
    String elementWybrany;
    Button przycisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        seekWiek = findViewById(R.id.seekWiek);
        name = findViewById(R.id.editImie);
        cel = findViewById(R.id.editCel);
        czas = findViewById(R.id.inputCzas);
        lista = findViewById(R.id.listElementy);
        textwiek = findViewById(R.id.textWiek);
        tekstWynik = findViewById(R.id.textWynik);
        przycisk = findViewById(R.id.button);
        String[] tablica_elementow = {"Pies", "Kot", "Świnka morska"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tablica_elementow);
        lista.setAdapter(arrayAdapter);

        seekWiek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            };
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            };
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String wiek = seekWiek.getProgress() + "";
                textwiek.setText(wiek);
            };
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng){
                elementWybrany = (String) lista.getItemAtPosition(myItemInt);
                if(elementWybrany == "Pies"){
                    seekWiek.setMax(18);
                } else if (elementWybrany == "Kot") {
                    seekWiek.setMax(20);
                }
                else if(elementWybrany == "Świnka morska"){
                    seekWiek.setMax(9);
                }
            }
        });

        przycisk.setOnClickListener(v-> wyswietlDane());


    }

    public void wyswietlDane(){
        String imie = name.getText().toString();
        String goal = cel.getText().toString();
        String time = czas.getText().toString();
        int wiek = seekWiek.getProgress();
        tekstWynik.setText(imie + ", " + elementWybrany + "," + wiek + ", " + goal + ", " + time);
    };

}