package com.tsarenko.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Daniil",
                        url = "Tg: @AleisderTheOne"
                ),
                description = "OpenAPI documentation for test application",
                title = "OpenAPI specification",
                version = "1.0"
        )
)
public final class OpenApiConfig {
}
