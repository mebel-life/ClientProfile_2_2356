package org.client.config;

import com.github.sonus21.rqueue.config.RqueueConfig;
import com.github.sonus21.rqueue.config.RqueueListenerBaseConfig;
import com.github.sonus21.rqueue.core.*;
import com.github.sonus21.rqueue.core.impl.ReactiveRqueueMessageEnqueuerImpl;
import com.github.sonus21.rqueue.core.impl.RqueueEndpointManagerImpl;
import com.github.sonus21.rqueue.core.impl.RqueueMessageEnqueuerImpl;
import com.github.sonus21.rqueue.core.impl.RqueueMessageManagerImpl;
import com.github.sonus21.rqueue.listener.RqueueMessageHandler;
import com.github.sonus21.rqueue.listener.RqueueMessageListenerContainer;
import com.github.sonus21.rqueue.utils.condition.ReactiveEnabled;
import org.springframework.context.annotation.*;



@Configuration
@ComponentScan({"com.github.sonus21.rqueue.web", "com.github.sonus21.rqueue.dao"})
public class RqueueListenerConfig extends RqueueListenerBaseConfig {

    @Bean
    public RqueueMessageHandler rqueueMessageHandler() {
        return simpleRqueueListenerContainerFactory.getRqueueMessageHandler(
                getMessageConverterProvider());
    }

    @Bean
    @DependsOn("rqueueConfig")
    public RqueueMessageListenerContainer rqueueMessageListenerContainer(
            RqueueMessageHandler rqueueMessageHandler) {
        simpleRqueueListenerContainerFactory.setRqueueMessageHandler(rqueueMessageHandler);
        return simpleRqueueListenerContainerFactory.createMessageListenerContainer();
    }

    @Bean
    public RqueueMessageTemplate rqueueMessageTemplate(RqueueConfig rqueueConfig) {
        return getMessageTemplate(rqueueConfig);
    }

    @Bean
    public RqueueMessageManager rqueueMessageManager(
            RqueueMessageHandler rqueueMessageHandler, RqueueMessageTemplate rqueueMessageTemplate) {
        return new RqueueMessageManagerImpl(
                rqueueMessageTemplate,
                rqueueMessageHandler.getMessageConverter(),
                simpleRqueueListenerContainerFactory.getMessageHeaders());
    }

    @Bean
    public RqueueEndpointManager rqueueEndpointManager(
            RqueueMessageHandler rqueueMessageHandler, RqueueMessageTemplate rqueueMessageTemplate) {
        return new RqueueEndpointManagerImpl(
                rqueueMessageTemplate,
                rqueueMessageHandler.getMessageConverter(),
                simpleRqueueListenerContainerFactory.getMessageHeaders());
    }

    @Bean
    public RqueueMessageEnqueuer rqueueMessageEnqueuer(
            RqueueMessageHandler rqueueMessageHandler, RqueueMessageTemplate rqueueMessageTemplate) {
        return new RqueueMessageEnqueuerImpl(
                rqueueMessageTemplate,
                rqueueMessageHandler.getMessageConverter(),
                simpleRqueueListenerContainerFactory.getMessageHeaders());
    }


    @Bean
    @Conditional(ReactiveEnabled.class)
    public ReactiveRqueueMessageEnqueuer reactiveRqueueMessageEnqueuer(
            RqueueMessageHandler rqueueMessageHandler, RqueueMessageTemplate rqueueMessageTemplate) {
        return new ReactiveRqueueMessageEnqueuerImpl(
                rqueueMessageTemplate,
                rqueueMessageHandler.getMessageConverter(),
                simpleRqueueListenerContainerFactory.getMessageHeaders());
    }
}

