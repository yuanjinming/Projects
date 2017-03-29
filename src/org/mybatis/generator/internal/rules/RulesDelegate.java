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
package org.mybatis.generator.internal.rules;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

/**
 * This class can be used by plugins to easily implement a custom rules
 * implementation. Plugins should respect the rules implementation calculated by
 * the generator, as well as implementations from other plugins. In general if
 * something is disabled by the default rules, or is disabled by some other
 * plugin, it should not be re-enabled. Therefore, the following pattern of use
 * is recommended:
 * 
 * <pre>
 * public class MyPlugin extends PluginAdapter {
 *   &#64;Override
 *   public void initialized(IntrospectedTable introspectedTable) {
 *     MyRules myRules = new MyRules(introspectedTable.getRules());
 *     introspectedTable.setRules(myRules);
 *   }
 * }
 * 
 * public class MyRules extends RulesDelegate (
 *   public MyRules(Rules rules) {
 *     super(rules);
 *   }
 *   
 *   &#64;Override
 *   public boolean generateInsert() {
 *     boolean rc = super.generateInsert();
 *     if (rc) {
 *       // Other plugins, and the default rules, enable generation
 *       // of the insert method.  We can decide to disable it here
 *       // if needed.
 *     }
 *     
 *     return rc;
 *   }
 * </pre>
 * 
 * 
 * @author Jeff Butler
 * 
 */
public class RulesDelegate implements Rules {
    protected Rules rules;

    public RulesDelegate(Rules rules) {
        this.rules = rules;
    }

    public FullyQualifiedJavaType calculateAllFieldsClass() {
        return rules.calculateAllFieldsClass();
    }

    public boolean generateBaseRecordClass() {
        return rules.generateBaseRecordClass();
    }

    public boolean generateBaseResultMap() {
        return rules.generateBaseResultMap();
    }

    public IntrospectedTable getIntrospectedTable() {
        return rules.getIntrospectedTable();
    }

    public boolean generateJavaClient() {
        return rules.generateJavaClient();
    }

	@Override
	public boolean generateGet() {
		return rules.generateGet();
	}

	@Override
	public boolean generateDelete() {
		return rules.generateDelete();
	}

	@Override
	public boolean generateDeleteBy() {
		return rules.generateDeleteBy();
	}

	@Override
	public boolean generateAdd() {
		return rules.generateAdd();
	}

	@Override
	public boolean generateUpdate() {
		return rules.generateUpdate();
	}

	@Override
	public boolean generateUpdateBy() {
		return rules.generateUpdateBy();
	}

	@Override
	public boolean generateGetList() {
		return rules.generateGetList();
	}

	@Override
	public boolean generateCount() {
		return rules.generateCount();
	}

	@Override
	public boolean generateRunSql() {
		return rules.generateRunSql();
	}

	@Override
	public boolean generateBaseColumnList() {
		return rules.generateBaseColumnList();
	}
}
