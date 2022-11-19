package com.example.tacolodinput;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText tAmount, tPercent, tTime;
    String sAmount, sPercent, sTime, outputMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCompute = (Button) findViewById(R.id.btnCompute);

        btnCompute.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Calculating....", Toast.LENGTH_SHORT).show();
        CalculatingResult();
    }

    public void CalculatingResult(){
        tAmount = (EditText) findViewById(R.id.tAmount);
        tPercent = (EditText) findViewById(R.id.tPercent);
        tTime = (EditText) findViewById(R.id.tTime);
        if(tAmount.getText().toString().isEmpty() || tPercent.getText().toString().isEmpty() || tTime.getText().toString().isEmpty()){
            sAmount = "0";
            sPercent = "0";
            sTime = "0";
        }else{
            sAmount = tAmount.getText().toString();
            sPercent = tPercent.getText().toString();
            sTime = tTime.getText().toString();
        }
        double rawAmount = Double.parseDouble(sAmount);
        double rawPercent = Double.parseDouble(sPercent);
        double rawTime = Double.parseDouble(sTime);

        rawPercent = ToDecimal(rawPercent);

        double result = rawAmount *  rawPercent * rawTime;
        // Casted result to int type, remove to show decimal value
        outputMessage = "The interest of " + sAmount + " php is " + result + " php.";

        // Create Bundle instance, this will allow transfer of data from Activity to DialogFragment
        Bundle args = new Bundle();
        args.putString("result", outputMessage);

        // Create a dialog instance
        DialogFragmentCustom dialogFragmentImp = new DialogFragmentCustom();
        // Pass on dialog argument(args), the result
        dialogFragmentImp.setArguments(args);
        // Display the Dialog
        dialogFragmentImp.show(getSupportFragmentManager(),"Display Result");
        // Reset EditTexts
        clearEditText();
    }
    public void clearEditText(){
        tAmount.getText().clear();
        tPercent.getText().clear();
        tTime.getText().clear();
        tAmount.requestFocus();
    }
    public double ToDecimal(double nbr){
        nbr = nbr/100;
        return nbr;
    }
}