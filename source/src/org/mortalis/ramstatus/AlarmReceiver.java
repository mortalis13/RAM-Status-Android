package org.mortalis.ramstatus;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    int ramMB = getMem(context);
    int icon = getIcon(ramMB);
    showNotification(context, icon, ramMB);
  }
  

  int getMem(Context context){
    MemoryInfo mi = new MemoryInfo();
    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

    activityManager.getMemoryInfo(mi);
    int avail = (int) (mi.availMem / 1048576L);
    
    return avail;
  }
  
  
  public int getIcon(int ramMB){
    int icon = 0;
    int category = ramMB/100;
    
    switch(category){
      case 0:
        icon = R.drawable.icon_digits_00;
        break;
      case 1:
        icon = R.drawable.icon_digits_01;
        break;
      case 2:
        icon = R.drawable.icon_digits_02;
        break;
      case 3:
        icon = R.drawable.icon_digits_03;
        break;
      case 4:
        icon = R.drawable.icon_digits_04;
        break;
      case 5:
        icon = R.drawable.icon_digits_05;
        break;
      case 6: 
        icon = R.drawable.icon_digits_06;
        break; 
      case 7: 
        icon = R.drawable.icon_digits_07; 
        break;
      case 8:
        icon = R.drawable.icon_digits_08;
        break; 
      case 9:
        icon = R.drawable.icon_digits_09;
        break;
    }
    
    return icon;
  }
  
  public void showNotification(Context context, int icon, int ramMB){
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
    
//    ramMB = 100;
    
    mBuilder.setSmallIcon(icon, ramMB);
    mBuilder.setOngoing(true);
    
    mBuilder.setContentTitle("Free RAM");
    mBuilder.setContentText(ramMB + " MB Free");  
    
    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    mNotificationManager.notify(MainActivity.notificationId, mBuilder.build());
  }
  
}
