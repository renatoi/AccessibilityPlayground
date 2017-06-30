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
import android.widget.TextView;

public class ToggleButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_button);

        // simple click listener
        Button myButton = (Button) findViewById(R.id.my_button);
        myButton.setOnClickListener(view -> Log.wtf("A11Y", "LINEAR LAYOUT CLICKED!"));

        TextView myTextView = (TextView) findViewById(R.id.my_text_view);
        myTextView.setVisibility(View.GONE);

        ViewCompat.setAccessibilityDelegate(myButton, new AccessibilityDelegateCompat() {
            private boolean isExpanded = false;
            private boolean shouldAnnounceToggleState = false;

            @Override
            public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
                return super.onRequestSendAccessibilityEvent(host, child, event);
            }

            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);

                if (isExpanded) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE);
                } else {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND);
                }

                // force focus to announce new state
                if (shouldAnnounceToggleState) {
                    shouldAnnounceToggleState = false;
                    host.sendAccessibilityEvent(AccessibilityEventCompat.TYPE_VIEW_ACCESSIBILITY_FOCUSED);
                }
            }

            @Override
            public boolean performAccessibilityAction(View host, int action, Bundle args) {
                if (action == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND.getId()) {
                    isExpanded = true;
                    shouldAnnounceToggleState = true;
                } else if (action == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE.getId()) {
                    isExpanded = false;
                    shouldAnnounceToggleState = true;
                } else if (action == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.getId()) {
                    isExpanded = !isExpanded;
                    shouldAnnounceToggleState = true;
                }
                myTextView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                return super.performAccessibilityAction(host, action, args);
            }
        });
    }
}
