package org.mortalis.ramstatus;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    int ramMB = getMem(context);
    int iconId = getIcon(ramMB);
    int iconLevel = ramMB;
    if (iconId == 0) {
      iconId = R.drawable.icon_digits_00;
      iconLevel = 0;
    }
    Fun.showNotification(context, iconId, iconLevel, ramMB);
  }
  

  private int getMem(Context context) {
    MemoryInfo mi = new MemoryInfo();
    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

    activityManager.getMemoryInfo(mi);
    int avail = (int) (mi.availMem / 1048576L);     // bytes to MB
    
    return avail;
  }
  
  public int getIcon(int ramMB) {
    int iconId = 0;
    int category = ramMB/100;
    
    switch(category){
      case 0:
        iconId = R.drawable.icon_digits_00;
        break;
      case 1:
        iconId = R.drawable.icon_digits_01;
        break;
      case 2:
        iconId = R.drawable.icon_digits_02;
        break;
      case 3:
        iconId = R.drawable.icon_digits_03;
        break;
      case 4:
        iconId = R.drawable.icon_digits_04;
        break;
      case 5:
        iconId = R.drawable.icon_digits_05;
        break;
      case 6: 
        iconId = R.drawable.icon_digits_06;
        break; 
      case 7: 
        iconId = R.drawable.icon_digits_07; 
        break;
      case 8:
        iconId = R.drawable.icon_digits_08;
        break; 
      case 9:
        iconId = R.drawable.icon_digits_09;
        break;
    }
    
    return iconId;
  }
  
}
