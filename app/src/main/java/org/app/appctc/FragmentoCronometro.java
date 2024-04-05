package org.app.appctc;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Locale;

/* FRAGMENTO CRONÓMETRO: Cronometro que tiene pausa, inicio y reinicio */
public class FragmentoCronometro extends Fragment {

    private TextView text_cronometro;
    private TextView button_star;
    private TextView button_pause;
    private TextView button_reset;

    // Clase que implementa un cronometro
    private CountDownTimer countUpTimer;
    private boolean timerRunning;
    private long tiempoInicial;
    private long tiempoTranscurrido;

    public View onCreateView(LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Se infla el diseño del segmento desde XML
        View view = inflater.inflate(R.layout.activity_fragmento_cronometro, container, false);

        text_cronometro = view.findViewById(R.id.text_cronometro);
        button_star = view.findViewById(R.id.star_button_cronometro);
        button_pause = view.findViewById(R.id.pause_button_cronometro);
        button_reset = view.findViewById(R.id.reset_button_cronometro);

        button_pause.setEnabled(false);
        button_reset.setEnabled(false);

        button_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarCronometro();
            }

        });

        button_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausarCronometro();
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetearCronometro();
            }
        });

        return view;
    }


    private void iniciarCronometro() {
        tiempoInicial = System.currentTimeMillis() - tiempoTranscurrido;
        countUpTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {

            // En cada tick se actualiza el tiempo transcurrido
            @Override
            public void onTick(long millisUntilFinished) {
                tiempoTranscurrido = System.currentTimeMillis() - tiempoInicial;
                actualizarTiempo();
            }

            @Override
            public void onFinish() {
            }
        }.start();

        timerRunning = true;
        actualizarBotones();
    }

    private void pausarCronometro() {
        // Se cancela el cronometro
        countUpTimer.cancel();
        timerRunning = false;
        actualizarBotones();
    }

    private void resetearCronometro() {
        tiempoTranscurrido = 0;
        actualizarTiempo();
        actualizarBotones();
    }

    private void actualizarTiempo() {
        // Actualiza el tiempo trasncurrido en formado HH:MM:SS
        int hours = (int) (tiempoTranscurrido / 1000) / 3600;
        int minutes = (int) ((tiempoTranscurrido / 1000) % 3600) / 60;
        int seconds = (int) (tiempoTranscurrido / 1000) % 60;

        String timeElapsedFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        text_cronometro.setText(timeElapsedFormatted);
    }

    private void actualizarBotones() {
        if (timerRunning) {
            button_pause.setEnabled(true);
            button_star.setEnabled(false);
            button_reset.setEnabled(false);
        } else {
            button_pause.setEnabled(false);
            button_star.setEnabled(true);
            button_reset.setEnabled(true);
            if (tiempoTranscurrido > 0) {
                button_pause.setEnabled(false);
                button_star.setEnabled(true);
                button_reset.setEnabled(true);
            } else if (tiempoTranscurrido == 0) {
                button_pause.setEnabled(false);
                button_star.setEnabled(true);
                button_reset.setEnabled(false);
            }
        }
    }

    @Override
    public void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("FragmentoCronometro", "Se llamo el Fragmento cronometro");
    }
}