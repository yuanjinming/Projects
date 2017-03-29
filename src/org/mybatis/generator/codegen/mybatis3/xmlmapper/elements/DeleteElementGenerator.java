package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

public class DeleteElementGenerator extends AbstractXmlElementGenerator {

    public DeleteElementGenerator() {
        super();
    }

	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("delete"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", "delete")); //$NON-NLS-1$

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("delete from "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        
        sb.setLength(0);
        sb.append("where ");
        sb.append(MyBatis3FormattingUtilities
        		.getEscapedColumnName(introspectedTable.getPrimaryKeyColumn()));
        sb.append(" in ");
        answer.addElement(new TextElement(sb.toString()));
        

        XmlElement valuesForeachClause = new XmlElement("foreach");
        valuesForeachClause.addAttribute(new Attribute("collection", "list"));
        valuesForeachClause.addAttribute(new Attribute("index", "index"));
        valuesForeachClause.addAttribute(new Attribute("item", "id"));
        valuesForeachClause.addAttribute(new Attribute("open", "("));
        valuesForeachClause.addAttribute(new Attribute("separator", ","));
        valuesForeachClause.addAttribute(new Attribute("close", ")"));
        
        valuesForeachClause.addElement(new TextElement("#{id}"));
        
        answer.addElement(valuesForeachClause);

        if (context.getPlugins()
                .sqlMapDeleteElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
	}

}
