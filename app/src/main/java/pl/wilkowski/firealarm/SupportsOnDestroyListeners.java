package pl.wilkowski.firealarm;

interface SupportsOnDestroyListeners {
    void addOnDestroyListener(OnDestroyListener listener);
    void removeOnDestroyListener(OnDestroyListener listener);
}
