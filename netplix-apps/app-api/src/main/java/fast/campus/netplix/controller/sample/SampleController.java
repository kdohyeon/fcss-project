package fast.campus.netplix.controller.sample;

import fast.campus.netplix.controller.NetplixApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sample")
public class SampleController {
    @GetMapping("/test")
    public NetplixApiResponse<String> sample() {
        return NetplixApiResponse.ok("correct auth");
    }
}
