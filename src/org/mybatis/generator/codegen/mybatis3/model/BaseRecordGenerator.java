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
package org.mybatis.generator.codegen.mybatis3.model;

import static org.mybatis.generator.internal.util.JavaBeansUtil.getJavaBeansField;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getJavaBeansGetter;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getJavaBeansSetter;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class BaseRecordGenerator extends AbstractJavaGenerator {

    public BaseRecordGenerator() {
        super();
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString(
                "Progress.8", table.toString())); //$NON-NLS-1$
        Plugin plugins = context.getPlugins();
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getBaseRecordType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        String mapper = introspectedTable.getMyBatis3JavaMapperType();
        topLevelClass.addImportedType("org.mybatis.dao.BaseEntity");
        topLevelClass.addImportedType(mapper);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        Field pk = getJavaBeansField(introspectedTable.getPrimaryKeyColumn(), context, introspectedTable);
        topLevelClass.setSuperClass(new FullyQualifiedJavaType("BaseEntity<"+pk.getType()+", "+mapper.substring(mapper.lastIndexOf('.') + 1)+">"));
        commentGenerator.addJavaFileComment(topLevelClass);

        FullyQualifiedJavaType superClass = getSuperClass();
        if (superClass != null) {
            topLevelClass.setSuperClass(superClass);
            topLevelClass.addImportedType(superClass);
        }
        commentGenerator.addModelClassComment(topLevelClass, introspectedTable);

        List<IntrospectedColumn> introspectedColumns = getColumnsInThisClass();

        if (introspectedTable.isConstructorBased()) {
            addParameterizedConstructor(topLevelClass);
            
            if (!introspectedTable.isImmutable()) {
                addDefaultConstructor(topLevelClass);
            }
        }
        
        InnerClass dbClass = new InnerClass("DB");
        dbClass.setStatic(true);
        dbClass.setVisibility(JavaVisibility.PUBLIC);
        topLevelClass.addInnerClass(dbClass);
        
        Method pkMethod = new Method("getPrimaryKey");
        pkMethod.setReturnType(pk.getType());
        pkMethod.setVisibility(JavaVisibility.PUBLIC);
        pkMethod.addBodyLine("return " + pk.getName() + ";");
        pkMethod.addAnnotation("@Override");
        topLevelClass.addMethod(pkMethod);
        Method tableNameMethod = new Method("tableName");
        tableNameMethod.setReturnType(new FullyQualifiedJavaType("String"));
        tableNameMethod.setVisibility(JavaVisibility.PUBLIC);
        tableNameMethod.addBodyLine("return DB.tableName;");
        tableNameMethod.addAnnotation("@Override");
        topLevelClass.addMethod(tableNameMethod);
        topLevelClass.addField(pk);
        topLevelClass.addImportedType(pk.getType());
        Method idGetMethod = getJavaBeansGetter(introspectedTable.getPrimaryKeyColumn(), context, introspectedTable);
        topLevelClass.addMethod(idGetMethod);
        if (!introspectedTable.isImmutable()){
        	Method idSetMethod = getJavaBeansSetter(introspectedTable.getPrimaryKeyColumn(), context, introspectedTable);
            topLevelClass.addMethod(idSetMethod);
        }
        
        Field pkf = new Field();
        pkf.setVisibility(JavaVisibility.PUBLIC);
        pkf.setType(new FullyQualifiedJavaType("String"));
        pkf.setStatic(true);
        pkf.setFinal(true);
        pkf.setName("primaryKey = \""+ introspectedTable.getPrimaryKeyColumn().getActualColumnName() + "\"");
        dbClass.addField(pkf);
        Field tbn = new Field();
        tbn.setVisibility(JavaVisibility.PUBLIC);
        tbn.setType(new FullyQualifiedJavaType("String"));
        tbn.setStatic(true);
        tbn.setFinal(true);
        tbn.setName("tableName = \""+ introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() + "\"");
        dbClass.addField(tbn);
        Field pkid = new Field();
        pkid.setVisibility(JavaVisibility.PUBLIC);
        pkid.setType(new FullyQualifiedJavaType("String"));
        pkid.setStatic(true);
        pkid.setFinal(true);
        pkid.setName(pk.getName() + " = \""+ introspectedTable.getPrimaryKeyColumn().getActualColumnName() + "\"");
        commentGenerator.addFieldComment(pkid, introspectedTable, introspectedTable.getPrimaryKeyColumn());
        dbClass.addField(pkid);
        
        Method setDefaultValues = new Method("setDefaultValues");
        setDefaultValues.setVisibility(JavaVisibility.PUBLIC);
        setDefaultValues.addAnnotation("@Deprecated");
        setDefaultValues.addAnnotation("@Override");
        topLevelClass.addMethod(setDefaultValues);
        
        
        String rootClass = getRootClass();
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            if (RootClassInfo.getInstance(rootClass, warnings)
                    .containsProperty(introspectedColumn)) {
                continue;
            }

            Field field = getJavaBeansField(introspectedColumn, context, introspectedTable);
            if (plugins.modelFieldGenerated(field, topLevelClass,
                    introspectedColumn, introspectedTable,
                    Plugin.ModelClassType.BASE_RECORD)) {
                topLevelClass.addField(field);
                topLevelClass.addImportedType(field.getType());
                
                if(null!=introspectedColumn.getDefaultValue() && !introspectedColumn.getDefaultValue().equals("null")){
                	setDefaultValues.addBodyLine("if (null == this."+field.getName()+") {");
                    if(field.getType().getShortName().equals("String")){
                    	setDefaultValues.addBodyLine("this."+field.getName()+" = \""+introspectedColumn.getDefaultValue()+"\";");
                    } else if(field.getType().getShortName().equals("Date")) {
                    	if(introspectedColumn.getDefaultValue()!=null && !"".equals(introspectedColumn.getDefaultValue()) ){
                    		if(introspectedColumn.getDefaultValue().startsWith("CURRENT")){
                    			setDefaultValues.addBodyLine("this."+field.getName()+" = new Date();");
                    		} else {
                        		DateFormat df;
                        		Date d = null;
                    			if(introspectedColumn.getDefaultValue().length()<=5){
                            		df = new SimpleDateFormat("yyyy-MM-dd");
                    			}else{
                            		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    			}
                        		try {
                        			d = df.parse(introspectedColumn.getDefaultValue());
                        			setDefaultValues.addBodyLine("this."+field.getName()+" = new Date("+d.getTime()+"l);");
                        		} catch (ParseException e) {
                        			e.printStackTrace();
                        		}
                    		}
                    	}
                    } else if(field.getType().getShortName().equals("Long")) {
                    	setDefaultValues.addBodyLine("this."+field.getName()+" = "+introspectedColumn.getDefaultValue()+"l;");
                    } else if(field.getType().getShortName().equals("Short")) {
                    	setDefaultValues.addBodyLine("this."+field.getName()+" = (short)"+introspectedColumn.getDefaultValue()+";");
                    } else if(field.getType().getShortName().equals("Float")) {
                    	setDefaultValues.addBodyLine("this."+field.getName()+" = "+introspectedColumn.getDefaultValue()+"f;");
                    } else if(field.getType().getShortName().equals("BigDecimal")) {
                    	setDefaultValues.addBodyLine("this."+field.getName()+" = BigDecimal.valueOf("+introspectedColumn.getDefaultValue()+");");
                    } else if(field.getType().getShortName().equals("Integer")) {
                    	setDefaultValues.addBodyLine("this."+field.getName()+" = "+introspectedColumn.getDefaultValue()+";");
                    }
                    setDefaultValues.addBodyLine("}");
                }
                
                
                Field dbField = new Field();
                dbField.setVisibility(JavaVisibility.PUBLIC);
                dbField.setType(new FullyQualifiedJavaType("String"));
                dbField.setStatic(true);
                dbField.setFinal(true);
                dbField.setName(field.getName() + " = \""+ introspectedColumn.getActualColumnName() + "\"");

                commentGenerator.addFieldComment(dbField, introspectedTable, introspectedColumn);
                dbClass.addField(dbField);
            }

            Method method = getJavaBeansGetter(introspectedColumn, context, introspectedTable);
            if (plugins.modelGetterMethodGenerated(method, topLevelClass,
                    introspectedColumn, introspectedTable,
                    Plugin.ModelClassType.BASE_RECORD)) {
                topLevelClass.addMethod(method);
            }

            if (!introspectedTable.isImmutable()) {
                method = getJavaBeansSetter(introspectedColumn, context, introspectedTable);
                if (plugins.modelSetterMethodGenerated(method, topLevelClass,
                        introspectedColumn, introspectedTable,
                        Plugin.ModelClassType.BASE_RECORD)) {
                    topLevelClass.addMethod(method);
                }
            }
        }

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (context.getPlugins().modelBaseRecordClassGenerated(
                topLevelClass, introspectedTable)) {
            answer.add(topLevelClass);
        }
        return answer;
    }

    private FullyQualifiedJavaType getSuperClass() {
        FullyQualifiedJavaType superClass;
        String rootClass = getRootClass();
        if (rootClass != null) {
            superClass = new FullyQualifiedJavaType(rootClass);
        } else {
            superClass = null;
        }

        return superClass;
    }

    private void addParameterizedConstructor(TopLevelClass topLevelClass) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.setName(topLevelClass.getType().getShortName());
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        List<IntrospectedColumn> constructorColumns =
            introspectedTable.getAllColumns();
            
        for (IntrospectedColumn introspectedColumn : constructorColumns) {
            method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(),
                    introspectedColumn.getJavaProperty()));
            topLevelClass.addImportedType(introspectedColumn.getFullyQualifiedJavaType());
        }
        
        StringBuilder sb = new StringBuilder();

        List<IntrospectedColumn> introspectedColumns = getColumnsInThisClass();
        
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            sb.setLength(0);
            sb.append("this."); //$NON-NLS-1$
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" = "); //$NON-NLS-1$
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(';');
            method.addBodyLine(sb.toString());
        }

        topLevelClass.addMethod(method);
    }
    
    private List<IntrospectedColumn> getColumnsInThisClass() {
        List<IntrospectedColumn> introspectedColumns;
        introspectedColumns = introspectedTable.getBaseColumns();
        
        return introspectedColumns;
    }
}
