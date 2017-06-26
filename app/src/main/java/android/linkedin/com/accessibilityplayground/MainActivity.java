package android.linkedin.com.accessibilityplayground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // simple click listener
        MyLinearLayout myLinearLayout = (MyLinearLayout) findViewById(R.id.my_linear_layout);
        myLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.wtf("A11Y", "LINEAR LAYOUT CLICKED!");
            }
        });
    }
}
