package justonemore.test.example.com.justonemore_test;

//import android.support.v7.app.ActionBarActivity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

    Button btnStartProgress;
    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();

    private long fileSize = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();

        makePushNotification();
    }

    public void makePushNotification(){
    /*    NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("My Notification")
                .setContentText("You're almost there! Just one more push!!!");

        // Create an explicit intent for an Activity in the app
        Intent resultIntent = new Intent(this, ResultActivity.class);

        // The stack builder object will contain an artificial back stack for the started Activity.
        // This ensures that navigating backward from the Activity leads out of your application to the Home Screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent(but not the Intent itself)
        stackBuilder.addParentStack(ResultActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //mID allows you to update the notification later on.
        //mNotificationManager.notify(mId, mBuilder.build());
*/
    }

    public void addListenerOnButton(){
        btnStartProgress = (Button) findViewById(R.id.btnStartProgress);
        btnStartProgress.setOnClickListener(
                new OnClickListener() {

                    public void onClick(View v) {

                        // prepare for a progress bar dialog
                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("File downloading ...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(101);
                        progressBar.show();

                        //reset progress bar status
                        progressBarStatus = 0;

                        //reset filesize
                        fileSize = 0;

                        new Thread(new Runnable(){
                            public void run() {
                                while (progressBarStatus < 100) {

                                    // process some tasks
                                    progressBarStatus = doSomeTasks();

                                    // computer is too fast, sleep 1 second
                                    try{
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e){
                                        e.printStackTrace();
                                    }

                                    //Update the progress bar
                                    progressBarHandler.post(new Runnable() {
                                        public void run() {
                                            progressBar.setProgress(progressBarStatus);
                                        }
                                    });
                                }

                                //ok, file is downloaded
                                if(progressBarStatus >= 100) {

                                    // sleep 2 seconds, so that you can see the 100%
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e){
                                        e.printStackTrace();
                                    }

                                    //close the progress bar dialog
                                    progressBar.dismiss();
                                }
                            }
                        }).start();
                    }
                });
    }
    // file download simulator...
    public int doSomeTasks(){

        while (fileSize <= 1000000) {
            fileSize++;
            if(fileSize == 100000) {
                return 10;
            } else if (fileSize == 200000) {
                return 20;
            } else if (fileSize == 300000) {
                return 30;
            }
            // add your own
        }
        return 100;
    }
}

/*public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/