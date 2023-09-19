package ua.in.asilichenko.sharedtextreceiver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "Share Text Receiver";

  private TextView logTextView;

  private void log(String text) {
    Log.d(TAG, text);
    logTextView.append(text + "\n\n");
  }

  private void receiveText() {
    final Intent intent = getIntent();
    final String action = intent.getAction();
    if (Intent.ACTION_SEND.equals(action)) {
      final String stringExtra = intent.getStringExtra(Intent.EXTRA_TEXT);
      if (null != stringExtra) {
        log("Received text: " + stringExtra);
        intent.removeExtra(Intent.EXTRA_TEXT);
      } else {
        log("Received text is empty.");
      }
    } else {
      log("Intent Action: " + action);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    logTextView = findViewById(R.id.logTextView);
    log("=================== onCreate ===================");
    receiveText();
  }

  @Override
  protected void onPause() {
    super.onPause();
    log("onPause");
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    log("onNewIntent");
    setIntent(intent);
    receiveText();
  }

  @Override
  protected void onResume() {
    super.onResume();
    log("onResume");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    log("onDestroy");
  }
}