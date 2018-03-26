package com.example.sincronismo.calcularoleo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText proporcao;
    private EditText quantidadeGasolina;
    private Button consultaTabela;
    private Button calcular;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proporcao = (EditText) findViewById(R.id.proporcao_id);
        quantidadeGasolina = (EditText) findViewById(R.id.quantidade_id);

        /**
         * VERIFICA SE EXISTE ALGUM VALOR NA INTENT
         */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            proporcao.setText(getIntent().getExtras().getString("teste"));
        }

        consultaTabela = (Button) findViewById(R.id.button_table_id);
        consultaTabela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTabelaProporcao();
            }
        });

        calcular = (Button) findViewById(R.id.calcular_id);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qtdGasolina = quantidadeGasolina.getText().toString();
                String qtdProporcao = proporcao.getText().toString();
                double result = calculaOleo(qtdGasolina, qtdProporcao);
                alerta(result);
            }
        });
    }

    private void startTabelaProporcao(){
        Intent intent = new Intent(this, ListViewProporcoes.class);
        startActivity(intent);
    }

    private double calculaOleo(String qtdGasolina, String proporcao){
        String partes[] = proporcao.split(":");
        double te = (Double.parseDouble(qtdGasolina) / (Double.parseDouble(partes[0]) / Double.parseDouble(partes[1])));
        return te;
    }

    private void alerta(double result){
        dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Informação");
        dialog.setMessage("Quantidade de Óleo: " + result);
        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Confirmado", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.create();
        dialog.show();
    }

}
