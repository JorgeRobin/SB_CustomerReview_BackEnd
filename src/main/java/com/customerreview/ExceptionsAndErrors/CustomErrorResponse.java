package com.customerreview.ExceptionsAndErrors;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
 
import java.util.Map;

@Component
public class CustomErrorResponse extends DefaultErrorAttributes {
	
	@Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String, Object> getErrorAttributes(
                    RequestAttributes requestAttributes,
                    boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                errorAttributes.put("data", null);
                return errorAttributes;
            }

        };
    }
 
}
