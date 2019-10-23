package ch.beerpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.beerpro.presentation.MainActivity;

public class activity_error extends AppCompatActivity {

    @BindView(R.id.toolbar_error)
    Toolbar toolbar_error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar_error);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.beer_glass_icon);
    }

    @OnClick(R.id.error_btn)
    public void returnHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
