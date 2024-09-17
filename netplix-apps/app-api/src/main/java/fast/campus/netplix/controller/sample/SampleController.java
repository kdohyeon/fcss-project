package fast.campus.netplix.controller.sample;

import fast.campus.netplix.controller.NetplixApiResponse;
import fast.campus.netplix.sample.SampleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sample")
@RequiredArgsConstructor
public class SampleController {

    private final SampleUseCase sampleUseCase;

    @GetMapping("/test")
    public NetplixApiResponse<Void> sample() {
        sampleUseCase.sample();
        return NetplixApiResponse.ok(null);
    }
}
