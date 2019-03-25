/*
 * Copyright 2019. the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tairanchina.csp.dew.core.doc;


import com.tairanchina.csp.dew.Dew;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@ConditionalOnMissingClass("org.springframework.cloud.client.discovery.DiscoveryClient")
public class DocLocalAutoConfiguration {

    @Value("${server.ssl.key-store:}")
    private String localSSLKeyStore;
    @Value("${server.context-path:}")
    private String localContextPath;

    @Bean
    public DocController docController() {
        return new DocController(() ->
                new ArrayList<String>() {{
                    add((localSSLKeyStore == null || localSSLKeyStore.isEmpty() ? "http" : "https") + "://localhost:" + Dew.Info.webPort + localContextPath + "/v2/api-docs");
                }}
        );
    }

}