package org.divestos.supportdivestos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnWebsite).setOnClickListener(this);
        findViewById(R.id.btnNews).setOnClickListener(this);
        findViewById(R.id.btnChat).setOnClickListener(this);
        findViewById(R.id.btnRecommendedApps).setOnClickListener(this);
        findViewById(R.id.btnCredits).setOnClickListener(this);
        findViewById(R.id.btnDonate).setOnClickListener(this);
        findViewById(R.id.btnSourceCode).setOnClickListener(this);
        findViewById(R.id.btnPrivacyPolicy).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWebsite: {
                openWebsite("https://divestos.org");
                break;
            }
            case R.id.btnNews: {
                openWebsite("https://divestos.org/index.php?page=news");
                break;
            }
            case R.id.btnDonate: {
                openWebsite("https://divested.dev/donate");
                break;
            }
            case R.id.btnChat: {
                openXMPP("xmpp:divestos-mobile@conference.konvers.me?join");
                break;
            }
            case R.id.btnRecommendedApps: {
                openWebsite("https://divestos.org/index.php?page=recommended_apps");
                break;
            }
            case R.id.btnCredits: {
                openWebsite("https://divestos.org/index.php?page=about#credits");
                break;
            }
            case R.id.btnSourceCode: {
                openWebsite("https://gitlab.com/divested-mobile");
                break;
            }
            case R.id.btnPrivacyPolicy: {
                openWebsite("https://divestos.org/index.php?page=privacy_policy");
                break;
            }
        }
    }

    public void openWebsite(String website) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
        startActivity(browserIntent);
    }

    public void openXMPP(String target) {
        Intent xmppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(target));
        if (xmppIntent.resolveActivity(getPackageManager()) == null) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.xmppRequiredTitle))
                    .setMessage(getString(R.string.xmppRequiredDescription))
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> openWebsite("https://f-droid.org/en/packages/eu.siacs.conversations/"))
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        } else {
            startActivity(xmppIntent);
        }
    }

}