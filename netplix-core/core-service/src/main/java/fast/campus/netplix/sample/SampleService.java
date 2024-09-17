package fast.campus.netplix.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService implements SampleUseCase {

    private final SamplePort samplePort;

    @Override
    public void sample() {
        samplePort.sample();
    }
}
