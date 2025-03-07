package model;

import java.util.ArrayList;
import java.util.List;

public class CarEventManager {
    private final List<ICarModelListener> listeners = new ArrayList<>();

    public void addListener(ICarModelListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ICarModelListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners() {
        for (ICarModelListener listener : listeners) {
            listener.onCarModelUpdated();
        }
    }
}
