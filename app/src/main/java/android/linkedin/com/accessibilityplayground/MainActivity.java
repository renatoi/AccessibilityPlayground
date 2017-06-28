package android.linkedin.com.accessibilityplayground;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;

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

        ViewCompat.setAccessibilityDelegate(myLinearLayout, new AccessibilityDelegateCompat() {
            private boolean isExpanded = false;
            private boolean shouldAnnounceToggleState = false;

            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);

                info.setClassName(Button.class.getName());

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
                if (action == AccessibilityActionCompat.ACTION_EXPAND.getId()) {
                    isExpanded = true;
                    shouldAnnounceToggleState = true;
                } else if (action == AccessibilityActionCompat.ACTION_COLLAPSE.getId()) {
                    isExpanded = false;
                    shouldAnnounceToggleState = true;
                } else if (action == AccessibilityActionCompat.ACTION_CLICK.getId()) {
                    isExpanded = !isExpanded;
                    shouldAnnounceToggleState = true;
                }
                return super.performAccessibilityAction(host, action, args);
            }
        });
    }
}
