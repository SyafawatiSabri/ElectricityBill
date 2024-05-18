package com.example.assignmentelectric;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText unitsEditText, rebateEditText;
    private Button calcButton, clearButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize views
        unitsEditText = findViewById(R.id.NumberElectric);
        rebateEditText = findViewById(R.id.Rebate);
        calcButton = findViewById(R.id.btnCalc);
        clearButton = findViewById(R.id.btnClear);
        resultTextView = findViewById(R.id.FinalCostOutput);

        // Calculate button click listener
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        // Clear button click listener
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item clicks
        int selected = item.getItemId();
        if (selected == R.id.action_about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void calculateBill() {
        String unitsString = unitsEditText.getText().toString();
        String rebateString = rebateEditText.getText().toString();

        // Validate input
        if (unitsString.isEmpty() || rebateString.isEmpty()) {
            resultTextView.setText("Please enter units and rebate percentage.");
            return;
        }

        double units = Double.parseDouble(unitsString);
        double rebate = Double.parseDouble(rebateString);

        double bill = 0.0;

        if (units <= 200) {
            bill = units * 0.218;
        } else if (units <= 300) {
            double unitsFirstBlock = 200;
            double unitsNextBlock = units - unitsFirstBlock;
            bill = (unitsFirstBlock * 0.218) + (unitsNextBlock * 0.334);
        } else if (units <= 600) {
            double unitsFirstBlock = 200;
            double unitsSecondBlock = 100;
            double unitsNextBlock = units - unitsFirstBlock - unitsSecondBlock;
            bill = (unitsFirstBlock * 0.218) + (unitsSecondBlock * 0.334) + (unitsNextBlock * 0.516);
        } else {
            double unitsFirstBlock = 200;
            double unitsSecondBlock = 100;
            double unitsThirdBlock = 300;
            double unitsNextBlock = units - unitsFirstBlock - unitsSecondBlock - unitsThirdBlock;
            bill = (unitsFirstBlock * 0.218) + (unitsSecondBlock * 0.334) + (unitsThirdBlock * 0.516) + (unitsNextBlock * 0.546);
        }

        bill *= (1 - rebate / 100);

        resultTextView.setText("Electric Bill: RM" + String.format("%.2f", bill));
    }

    private void clearInput() {
        unitsEditText.setText("");
        rebateEditText.setText("");
        resultTextView.setText("");
    }
}
