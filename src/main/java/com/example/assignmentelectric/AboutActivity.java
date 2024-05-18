package com.example.assignmentelectric;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set toolbar title
        getSupportActionBar().setTitle("About");

        // Handle GitHub link
        TextView githubTextView = findViewById(R.id.github);
        githubTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://github.com/SyafawatiSabri");
            }
        });

        // Handle Social Media link
        TextView socialMediaTextView = findViewById(R.id.Socmed);
        socialMediaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.youtube.com/channel/UCvvFQgVnrZXFBjF6m3gmyFg");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close the AboutActivity and go back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
