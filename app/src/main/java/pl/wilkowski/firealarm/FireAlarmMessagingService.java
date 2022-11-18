package pl.wilkowski.firealarm;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;

import java.io.IOException;

public class FireAlarmMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FireAlarm";

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refreshed token: " + token);

        try {
            var updateUserTokenUseCase = ServiceLocator.getInstance().getUpdateUserTokenUseCase();
            updateUserTokenUseCase.invoke(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        Log.d(TAG, "Message received: " + message.getData());
        ServiceLocator.getInstance().getFireAlarmUseCase().invoke();
    }
}
