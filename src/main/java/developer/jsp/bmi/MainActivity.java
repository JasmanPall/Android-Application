package developer.jsp.bmi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //Define layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Making the references to the fields used
        final EditText e1 = (EditText) findViewById(R.id.et1);
        final EditText e2 = (EditText) findViewById(R.id.et2);
        final EditText e3 = (EditText) findViewById(R.id.et3);
        final TextView tv4 = (TextView) findViewById(R.id.tv4);
        final TextView tv6 = (TextView) findViewById(R.id.tv6);
        final TextView tv7 = (TextView) findViewById(R.id.tv7);

        findViewById(R.id.ib1).setOnClickListener(new View.OnClickListener() {

            // Logic for validation, input can't be empty
            @Override
            public void onClick(View v) {

                String str1 = e1.getText().toString();
                String str2 = e2.getText().toString();
                String str3 = e3.getText().toString();

                if(TextUtils.isEmpty(str3)){
                    e3.setError(" Age helps me find the % of body fat ");

                    return;
                }

                if(TextUtils.isEmpty(str1)){
                    e1.setError("You forgot to enter your Weight");

                    return;
                }

                if(TextUtils.isEmpty(str2)){
                    e2.setError(" Height is important to me for calculation ");

                    return;
                }



                float weight = Float.parseFloat(str1);
                float height = Float.parseFloat(str2);
                float age = Float.parseFloat(str3);

//Calculate BMI value
                float bmiValue = calculateBMI(weight, height);

// Round-off the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);
                float ff = bmiValue;
                String decimalbmiValue = String.format("%.02f", ff);


                tv4.setTextColor(Color.parseColor("#00FF00"));
                tv4.setText(String.valueOf(" BMI - " + decimalbmiValue + " ( " + bmiInterpretation + " )"));

//ideal weight calculation codes
                Float bmiResult = calculateweight(height);

                float f = bmiResult;
                String test = String.format("%.02f", f);
                float bodyfat;

                bodyfat = (float) (((bmiValue * 1.2) + (age * 0.23)) - 16.2 );

                tv6.setText(String.valueOf(" Ideal Weight should be " + test + " pounds "));

                String decimalbodyfat = String.format("%.02f", bodyfat);

                tv7.setTextColor(Color.parseColor("#FF0000"));
                tv7.setText(String.valueOf(" Your Body Fat is " + decimalbodyfat + " % "));


            }
        });

    }



    //IDEAL weight
    private float calculateweight ( float height) {

        float idealweight = (float) ((21.997) * ((height * 0.0254) * (height * 0.0254)) / (0.4535));
        return (float) (idealweight);
    }

    //Calculate BMI
    private float calculateBMI (float weight, float height) {

        //return (float) (weight  / (height * height) );
        return (float) ((weight * 0.45359237) / ((height * 0.0254) * (height * 0.0254) ));
    }


    // Interpret what BMI means
    private String interpretBMI(float bmiValue) {

        if (bmiValue < 15)
        {
            return "Very Severely underweight";
        }
        else if (bmiValue < 17)
        {
            return "Severely underweight";
        }
        else if (bmiValue < 18.5)
        {
            return "Underweight";
        }

        else if (bmiValue < 25)
        {
            return "Normal";
        }
        else if (bmiValue < 30)
        {
            return "Overweight";
        }
        else if (bmiValue < 35)
        {
            return "Obese Class I";
        }
        else if (bmiValue < 40)
        {
            return "Obese Class II";
        }
        else
        {
            return "Obese Class III";
        }
    }
}