package fast.campus.netplix.batch.reader;

import fast.campus.netplix.entity.subscription.UserSubscriptionEntity;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class TestItemReader implements ItemReader<UserSubscriptionEntity> {
    @Override
    public UserSubscriptionEntity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
