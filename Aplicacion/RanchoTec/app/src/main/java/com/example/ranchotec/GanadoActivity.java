package com.example.ranchotec;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GanadoActivity extends AppCompatActivity {
    private EditText idEditText, especieEditText, razaEditText, nombreEditText, fechaNacimientoEditText,
            sexoEditText, colorEditText, historialVacunacionEditText;
    private Button guardarButton, actualizarButton, eliminarButton, buscarButton;
    private DatabaseReference mDatabase;
    private String ganadoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganado);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        idEditText = findViewById(R.id.idEditText);
        especieEditText = findViewById(R.id.especieEditText);
        razaEditText = findViewById(R.id.razaEditText);
        nombreEditText = findViewById(R.id.nombreEditText);
        fechaNacimientoEditText = findViewById(R.id.fechaNacimientoEditText);
        sexoEditText = findViewById(R.id.sexoEditText);
        colorEditText = findViewById(R.id.colorEditText);
        historialVacunacionEditText = findViewById(R.id.historialVacunacionEditText);
        guardarButton = findViewById(R.id.guardarButton);
        actualizarButton = findViewById(R.id.actualizarButton);
        eliminarButton = findViewById(R.id.eliminarButton);
        buscarButton = findViewById(R.id.buscarButton);

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarRegistro();
            }
        });

        actualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRegistro();
            }
        });

        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarRegistro();
            }
        });

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarRegistro();
            }
        });
    }

    private void guardarRegistro() {
        String id = idEditText.getText().toString();
        String especie = especieEditText.getText().toString();
        String raza = razaEditText.getText().toString();
        String nombre = nombreEditText.getText().toString();
        String fechaNacimiento = fechaNacimientoEditText.getText().toString();
        String sexo = sexoEditText.getText().toString();
        String color = colorEditText.getText().toString();
        String historialVacunacion = historialVacunacionEditText.getText().toString();

        Ganado ganado = new Ganado(id, especie, raza, nombre, fechaNacimiento, sexo, color, historialVacunacion);

        String key = mDatabase.child("ganado").push().getKey();
        mDatabase.child("ganado").child(key).setValue(ganado);

        idEditText.setText("");
        especieEditText.setText("");
        razaEditText.setText("");
        nombreEditText.setText("");
        fechaNacimientoEditText.setText("");
        sexoEditText.setText("");
        colorEditText.setText("");
        historialVacunacionEditText.setText("");

        Toast.makeText(this, "Registro guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void actualizarRegistro() {
        String especie = especieEditText.getText().toString();
        String raza = razaEditText.getText().toString();
        String nombre = nombreEditText.getText().toString();
        String fechaNacimiento = fechaNacimientoEditText.getText().toString();
        String sexo = sexoEditText.getText().toString();
        String color = colorEditText.getText().toString();
        String historialVacunacion = historialVacunacionEditText.getText().toString();

        mDatabase.child("ganado").child(ganadoId).child("especie").setValue(especie);
        mDatabase.child("ganado").child(ganadoId).child("raza").setValue(raza);
        mDatabase.child("ganado").child(ganadoId).child("nombre").setValue(nombre);
        mDatabase.child("ganado").child(ganadoId).child("fechaNacimiento").setValue(fechaNacimiento);
        mDatabase.child("ganado").child(ganadoId).child("sexo").setValue(sexo);
        mDatabase.child("ganado").child(ganadoId).child("color").setValue(color);
        mDatabase.child("ganado").child(ganadoId).child("historialVacunacion").setValue(historialVacunacion);

        Toast.makeText(this, "Registro actualizado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void eliminarRegistro() {
        mDatabase.child("ganado").child(ganadoId).removeValue();

        idEditText.setText("");
        especieEditText.setText("");
        razaEditText.setText("");
        nombreEditText.setText("");
        fechaNacimientoEditText.setText("");
        sexoEditText.setText("");
        colorEditText.setText("");
        historialVacunacionEditText.setText("");

        Toast.makeText(this, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void buscarRegistro() {
        String id = idEditText.getText().toString();

        mDatabase.child("ganado").orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Ganado ganado = dataSnapshot.getValue(Ganado.class);
                        if (ganado != null) {
                            ganadoId = dataSnapshot.getKey();
                            especieEditText.setText(ganado.getEspecie());
                            razaEditText.setText(ganado.getRaza());
                            nombreEditText.setText(ganado.getNombre());
                            fechaNacimientoEditText.setText(ganado.getFechaNacimiento());
                            sexoEditText.setText(ganado.getSexo());
                            colorEditText.setText(ganado.getColor());
                            historialVacunacionEditText.setText(ganado.getHistorialVacunacion());
                        }
                    }
                } else {
                    Toast.makeText(GanadoActivity.this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GanadoActivity.this, "Error al buscar registro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
