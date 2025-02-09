package app.textile.oltyems.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import app.textile.oltyems.R;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);


        Button showDialogButton = findViewById(R.id.showDialogButton);

        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the custom popup dialog above the button
                showCustomDialog(v);
            }
        });
    }

    private void showCustomDialog(View anchorView) {
        // Create a view for the popup window (this will be our dialog-like view)
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.bottom_sheet_layout, null);

        // Set up the PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        // Make the popup window focusable and dismissible when touched outside
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        // Get the button's location on the screen to position the popup above it
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);

        // Show the PopupWindow above the button (adjust for the size of the button)
        popupWindow.showAtLocation(anchorView, Gravity.TOP,
                location[0], location[1] - popupView.getHeight() - 375);
    }
}