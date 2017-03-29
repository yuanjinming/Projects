/**
 *    Copyright 2006-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.api;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;

/**
 * This class includes no-operation methods for almost every method in the
 * Plugin interface. Clients may extend this class to implement some or all of
 * the methods in a plugin.
 * <p>
 * This adapter does not implement the <tt>validate</tt> method - all plugins
 * must perform validation.
 * 
 * @author Jeff Butler
 * 
 */
public abstract class PluginAdapter implements Plugin {
    protected Context context;
    protected Properties properties;

    public PluginAdapter() {
        properties = new Properties();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties.putAll(properties);
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        return null;
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(
            IntrospectedTable introspectedTable) {
        return null;
    }

    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles() {
        return null;
    }

    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(
            IntrospectedTable introspectedTable) {
        return null;
    }

    public boolean clientGetMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientGetMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientDeleteMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientDeleteMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientDeleteByMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientDeleteByMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientAddMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientAddMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientUpdateMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientUpdateMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientUpdateByMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientUpdateByMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientGetListMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientGetListMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientCountMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientCountMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientRunSqlMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean clientRunSqlMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean modelFieldGenerated(Field field,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable,
            Plugin.ModelClassType modelClassType) {
        return true;
    }

    public boolean modelGetterMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable,
            Plugin.ModelClassType modelClassType) {
        return true;
    }

    public boolean modelSetterMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable,
            Plugin.ModelClassType modelClassType) {
        return true;
    }

    public boolean sqlMapResultMapElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        return true;
    }

    public boolean sqlMapDeleteElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        return true;
    }
    public boolean sqlMapDeleteByElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        return true;
    }
    public boolean sqlMapAddElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        return true;
    }
    public boolean sqlMapUpdateElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        return true;
    }
    public boolean sqlMapUpdateByElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        return true;
    }
    public boolean sqlMapGetListElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        return true;
    }
    public boolean sqlMapCountElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        return true;
    }
    public boolean sqlMapRunSqlElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        return true;
    }

    public void initialized(IntrospectedTable introspectedTable) {
    }

    public boolean sqlMapBaseColumnListElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        return true;
    }

	public boolean sqlMapGetElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return true;
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		return true;
	}

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		return true;
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		return true;
	}
}
