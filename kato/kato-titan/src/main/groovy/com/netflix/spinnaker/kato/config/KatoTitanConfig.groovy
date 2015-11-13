/*
 * Copyright 2015 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.kato.config
import com.netflix.spinnaker.clouddriver.titan.TitanClientProvider
import com.netflix.spinnaker.kato.titan.deploy.handlers.TitanDeployHandler
import groovy.util.logging.Slf4j
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn

import javax.annotation.PostConstruct

@Configuration
@ConditionalOnProperty('titan.enabled')
@ComponentScan('com.netflix.spinnaker.kato.titan')
@Slf4j
class KatoTitanConfig {

  @PostConstruct
  void init() {
    log.info("KatoTitanConfig is enabled")
  }

  @Bean
  @DependsOn('netflixTitanCredentials')
  TitanDeployHandler titanDeployHandler(TitanClientProvider titanClientProvider) {
    new TitanDeployHandler(titanClientProvider)
  }
}
