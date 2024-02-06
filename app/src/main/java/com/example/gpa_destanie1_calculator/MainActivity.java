package com.example.gpa_destanie1_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    EditText[] gradeInputs = new EditText[5];
    Button computeButton;
    TextView gpaResult;
    LinearLayout mainLayout;
    private Bundle outState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gradeInputs[0] = findViewById(R.id.course1Grade);
        gradeInputs[1] = findViewById(R.id.course2Grade);
        gradeInputs[2] = findViewById(R.id.course3Grade);
        gradeInputs[3] = findViewById(R.id.course4Grade);
        gradeInputs[4] = findViewById(R.id.course5Grade);

        computeButton = findViewById(R.id.computeButton);
        gpaResult = findViewById(R.id.gpaResult);
        mainLayout = findViewById(R.id.mainLayout);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (computeButton.getText().toString().equals("Clear Form")) {
                    // Clear all input fields
                    for (int i = 0; i < 5; i++) {
                        gradeInputs[i].setText("");
                    }

                    // Reset GPA result text and background color
                    gpaResult.setText("GPA: ");
                    mainLayout.setBackgroundColor(Color.WHITE);

                    // Change button text back to "Compute GPA"
                    computeButton.setText("Compute GPA");
                } else {
                    // Calculate GPA as before
                    double totalGradePoints = 0;
                    int totalCredits = 0;
                    boolean hasEmptyField = false; // Flag to check for empty fields

                    for (int i = 0; i < 5; i++) {
                        String gradeStr = gradeInputs[i].getText().toString().trim();

                        if (!gradeStr.isEmpty()) {
                            double gradeValue = Double.parseDouble(gradeStr);
                            if (gradeValue != -1) {
                                // Assuming each course is worth 3 credits
                                totalGradePoints += (gradeValue);
                                totalCredits += 3;
                            } else {
                                // Handle invalid input for the grade
                                gradeInputs[i].setError("Invalid Grade");
                            }
                        } else {
                            // Handle empty input
                            gradeInputs[i].setError("Enter a Grade");
                            hasEmptyField = true; // Set the flag
                        }
                    }

                    if (hasEmptyField) {
                        // Display a message if there are empty fields
                        // You can show a toast or any other suitable method
                        // For now, we'll just use setError for the button
                        computeButton.setError("Fill all fields");
                    } else {
                        if (totalCredits > 0) {
                            double gpa = totalGradePoints / 5;
                            gpaResult.setText("GPA: " +String.format("%.2f", gpa));



                            // Change background color based on GPA range
                            if (gpa <60) {
                                mainLayout.setBackgroundColor(Color.RED);
                            } else if (gpa >= 60 && gpa <= 79) {
                                mainLayout.setBackgroundColor(Color.YELLOW);
                            } else {
                                mainLayout.setBackgroundColor(Color.GREEN);
                            }

                            // Change button text to "Clear Form"
                            computeButton.setText("Clear Form");
                        }
                    }
                }
            }
        });

        for (int i = 0; i < 5; i++) {
            gradeInputs[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        computeButton.setText("Compute GPA");
                    }
                }
            });
        }
    }
}