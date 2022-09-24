/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.beans.factory;

/**
 * 在实际的开发中,我们却经常要用到Spring容器本身的功能或资源,
 * 所以Spring容器中的Bean此时就要意识到Spring容器的存在才能调用Spring所提供的资源。
 * 我们通过Spring提供的一系列Aware接口来实现具体的功能。
 *
 * Spring 容器在初始化bean时会主动检测当前bean是否实现了Aware接口，
 * 如果实现了则回调其set方法将相应的参数设置给该bean，此时该bean就从Spring容器中获得了相应的资源
 * Aware：翻译即为 有意识的，感知的；
 *
 * Spring Aware的目的是为了让Bean获得Spring容器的服务，
 * 当Bean继承自ApplicationContextAware的时候就可以得到Spring容器的所有服务。
 *
 * 一个标记的超级接口，指示一个bean是符合条件的，能被作为一个特定的框架对象，通过回调方法，接受spring容器的通知；
 * A marker superinterface indicating that a bean is eligible to be notified by the
 * Spring container of a particular framework object through a callback-style method.
 * The actual method signature is determined by individual subinterfaces but should
 * typically consist of just one void-returning method that accepts a single argument.
 *
 * <p>Note that merely implementing {@link Aware} provides no default functionality.
 * Rather, processing must be done explicitly, for example in a
 * {@link org.springframework.beans.factory.config.BeanPostProcessor}.
 * Refer to {@link org.springframework.context.support.ApplicationContextAwareProcessor}
 * for an example of processing specific {@code *Aware} interface callbacks.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.1
 */
public interface Aware {

}