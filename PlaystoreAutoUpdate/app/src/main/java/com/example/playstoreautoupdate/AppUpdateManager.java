package com.example.playstoreautoupdate;

import androidx.appcompat.app.AppCompatActivity;
import eu.dkaratzas.android.inapp.update.Constants;
import eu.dkaratzas.android.inapp.update.InAppUpdateManager;
import eu.dkaratzas.android.inapp.update.InAppUpdateStatus;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class AppUpdateManager extends AppCompatActivity implements InAppUpdateManager.InAppUpdateHandler {

    InAppUpdateManager inAppUpdateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_update_manager);

        inAppUpdateManager = InAppUpdateManager.Builder(this,101)
                .resumeUpdates(true)
                .mode(Constants.UpdateMode.FLEXIBLE)
                .snackBarMessage("An update has just been downloaded")
                .snackBarAction("RESTART")
                .handler(this);

        inAppUpdateManager.checkForAppUpdate();
    }

    @Override
    public void onInAppUpdateError(int code, Throwable error) {

    }

    @Override
    public void onInAppUpdateStatus(InAppUpdateStatus status) {

        //if the update downloaded, ask your confirmation and complete the update

        if (status.isDownloaded()){

            View rootview = getWindow().getDecorView().findViewById(R.id.content);

            Snackbar snackbar = Snackbar.make(rootview,"An update has just been download",
                    Snackbar.LENGTH_INDEFINITE);

            snackbar.setAction("Update", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    inAppUpdateManager.checkForAppUpdate();
                }
            });

            snackbar.show();
        }
    }
}
