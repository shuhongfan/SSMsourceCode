/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.servlet.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.w3c.dom.Element;

import java.util.Map;

/**
 * bean的定义解析器，用于解析 <mvc:default-servlet-handler/>元素，并注册一个DefaultServletHttpRequestHandler
 *
 * {@link BeanDefinitionParser} that parses a {@code default-servlet-handler} element to
 * register a {@link DefaultServletHttpRequestHandler}.  Will also register a
 * {@link SimpleUrlHandlerMapping} for mapping resource requests, and a
 * {@link HttpRequestHandlerAdapter}.
 *
 * @author Jeremy Grelle
 * @author Rossen Stoyanchev
 * @since 3.0.4
 */
class DefaultServletHandlerBeanDefinitionParser implements BeanDefinitionParser {

	@Override
	@Nullable
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		Object source = parserContext.extractSource(element);

		String defaultServletName = element.getAttribute("default-servlet-name");

		//创建DefaultServletHttpRequestHandler类的bean定义
		RootBeanDefinition defaultServletHandlerDef = new RootBeanDefinition(DefaultServletHttpRequestHandler.class);
		defaultServletHandlerDef.setSource(source);
		defaultServletHandlerDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		if (StringUtils.hasText(defaultServletName)) {
			defaultServletHandlerDef.getPropertyValues().add("defaultServletName", defaultServletName);
		}

		//得到bean的名字，如果有多个会在后面加上#0,#1,#2......
		String defaultServletHandlerName = parserContext.getReaderContext().generateBeanName(defaultServletHandlerDef);

		//注册bean的定义，底层将bean的定义放入beanDefinitionMap的一个Map中
		parserContext.getRegistry().registerBeanDefinition(defaultServletHandlerName, defaultServletHandlerDef);
		//在该元素完成解析的时候做事件通知所用
		parserContext.registerComponent(new BeanComponentDefinition(defaultServletHandlerDef, defaultServletHandlerName));

		//url映射
		Map<String, String> urlMap = new ManagedMap<>();
		urlMap.put("/**", defaultServletHandlerName);

		//创建SimpleUrlHandlerMapping类的bean定义
		RootBeanDefinition handlerMappingDef = new RootBeanDefinition(SimpleUrlHandlerMapping.class);
		handlerMappingDef.setSource(source);
		handlerMappingDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		handlerMappingDef.getPropertyValues().add("urlMap", urlMap);

		String handlerMappingBeanName = parserContext.getReaderContext().generateBeanName(handlerMappingDef);

		//注册bean的定义，底层将bean的定义放入beanDefinitionMap的一个Map中
		parserContext.getRegistry().registerBeanDefinition(handlerMappingBeanName, handlerMappingDef);
		//在该元素完成解析的时候做事件通知所用
		parserContext.registerComponent(new BeanComponentDefinition(handlerMappingDef, handlerMappingBeanName));
		//注册一些默认组件，已确保组件注册齐全，功能服务正常
		// Ensure BeanNameUrlHandlerMapping (SPR-8289) and default HandlerAdapters are not "turned off"
		MvcNamespaceUtils.registerDefaultComponents(parserContext, source);

		return null;
	}
}