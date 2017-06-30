package android.linkedin.com.accessibilityplayground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toggleButton = (Button) findViewById(R.id.go_to_toggle_button_activity);
        toggleButton.setOnClickListener((View v) -> {
            startActivity(new Intent(this, ToggleButtonActivity.class));
        });

        Button customActionsButton = (Button) findViewById(R.id.go_to_custom_actions_activity);
        customActionsButton.setOnClickListener((View v) -> {
            startActivity(new Intent(this, CustomActionsActivity.class));
        });
    }
}
