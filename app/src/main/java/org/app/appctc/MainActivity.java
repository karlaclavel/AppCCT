package org.app.appctc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private void loadFragment(Fragment fragment, String title) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        TextView textView = findViewById(R.id.titulo_fragmento);
        textView.setText(title);
    }

    private DrawerLayout menu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*** MENU OPCIONES ***/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        //Proporciona una animacion de hamburguesa. Actúa como un listener para abrir y cerrar el drawer.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, menu , toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        menu.addDrawerListener(toggle);
        toggle.syncState();

        /*** FRAGMENTOS ***/

        // Establecer el listener para los items del menú
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.contador) {
                    loadFragment(new FragmentoContador(), "CONTADOR");
                } else if (id == R.id.cronometro) {
                    loadFragment(new FragmentoCronometro(), "CRONÓMETRO");
                } else if (id == R.id.temporizador) {
                    loadFragment(new FragmentoTemporizador(), "TEMPORIZADOR");
                }

                menu.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        loadFragment(new FragmentoContador(), "CONTADOR");

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.contador) {
            // Realiza la acción para la primera opción
            Toast.makeText(this, "contador inicializado", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.cronometro) {
            // Realiza la acción para la segunda opción
            // Por ejemplo, mostrar un Toast o abrir otro fragmento
            Toast.makeText(this, "cronómetro inicializado", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.temporizador) {
            // Realiza la acción para la segunda opción
            // Por ejemplo, mostrar un Toast o abrir otro fragmento
            Toast.makeText(this, "temporizados inicializado", Toast.LENGTH_SHORT).show();
        }

        menu.closeDrawer(GravityCompat.START);
        return true;
    }
}