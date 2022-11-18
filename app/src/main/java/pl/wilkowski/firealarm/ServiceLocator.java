package pl.wilkowski.firealarm;

import android.content.ContextWrapper;

import java.net.URL;

class ServiceLocator {
    private static ServiceLocator INSTANCE;
    private UpdateUserTokenUseCase updateUserTokenUseCase;
    private FireAlarmUseCase fireAlarmUseCase;

    private ServiceLocator(ContextWrapper contextWrapper, SupportsOnDestroyListeners supportsOnDestroyListeners) {
        try {
            updateUserTokenUseCase = new UpdateUserTokenUseCase(
                    new URL(BuildConfig.updateUserTokenEndpoint));
            fireAlarmUseCase = new FireAlarmUseCase(new AndroidAlarm(contextWrapper, supportsOnDestroyListeners));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void create(ContextWrapper contextWrapper, SupportsOnDestroyListeners supportsOnDestroyListeners) {
        INSTANCE = new ServiceLocator(contextWrapper, supportsOnDestroyListeners);
    }

    static void destroy() {
        INSTANCE = null;
    }

    static ServiceLocator getInstance() {
        return INSTANCE;
    }

    UpdateUserTokenUseCase getUpdateUserTokenUseCase() {
        return updateUserTokenUseCase;
    }

    FireAlarmUseCase getFireAlarmUseCase() {
        return fireAlarmUseCase;
    }
}
