package com.imcapp.andreszamora.imcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class Principal extends AppCompatActivity {
EditText etPeso,etAltura;
TextView resultado;
Button btnCalcular;
ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        etPeso=(EditText) findViewById(R.id.etPeso);
        etAltura=(EditText) findViewById(R.id.etAltura);
        resultado=(TextView) findViewById(R.id.tvresultado);
        btnCalcular=(Button) findViewById(R.id.btnCalcular);
        imagen=(ImageView) findViewById(R.id.ivimagen);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float peso,altura,IMC;
                try {
                    peso = Float.parseFloat(etPeso.getText().toString());
                    altura = Float.parseFloat(etAltura.getText().toString());
                    IMC = (float) (peso / Math.pow((double) altura, 2));
                    resultado.setText("IMC: " + IMC);
                    InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etAltura.getWindowToken(), 0);

                    if(peso==0||altura==0){
                     throw new NumberFormatException();
                    }else {
                        byte caso;
                        if (IMC < 18.5) {
                            caso = 1;
                        } else if (IMC > 18.5 && IMC < 24.99) {
                            caso = 2;
                        } else if (IMC > 25.0 && IMC < 30.0) {
                            caso = 3;
                        } else {
                            caso = 4;
                        }
                        switch (caso) {
                            case 1:
                                Crouton.makeText(Principal.this, "Bajo Peso", Style.INFO).show();
                                Picasso.with(getApplicationContext()).load(R.drawable.flaca).into(imagen);
                                break;
                            case 2:
                                Crouton.makeText(Principal.this, "Peso Normal", Style.CONFIRM).show();
                                Picasso.with(getApplicationContext()).load(R.drawable.normal).into(imagen);
                                //Picasso.with(getApplicationContext()).load("http://d3ustg7s7bf7i9.cloudfront.net/mmediafiles/pl/a6/a6540e59-47a7-4817-ad84-005147cc3fd1_879_586.jpg").into(imagen);
                                break;
                            case 3:
                                Crouton.makeText(Principal.this, "Sobre Peso", Style.ALERT).show();
                                Picasso.with(getApplicationContext()).load(R.drawable.sobrepeso).into(imagen);
                                break;
                            case 4:
                                Crouton.makeText(Principal.this, "Obesidad", Style.ALERT).show();
                                Picasso.with(getApplicationContext()).load(R.drawable.obesidad).into(imagen);
                                break;
                        }
                    }}catch (NumberFormatException nfe){
                    Crouton.makeText(Principal.this,"Datos Erroneos", Style.ALERT).show();
                    Toast.makeText(getApplicationContext(),"Datos Erroneos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
