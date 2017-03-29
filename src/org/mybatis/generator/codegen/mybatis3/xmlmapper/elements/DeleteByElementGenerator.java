package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class DeleteByElementGenerator extends AbstractXmlElementGenerator {

    public DeleteByElementGenerator() {
        super();
    }

	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("delete"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", "deleteBy")); //$NON-NLS-1$

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("delete from "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        
        sb.setLength(0);
        sb.append("where 1=1 ");
        answer.addElement(new TextElement(sb.toString()));
        XmlElement ifc = new XmlElement("if"); //$NON-NLS-1$
        ifc.addAttribute(new Attribute("test", "sqlClause != null"));
        ifc.addElement(new TextElement("${sqlClause}"));
        answer.addElement(ifc);
        
        if (context.getPlugins()
                .sqlMapDeleteByElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
	}

}
