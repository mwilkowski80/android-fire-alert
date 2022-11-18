package pl.wilkowski.firealarm;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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
