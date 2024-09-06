package fast.campus.netplix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(
        auditorAwareRef = "requestedByAuditorAware",
        dateTimeProviderRef = "requestedAtAuditorAware"
)
@Configuration
public class JpaAuditConfig {
}
