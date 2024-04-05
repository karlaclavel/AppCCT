package org.app.appctc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class FragmentoTemporizador extends Fragment {

    private TextView text_temporizador;
    private TextView time_temporizador;
    private TextView button_star;

    private TextView button_reset;

    private CountDownTimer countDownTimer;

    public View onCreateView(LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_fragmento_temporizador, container, false);

        text_temporizador = view.findViewById(R.id.text_temporizador);
        time_temporizador = view.findViewById(R.id.time_temporizador);
        button_star = view.findViewById(R.id.star_button_temporizador);
        button_reset = view.findViewById(R.id.reset_button_temporizador);

        button_reset.setEnabled(false);

        button_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarTemporizador();
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarTemporizador();
            }
        });

        return view;
    }

    private void reiniciarTemporizador() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        // Habilitar la edición del tiempo en el EditText
        time_temporizador.setEnabled(true);

        // Limpiar el texto del TextView del temporizador
        text_temporizador.setText("00:00");

        // Habilitar el botón de inicio y deshabilitar el botón de reinicio
        button_star.setEnabled(true);
        button_reset.setEnabled(false);
    }

    private void iniciarTemporizador() {
        // Obtener el valor ingresado por el usuario en el EditText
        String tiempoString = time_temporizador.getText().toString();

        if (tiempoString.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingresa un valor de tiempo válido", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Convertir el valor a entero
            int minutosSeleccionados = Integer.parseInt(tiempoString);

            // Convertir minutos a milisegundos
            long duracionMilisegundos = minutosSeleccionados * 60 * 1000;
            time_temporizador.setEnabled(false);
            button_star.setEnabled(false);
            button_reset.setEnabled(true);

            countDownTimer = new CountDownTimer(duracionMilisegundos, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long segundosRestantes = millisUntilFinished / 1000;
                    long minutos = segundosRestantes / 60;
                    long segundos = segundosRestantes % 60;
                    String tiempoRestante = String.format(Locale.getDefault(), "%02d:%02d", minutos, segundos);
                    text_temporizador.setText(tiempoRestante);
                }

                @Override
                public void onFinish() {
                    text_temporizador.setText("00:00");
                }
            };

            countDownTimer.start();
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Error al convertir el tiempo ingresado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("FragmewntoTemporizador", "Se llamo el Fragmento temporizador");

    }

}