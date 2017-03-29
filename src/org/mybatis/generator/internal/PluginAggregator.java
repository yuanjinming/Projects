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
package org.mybatis.generator.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;

/**
 * This class is for internal use only. It contains a list of plugins for the
 * current context and is used to aggregate plugins together. This class
 * implements the rule that if any plugin returns "false" from a method, then no
 * other plugin is called.
 * <p>
 * This class does not follow the normal plugin lifecycle and should not be
 * subclassed by clients.
 * 
 * @author Jeff Butler
 * 
 */
public final class PluginAggregator implements Plugin {
    private List<Plugin> plugins;

    public PluginAggregator() {
        plugins = new ArrayList<Plugin>();
    }

    public void addPlugin(Plugin plugin) {
        plugins.add(plugin);
    }

    public void setContext(Context context) {
        throw new UnsupportedOperationException();
    }

    public void setProperties(Properties properties) {
        throw new UnsupportedOperationException();
    }

    public boolean validate(List<String> warnings) {
        throw new UnsupportedOperationException();
    }

    public boolean modelBaseRecordClassGenerated(TopLevelClass tlc,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelBaseRecordClassGenerated(tlc, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(
            IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();
        for (Plugin plugin : plugins) {
            List<GeneratedJavaFile> temp = plugin
                    .contextGenerateAdditionalJavaFiles(introspectedTable);
            if (temp != null) {
                answer.addAll(temp);
            }
        }
        return answer;
    }

    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(
            IntrospectedTable introspectedTable) {
        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();
        for (Plugin plugin : plugins) {
            List<GeneratedXmlFile> temp = plugin
                    .contextGenerateAdditionalXmlFiles(introspectedTable);
            if (temp != null) {
                answer.addAll(temp);
            }
        }
        return answer;
    }
    
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapGenerated(sqlMap, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientGenerated(Interface interfaze,
            TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientGenerated(interfaze, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();
        for (Plugin plugin : plugins) {
            List<GeneratedJavaFile> temp = plugin
                    .contextGenerateAdditionalJavaFiles();
            if (temp != null) {
                answer.addAll(temp);
            }
        }
        return answer;
    }

    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles() {
        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();
        for (Plugin plugin : plugins) {
            List<GeneratedXmlFile> temp = plugin
                    .contextGenerateAdditionalXmlFiles();
            if (temp != null) {
                answer.addAll(temp);
            }
        }
        return answer;
    }

    public boolean sqlMapDocumentGenerated(Document document,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapDocumentGenerated(document, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelFieldGenerated(Field field,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable,
            Plugin.ModelClassType modelClassType) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelFieldGenerated(field, topLevelClass,
                    introspectedColumn, introspectedTable, modelClassType)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelGetterMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable,
            Plugin.ModelClassType modelClassType) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelGetterMethodGenerated(method, topLevelClass,
                    introspectedColumn, introspectedTable, modelClassType)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelSetterMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable,
            Plugin.ModelClassType modelClassType) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelSetterMethodGenerated(method, topLevelClass,
                    introspectedColumn, introspectedTable, modelClassType)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public void initialized(IntrospectedTable introspectedTable) {
        for (Plugin plugin : plugins) {
            plugin.initialized(introspectedTable);
        }
    }

    public boolean sqlMapBaseColumnListElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapBaseColumnListElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }


	@Override
	public boolean sqlMapGetElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapGetElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean sqlMapDeleteElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapDeleteElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean sqlMapDeleteByElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapDeleteByElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean sqlMapAddElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapAddElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean sqlMapUpdateElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean sqlMapRunSqlElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapRunSqlElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean sqlMapUpdateByElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean sqlMapGetListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapGetListElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean sqlMapCountElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapCountElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientAddMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientAddMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientDeleteMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientGetMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientGetMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientDeleteByMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientUpdateMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientUpdateByMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientGetListMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientGetListMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientCountMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientCountMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientRunSqlMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientRunSqlMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientAddMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientAddMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientDeleteMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientGetMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientGetMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientDeleteByMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientUpdateMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientUpdateByMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientGetListMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientGetListMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientCountMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientCountMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean clientRunSqlMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientRunSqlMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}

	@Override
	public boolean sqlMapResultMapElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapResultMapElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
	}
}
