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
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import static org.mybatis.generator.internal.util.JavaBeansUtil.getJavaBeansField;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class GetElementGenerator extends
        AbstractXmlElementGenerator {

    public GetElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", "get")); //$NON-NLS-1$
        answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
                introspectedTable.getBaseResultMapId()));
        
        Field field = getJavaBeansField(introspectedTable.getPrimaryKeyColumn(), context, introspectedTable);

        answer.addAttribute(new Attribute("parameterType",
        		field.getType().getFullyQualifiedName()));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("select "); //$NON-NLS-1$

        if (stringHasValue(introspectedTable
                .getSelectByPrimaryKeyQueryId())) {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByPrimaryKeyQueryId());
            sb.append("' as QUERYID,"); //$NON-NLS-1$
        }
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getBaseColumnListElement());


        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        boolean and = false;
        
        sb.setLength(0);
        if (and) {
            sb.append("  and "); //$NON-NLS-1$
        } else {
            sb.append("where "); //$NON-NLS-1$
            and = true;
        }

        sb.append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedTable
                        .getPrimaryKeyColumn()));
        sb.append(" = "); //$NON-NLS-1$
        //sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedTable.getPrimaryKeyColumn()));
        sb.append("#{id}");
        answer.addElement(new TextElement(sb.toString()));

        if (context.getPlugins()
                .sqlMapGetElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
