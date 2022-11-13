package pl.wilkowski.firealarm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FireAlarmUseCaseTest {

    private FireAlarmUseCase fireAlarmUseCase;
    private Alarm alarm;

    @BeforeEach
    void beforeEach() {
        alarm = mock(Alarm.class);
        fireAlarmUseCase = new FireAlarmUseCase(alarm);
    }

    @Test
    void youCanInvokeTheUseCase() {
        fireAlarmUseCase.invoke();
    }

    @Test
    void useCaseWillStartAnAlert() {
        fireAlarmUseCase.invoke();
        verify(alarm, only()).start();
    }
}
