package org.app.appctc;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentoContador extends Fragment {

    private int contador = 0;
    private TextView text_contador;
    private TextView click_contador;
    private TextView reset_contador;


    public View onCreateView(LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_fragmento_contador, container, false);

        text_contador = view.findViewById(R.id.text_contador);
        click_contador = view.findViewById(R.id.click_button_contador);
        reset_contador = view.findViewById(R.id.reset_button_contador);

        click_contador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementarContador();
            }
        });

        reset_contador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarContador();
            }
        });
        return view;
    }

    private void reiniciarContador() {
        contador = 0;
        text_contador.setText(Integer.toString(contador));

    }

    @Override
    public void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("FragmentoContador", "Se llamo el Fragmento contador");
    }

    private void incrementarContador() {
        contador++;
        text_contador.setText(Integer.toString(contador));
    }

}
