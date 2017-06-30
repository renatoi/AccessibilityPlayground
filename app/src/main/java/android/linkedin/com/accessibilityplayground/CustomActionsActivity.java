package android.linkedin.com.accessibilityplayground;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;

public class CustomActionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_actions);

        MyLinearLayout myLinearLayout = (MyLinearLayout) findViewById(R.id.my_linear_layout);
        myLinearLayout.setOnClickListener(view -> Log.wtf("A11Y", "LINEAR LAYOUT CLICKED!"));

        for (int i = 0; i < myLinearLayout.getChildCount(); i++) {
            View v = myLinearLayout.getChildAt(i);
            if (v instanceof Button) {
                v.setOnClickListener(view -> Log.wtf("A11Y", "BUTTON CLICKED!"));
            }
        }

        ViewCompat.setAccessibilityDelegate(myLinearLayout, new AccessibilityDelegateCompat() {

            @Override
            public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
                host.sendAccessibilityEvent(AccessibilityEventCompat.TYPE_VIEW_ACCESSIBILITY_FOCUSED);
                return false;
            }

            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);

                info.setClassName(Button.class.getName());
            }

            @Override
            public boolean performAccessibilityAction(View host, int action, Bundle args) {
                return super.performAccessibilityAction(host, action, args);
            }
        });
    }
}
