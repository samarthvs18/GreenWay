package com.example.greenway;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HomePageActivity extends AppCompatActivity {

    private TextView welcomeText, modelResult;
    private ProgressBar progressBar;
    private Button refreshButton;
    private String userName; // Assuming this is passed from the previous activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize UI elements
        welcomeText = findViewById(R.id.welcomeText);
        modelResult = findViewById(R.id.modelResult);
        progressBar = findViewById(R.id.progressBar);
        refreshButton = findViewById(R.id.refreshButton);

        // Set welcome message (could be passed via intent from the login)
        userName = getIntent().getStringExtra("USER_NAME"); // This can be passed during login
        welcomeText.setText("Welcome, " + userName);

        // Load initial AI model results (or TensorFlow Lite model)
        loadAIResults();

        // Set up refresh button
        refreshButton.setOnClickListener(v -> {
            modelResult.setText("Loading...");
            progressBar.setVisibility(View.VISIBLE);
            loadAIResults(); // Re-run the model fetch
        });
    }

    private void loadAIResults() {
        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Simulate data fetching from Firebase ML Kit or TensorFlow Lite model
        // This could be replaced by an actual AI model inference or Firebase ML Kit request
        // For demonstration, we simulate AI model processing with a delay
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate network or AI processing delay

                // Simulated result
                final String aiResult = "AI Model Output: Success";

                // Update the UI with results
                runOnUiThread(() -> {
                    modelResult.setText(aiResult);
                    progressBar.setVisibility(View.GONE);
                });
            } catch (InterruptedException e) {
                runOnUiThread(() -> {
                    Toast.makeText(HomePageActivity.this, "Failed to load AI results.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                });
            }
        }).start();
    }
}
