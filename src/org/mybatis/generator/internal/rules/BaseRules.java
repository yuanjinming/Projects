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
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * This class centralizes all the rules related to code generation - including
 * the methods and objects to create, and certain attributes related to those
 * objects.
 * 
 * @author Jeff Butler
 */
public abstract class BaseRules implements Rules {

    /** The table configuration. */
    protected TableConfiguration tableConfiguration;
    
    /** The introspected table. */
    protected IntrospectedTable introspectedTable;
    
    /** The is model only. */
    protected final boolean isModelOnly;

    /**
     * Instantiates a new base rules.
     *
     * @param introspectedTable
     *            the introspected table
     */
    public BaseRules(IntrospectedTable introspectedTable) {
        super();
        this.introspectedTable = introspectedTable;
        this.tableConfiguration = introspectedTable.getTableConfiguration();
        String modelOnly = tableConfiguration.getProperty(PropertyRegistry.TABLE_MODEL_ONLY);
        isModelOnly = StringUtility.isTrue(modelOnly);
    }


    /**
     * Calculates the class that contains all fields. This class is used as the
     * insert statement parameter, as well as the returned value from the select
     * by primary key method. The actual class depends on how the domain model
     * is generated.
     * 
     * @return the type of the class that holds all fields
     */
    public FullyQualifiedJavaType calculateAllFieldsClass() {

        String answer;

        if (generateBaseRecordClass()) {
            answer = introspectedTable.getBaseRecordType();
        } else {
            answer = introspectedTable.getPrimaryKeyType();
        }

        return new FullyQualifiedJavaType(answer);
    }


    /**
     * Implements the rule for generating the result map without BLOBs. If
     * either select method is allowed, then generate the result map.
     * 
     * @return true if the result map should be generated
     */
    public boolean generateBaseResultMap() {
        if (isModelOnly) {
            return true;
        }
        
        boolean rc = tableConfiguration.isSelectByExampleStatementEnabled()
                || tableConfiguration.isSelectByPrimaryKeyStatementEnabled();

        return rc;
    }



    /* (non-Javadoc)
     * @see org.mybatis.generator.internal.rules.Rules#getIntrospectedTable()
     */
    public IntrospectedTable getIntrospectedTable() {
        return introspectedTable;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.internal.rules.Rules#generateBaseColumnList()
     */
    public boolean generateBaseColumnList() {
        if (isModelOnly) {
            return false;
        }
        
        return true;
    }


    /* (non-Javadoc)
     * @see org.mybatis.generator.internal.rules.Rules#generateJavaClient()
     */
    public boolean generateJavaClient() {
        return !isModelOnly;
    }


	@Override
	public boolean generateGet() {
		return !isModelOnly;
	}


	@Override
	public boolean generateDelete() {
		return !isModelOnly;
	}


	@Override
	public boolean generateDeleteBy() {
		return !isModelOnly;
	}


	@Override
	public boolean generateAdd() {
		return !isModelOnly;
	}


	@Override
	public boolean generateUpdate() {
		return !isModelOnly;
	}


	@Override
	public boolean generateUpdateBy() {
		return !isModelOnly;
	}


	@Override
	public boolean generateGetList() {
		return !isModelOnly;
	}


	@Override
	public boolean generateCount() {
		return !isModelOnly;
	}


	@Override
	public boolean generateRunSql() {
		return !isModelOnly;
	}


	@Override
	public boolean generateBaseRecordClass() {
		return !isModelOnly;
	}
}
