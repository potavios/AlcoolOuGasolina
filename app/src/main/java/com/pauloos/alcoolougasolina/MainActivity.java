package com.pauloos.alcoolougasolina;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText gasolina, alcool;
    private TextView resultado;
    private Button calcularBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gasolina = findViewById(R.id.inputGasolina);
        alcool = findViewById(R.id.inputAlcool);
        resultado = findViewById(R.id.resultado);
        calcularBtn = findViewById(R.id.botaoCalcular);


        //Para esconder o teclado caso o foco seja tirado deste EditText
        gasolina.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    hideKeyboardFrom(getApplicationContext(), view);
                }
            }
        });


        //Para esconder o teclado caso o foco seja tirado deste EditText
        alcool.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    hideKeyboardFrom(getApplicationContext(), view);
                }
            }
        });



        calcularBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Usar este método caso o botão esteja aparecendo
                //hideKeyboardFrom(getApplicationContext(), view);

                String valorGasolina = gasolina.getText().toString();
                String valorAlcool = alcool.getText().toString();
                Boolean validarCampos = validarCampos( valorGasolina, valorAlcool );

                String mensagem = "Vale mais a pena abastecer com ";

                if( validarCampos ){
                    double mvalorGasolina = Double.parseDouble( gasolina.getText().toString());
                    double mvalorAlcool = Double.parseDouble( alcool.getText().toString());

                    String melhorResultado = calcularMelhor(mvalorGasolina, mvalorAlcool);

                        resultado.setText( mensagem + " " + melhorResultado);

                } else {
                    resultado.setTextColor(getResources().getColor(R.color.colorAccent));
                    resultado.setText("Favor preencher os campos.");
                }

            }
        });



    }

    public boolean validarCampos(String gasolina, String alcool) {

        boolean camposValidados = true;
        if ( gasolina == null || gasolina.equals("") || alcool == null || alcool.equals("") ) {
                camposValidados = false;
        }
        return camposValidados;

    }

    public String calcularMelhor(double gasolina, double alcool){
        double resultado = alcool / gasolina;
        if( resultado >= 0.7){
            return "gasolina";
        } else {
            return "alcool";
        }

    }

    public static void hideKeyboardFrom(Context context, View view) {

        if(view != null) {

            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }

}
