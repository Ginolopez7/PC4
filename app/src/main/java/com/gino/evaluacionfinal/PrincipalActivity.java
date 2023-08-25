package com.gino.evaluacionfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.gino.evaluacionfinal.databinding.ActivityPrincipalBinding;
import com.gino.evaluacionfinal.fragments.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

public class PrincipalActivity extends AppCompatActivity {

    private ActivityPrincipalBinding binding;
    public static String EMAIL = "EMAIL";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        sharedPreferences = getSharedPreferences(LoginActivity.SESSION_PREFERENCE, MODE_PRIVATE);
        setContentView(binding.getRoot());
        binding.tbPc4.setTitle("Bienvenido Usuario");
        setSupportActionBar(binding.tbPc4);
        addHomeFragment();
    }

    //binding.btnGetStarted.setOnClickListener(v -> {
    //            //Toast.makeText(this, "Comenzar", Toast.LENGTH_SHORT).show();
    //            Intent intent = new Intent(this, LoginActivity.class);
    //            startActivity(intent);
    //            finish();
    //        });

    private void addHomeFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(binding.fcvMain.getId(), new HomeFragment()).commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favorite) {
            Snackbar.make(binding.getRoot(), "Favorite", Snackbar.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.logout) {
            {
                // Mostrar cuadro de diálogo de confirmación
                new AlertDialog.Builder(this)
                        .setTitle("Cerrar sesión")
                        .setMessage("¿Deseas cerrar la sesión?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sharedPreferences.edit().clear().apply();
                                Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // No hacer nada, simplemente cerrar el cuadro de diálogo
                            }
                        })
                        .show();

                return true;
            }
        }
        return false;

    }
}
