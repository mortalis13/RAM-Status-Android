package org.mortalis.ramstatus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class MainActivity extends Activity {
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    createNotificationChannel();
    
    Button bStartService = (Button) findViewById(R.id.bStartService);
    bStartService.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startService();
      }
    });
    
    Button bStopService = (Button) findViewById(R.id.bStopService);
    bStopService.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        stopService();
      }
    });
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    switch(id){
      case R.id.exit:
        finish();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
  
  
  // -----------------------
  private void createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      String id = Vars.NOTIFICATIONS_CHANNEL_ID;
      CharSequence name = getString(R.string.notification_channel_name);
      String description = getString(R.string.notification_channel_description);
      int importance = NotificationManager.IMPORTANCE_DEFAULT;

      NotificationChannel channel = new NotificationChannel(id, name, importance);
      channel.setDescription(description);
      NotificationManager notificationManager = getSystemService(NotificationManager.class);
      notificationManager.createNotificationChannel(channel);
    }
  }
  
  private void startService() {
    Intent intent = new Intent(this, MemService.class);
    startService(intent);
  }

  private void stopService() {
    Intent intent = new Intent(this, MemService.class);
    stopService(intent);
  }

}
