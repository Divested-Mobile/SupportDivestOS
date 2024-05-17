/*
Copyright (c) 2021-2023 Divested Computing Group

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package org.divestos.supportdivestos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            setTheme(android.R.style.Theme_DeviceDefault_DayNight);
        }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnWebsite).setOnClickListener(this);
        findViewById(R.id.btnNews).setOnClickListener(this);
        findViewById(R.id.btnChatXmpp).setOnClickListener(this);
        findViewById(R.id.btnRecommendedApps).setOnClickListener(this);
        findViewById(R.id.btnCredits).setOnClickListener(this);
        findViewById(R.id.btnDonate).setOnClickListener(this);
        findViewById(R.id.btnSourceCode).setOnClickListener(this);
        findViewById(R.id.btnPrivacyPolicy).setOnClickListener(this);
        findViewById(R.id.btnWebsiteCloudflare).setOnClickListener(this);
        findViewById(R.id.btnWebsiteOnionPrimary).setOnClickListener(this);
        findViewById(R.id.btnWebsiteOnionSecondary).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWebsite: {
                openBrowser("https://divestos.org");
                break;
            }
            case R.id.btnNews: {
                openBrowser("https://divestos.org/pages/news");
                break;
            }
            case R.id.btnDonate: {
                openBrowser("https://divested.dev/donate");
                break;
            }
            case R.id.btnChatXmpp: {
                openXMPP("xmpp:divestos-mobile@conference.konvers.me?join");
                break;
            }
            case R.id.btnRecommendedApps: {
                openBrowser("https://divestos.org/pages/recommended_apps");
                break;
            }
            case R.id.btnCredits: {
                openBrowser("https://divestos.org/pages/about#credits");
                break;
            }
            case R.id.btnSourceCode: {
                openBrowser("https://gitlab.com/divested-mobile");
                break;
            }
            case R.id.btnPrivacyPolicy: {
                openBrowser("https://divestos.org/pages/privacy_policy");
                break;
            }
            case R.id.btnWebsiteCloudflare: {
                openBrowser("https://divestos.eeyo.re");
                break;
            }
            case R.id.btnWebsiteOnionPrimary: {
                openBrowser("http://divestoseb5nncsydt7zzf5hrfg44md4bxqjs5ifcv4t7gt7u6ohjyyd.onion");
                break;
            }
            case R.id.btnWebsiteOnionSecondary: {
                openBrowser("http://2ceyag7ppvhliszes2v25n5lmpwhzqrc7sv72apqka6hwggfi42y2uid.onion");
                break;
            }
        }
    }

    public void openBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public void openXMPP(String target) {
        Intent xmppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(target));
        if (xmppIntent.resolveActivity(getPackageManager()) == null) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.xmppRequiredTitle))
                    .setMessage(getString(R.string.xmppRequiredDescription))
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> openBrowser("https://f-droid.org/en/packages/eu.siacs.conversations/"))
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        } else {
            startActivity(xmppIntent);
        }
    }

}
