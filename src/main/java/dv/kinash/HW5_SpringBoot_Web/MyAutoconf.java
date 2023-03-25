package dv.kinash.HW5_SpringBoot_Web;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@ConditionalOnClass(MyController.class)
@ConditionalOnProperty(prefix="defaultParameterValues", name="available", havingValue="yes")
public class MyAutoconf {
    @Autowired
    private MyResolver myResolver;
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        List<HandlerMethodArgumentResolver> oldResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> resolvers =
                Stream.concat(Stream.of(myResolver), oldResolvers.stream())
                        .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
        requestMappingHandlerAdapter.setArgumentResolvers(resolvers);
    }

}

