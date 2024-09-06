package com.example.eventlistener3;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final TextView output_text = findViewById(R.id.output_text);
        final EditText radius_input = findViewById(R.id.radius);
        radius_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                    output_text.setText(String.format("%s %s", output_text.getText(), radius_input.getText()));
                    radius_input.setText("");
                }
                return false;
            }
        });

        final Button calculate_btn = findViewById(R.id.calculate_btn);
        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double r_square = 0.0;
                final DecimalFormat df = new DecimalFormat("0.00");
                if(radius_input.getText().toString().isEmpty()){
                    r_square = 0.0;
                } else {
                    r_square = Float.parseFloat((radius_input.getText().toString()));
                }
                output_text.setText(df.format(Math.PI*r_square));
            }
        });

        final Button clear_btn = findViewById(R.id.clear_btn);
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output_text.setText("");
            }
        });
    }
}