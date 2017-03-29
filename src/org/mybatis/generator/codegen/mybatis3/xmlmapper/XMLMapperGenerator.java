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
package org.mybatis.generator.codegen.mybatis3.xmlmapper;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AddElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BaseColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.CountElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.GetElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.GetListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ResultMapElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.RunSqlElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateElementGenerator;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class XMLMapperGenerator extends AbstractXmlGenerator {

    public XMLMapperGenerator() {
        super();
    }

    protected XmlElement getSqlMapElement() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString(
                "Progress.12", table.toString())); //$NON-NLS-1$
        XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
        String namespace = introspectedTable.getMyBatis3SqlMapNamespace();
        answer.addAttribute(new Attribute("namespace", namespace));

        context.getCommentGenerator().addRootComment(answer);
        
        addResultMapElement(answer);
        addBaseColumnListElement(answer);
        addAddElement(answer);
        addDeleteElement(answer);
        addDeleteByElement(answer);
        addUpdateElement(answer);
        addUpdateByElement(answer);
        addGetElement(answer);
        addGetListElement(answer);
        addCountElement(answer);
        addRunSqlElement(answer);

        return answer;
    }

    protected void addResultMapElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBaseResultMap()) {
            AbstractXmlElementGenerator elementGenerator = new ResultMapElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }


    protected void addGetListElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateGet()) {
            AbstractXmlElementGenerator elementGenerator = new GetListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    protected void addCountElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateDelete()) {
            AbstractXmlElementGenerator elementGenerator = new CountElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    protected void addRunSqlElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateDeleteBy()) {
            AbstractXmlElementGenerator elementGenerator = new RunSqlElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    protected void addGetElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateGet()) {
            AbstractXmlElementGenerator elementGenerator = new GetElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    protected void addDeleteElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateDelete()) {
            AbstractXmlElementGenerator elementGenerator = new DeleteElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    protected void addDeleteByElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateDeleteBy()) {
            AbstractXmlElementGenerator elementGenerator = new DeleteByElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    protected void addAddElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBaseColumnList()) {
            AbstractXmlElementGenerator elementGenerator = new AddElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    protected void addUpdateElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBaseColumnList()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    protected void addUpdateByElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBaseColumnList()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    protected void addBaseColumnListElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBaseColumnList()) {
            AbstractXmlElementGenerator elementGenerator = new BaseColumnListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void initializeAndExecuteGenerator(
            AbstractXmlElementGenerator elementGenerator,
            XmlElement parentElement) {
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.setProgressCallback(progressCallback);
        elementGenerator.setWarnings(warnings);
        elementGenerator.addElements(parentElement);
    }

    @Override
    public Document getDocument() {
        Document document = new Document(
                XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
                XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
        document.setRootElement(getSqlMapElement());

        if (!context.getPlugins().sqlMapDocumentGenerated(document,
                introspectedTable)) {
            document = null;
        }

        return document;
    }
}
