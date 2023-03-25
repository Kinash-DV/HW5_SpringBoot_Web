package dv.kinash.HW5_SpringBoot_Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class MyResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private final Environment environment;
    public MyResolver(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(RequestParam.class) && parameter.getParameterType() == String.class){
            String name = parameter.getParameterName();
            String defaultValue = environment.getProperty("defaultParameterValues.parameter." + name);
            return defaultValue != null && defaultValue.length() > 0;
        }
        return false;
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String name = parameter.getParameterName();
        String[] values = webRequest.getParameterValues(name);
        if (values == null)
            return environment.getProperty("defaultParameterValues.parameter." + name);
        else
            return values[0];
    }
}
