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
 * This interface centralizes all the rules related to code generation -
 * including the methods and objects to create, and certain attributes related
 * to those objects.
 * 
 * @author Jeff Butler
 */
public interface Rules {

    /**
     * Calculates the class that contains all fields. This class is used as the
     * insert statement parameter, as well as the returned value from the select
     * by primary key method. The actual class depends on how the domain model
     * is generated.
     * 
     * @return the type of the class that holds all fields
     */
    FullyQualifiedJavaType calculateAllFieldsClass();

    boolean generateGet();
    boolean generateDelete();
    boolean generateDeleteBy();
    boolean generateAdd();
    boolean generateUpdate();
    boolean generateUpdateBy();
    boolean generateGetList();
    boolean generateCount();
    boolean generateRunSql();


    /**
     * Implements the rule for generating a base record.
     * 
     * @return true if the class should be generated
     */
    boolean generateBaseRecordClass();
    
    /**
     * Implements the rule for generating a Java client.  This rule is
     * only active when a javaClientGenerator configuration has been
     * specified, but the table is designated as "modelOnly".  Do not
     * generate the client if the table is designated as modelOnly.
     * 
     * @return true if the Java client should be generated
     */
    boolean generateJavaClient();

    IntrospectedTable getIntrospectedTable();

	boolean generateBaseResultMap();

	boolean generateBaseColumnList();
    
    
    
}
