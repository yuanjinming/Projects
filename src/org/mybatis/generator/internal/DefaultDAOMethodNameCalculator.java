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

import org.mybatis.generator.api.DAOMethodNameCalculator;
import org.mybatis.generator.api.IntrospectedTable;

/**
 * @author Jeff Butler
 * 
 */
public class DefaultDAOMethodNameCalculator implements DAOMethodNameCalculator {

    /**
     * 
     */
    public DefaultDAOMethodNameCalculator() {
        super();
    }

    public String getGetMethodName(IntrospectedTable introspectedTable){
    	return "get";
    }
    public String getDeleteMethodName(IntrospectedTable introspectedTable){
    	return "delete";
    }
    public String getDeleteByMethodName(IntrospectedTable introspectedTable){
    	return "deleteBy";
    }
    public String getUpdateMethodName(IntrospectedTable introspectedTable){
    	return "update";
    }
    public String getUpdateByMethodName(IntrospectedTable introspectedTable){
    	return "updateBy";
    }
    public String getAddMethodName(IntrospectedTable introspectedTable){
    	return "add";
    }
    public String getGetListMethodName(IntrospectedTable introspectedTable){
    	return "getList";
    }
    public String getCountMethodName(IntrospectedTable introspectedTable){
    	return "count";
    }
    public String getRunSqlMethodName(IntrospectedTable introspectedTable){
    	return "runSql";
    }
}
