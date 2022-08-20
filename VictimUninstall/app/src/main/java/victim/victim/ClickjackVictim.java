package victim.victim;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ClickjackVictim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickjack_victim);
    }

    public void allow(View v){
        System.out.println("^^^^^^^^^^^^^^^\t POC Clickjack YES!!! \t^^^^^^^^^^^^^^");
        selfStop();
    }

    public void deny(View v){
        System.out.println("---------------\t POC No! \t---------------");
        selfStop();
    }

    private void selfStop(){
        finishAffinity();
        System.exit(0);
        finishAndRemoveTask();
    }

}