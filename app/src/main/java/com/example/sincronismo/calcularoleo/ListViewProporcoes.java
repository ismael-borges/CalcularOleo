package com.example.sincronismo.calcularoleo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewProporcoes extends Activity {

    private ListView listaItens;
    private String[] itens = {
            "40:1", "20:1", "25:1", "22:1"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_proporcoes);

        listaItens = (ListView) findViewById(R.id.lista_prop_id);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            getApplicationContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            itens
        );

        listaItens.setAdapter(adapter);
        listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codigoPosicao = position;
                String string = listaItens.getItemAtPosition(codigoPosicao).toString();
                Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
                startProporcao(string);
            }
        });
    }

    private void startProporcao(String string){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("teste", string);
        startActivity(intent);
    }
}
