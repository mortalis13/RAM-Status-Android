package org.mortalis.ramstatus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
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
  
  
  private void startService() {
    Intent intent = new Intent(this, MemService.class);
    startService(intent);
  }

  private void stopService() {
    Intent intent = new Intent(this, MemService.class);
    stopService(intent);
  }

}
