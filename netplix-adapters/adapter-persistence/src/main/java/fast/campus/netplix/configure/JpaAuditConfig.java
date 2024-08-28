package fast.campus.netplix.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(
        auditorAwareRef = "requestedByAuditorAware",
        dateTimeProviderRef = "requestedByAuditorAware"
)
@Configuration
public class JpaAuditConfig {
}
