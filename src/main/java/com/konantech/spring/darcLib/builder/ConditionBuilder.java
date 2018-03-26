package com.konantech.spring.darcLib.builder;

import com.konantech.spring.darcLib.EscapeUtils;
import com.konantech.spring.darcLib.SchemaViewManager;
import com.konantech.spring.darcLib.query.Query;
import com.konantech.spring.darcLib.query.search.*;
import com.konantech.spring.darcLib.spi.FieldDefinition;
import com.konantech.spring.darcLib.spi.SchemaView;
import org.apache.commons.lang.StringUtils;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConditionBuilder {

	private static DateFormat datetimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	private SchemaView schema;
	private StringWriter writer;

	public String build(Query query) {
		if (query.getConstraint() == null) {
			return StringUtils.EMPTY;
		}
		schema = SchemaViewManager.getInstance().getSchemaSearchView(
				query.getMediaType());
		writer = new StringWriter();
		writer.write("<condexp>");
		condition(query.getConstraint());
		writer.write("</condexp>");
		return writer.toString();
	}
	
	private void condition(SearchTerm term) {
		if (term instanceof BooleanTerm) {
			condition((BooleanTerm) term);
		} else if (term instanceof DateTerm) {
			condition((DateTerm) term);
		} else if (term instanceof ListTerm) {
			condition((ListTerm) term);
		} else if (term instanceof LongTerm) {
			condition((LongTerm) term);
		} else if (term instanceof StringTerm) {
			condition((StringTerm) term);
		} else if (term instanceof CompositeTerm) {
			condition((CompositeTerm) term);
		} else if (term instanceof UserRelationTerm) {
			condition((UserRelationTerm) term);
		}
	}
	
	private void condition(CompositeTerm term) {
		if (term.size() == 0) {
			return;
		}
		String tagName = (term instanceof AndTerm) ? "andcond" : "orcond";
		writer.write("<");
		writer.write(tagName);
		writer.write(">");
		for (SearchTerm aTerm : term.getTerms()) {
			condition(aTerm);
		}
		writer.write("</");
		writer.write(tagName);
		writer.write(">");
	}
	
	private void condition(BooleanTerm term) {
		numbercond(term.getPropertyName(), term.isSet() ? "1" : "0", "false");
	}
	
	private void condition(DateTerm term) {
		FieldDefinition fdef = schema.getFieldDefinition(term.getPropertyName());
		if ("datetime".equals(fdef.getRequiredType())) {
			comparison(term.getPropertyName(), datetimeFormat.format(term.getDate()), term.getComparison());
		} else if ("date".equals(fdef.getRequiredType())) {
			comparison(term.getPropertyName(), dateFormat.format(term.getDate()), term.getComparison());
		}
	}
	
	private void condition(ListTerm term) {
		FieldDefinition fdef = schema.getFieldDefinition(term.getPropertyName());
		boolean isText = ("string".equals(fdef.getRequiredType()) 
				|| "longstring".equals(fdef.getRequiredType()));
		listcond(term.getPropertyName(), term.getPattern(), String.valueOf(isText), "false");
	}
	
	private void condition(LongTerm term) {
		comparison(term.getPropertyName(), Long.toString(term.getNumber()), term.getComparison());
	}
	
	private void condition(StringTerm term) {
		FieldDefinition fdef = schema.getFieldDefinition(term.getPropertyName());
		if ("string".equals(fdef.getRequiredType())
				|| "longstring".equals(fdef.getRequiredType())) {
			textcond(term.getPropertyName(), term.getPattern(), "3", "false");
		} else if ("code".equals(fdef.getRequiredType())) {
			codecond(term.getPropertyName(), term.getPattern(), "false");
		} else if ("tree".equals(fdef.getRequiredType())) {
			treecond(term.getPropertyName(), term.getPattern(), "false", "true");
		} else if ("group".equals(fdef.getRequiredType())) {
			groupcond(term.getPropertyName(), term.getPattern(), "false", "false");
		} else if ("user".equals(fdef.getRequiredType())) {
			usercond(term.getPropertyName(), term.getPattern(), "false");
		} else if ("status".equals(fdef.getRequiredType())) {
			if ("2000".equals(term.getPattern())) {
				writer.write("<andcond>");				
				writer.write("<comparisoncond field=\"");
				writer.write(term.getPropertyName());
				writer.write("\" value=\"2000\" comparison=\"ge\"/>");
				writer.write("<comparisoncond field=\"");
				writer.write(term.getPropertyName());
				writer.write("\" value=\"2100\" comparison=\"le\"/>");
				writer.write("</andcond>");
				
			} else {
				comparison(term.getPropertyName(), term.getPattern(), term.getComparison());
			}			
		} else {
			comparison(term.getPropertyName(), term.getPattern(), term.getComparison());
		}
	}
	
	private void condition(UserRelationTerm term) {		
		writer.write("<userrelationcondweb field=\"");
		writer.write(term.getPropertyName());
		writer.write("\" assettype=\"");
		writer.write(SchemaViewManager.getAssetViewName(term.getType()));
		writer.write("\" username=\"");
		writer.write(term.getUserName());
		writer.write("\" subtype=\"");
		writer.write(String.valueOf(term.getSubtype()));
		writer.write("\" common=\"");
		writer.write("true");
		writer.write("\"/>");
	}
	
	private void comparison(String fieldName, String fieldValue, int comparison) {
        switch (comparison) {
        case ComparisonTerm.EQ:
        	numbercond(fieldName, fieldValue, "false");
        	break;
        case ComparisonTerm.NE:
        	numbercond(fieldName, fieldValue, "true");
        	break;
        case ComparisonTerm.GT:
        	comparisoncond(fieldName, fieldValue, "gt");
        	break;
        case ComparisonTerm.GE:
        	comparisoncond(fieldName, fieldValue, "ge");
        	break;
        case ComparisonTerm.LT:
        	comparisoncond(fieldName, fieldValue, "lt");
        	break;
        case ComparisonTerm.LE:
        	comparisoncond(fieldName, fieldValue, "le");
        	break;
        }
	}
	
	private void numbercond(String fieldName, String fieldValue, String isNot) {
		writer.write("<numbercond field=\"");
		writer.write(fieldName);
		writer.write("\" value=\"");
		writer.write(EscapeUtils.escapeXml(fieldValue));
		writer.write("\" notcond=\"");
		writer.write(isNot);
		writer.write("\"/>");
	}
	
	private void codecond(String fieldName, String fieldValue, String isNot) {
		writer.write("<codecondweb field=\"");
		writer.write(fieldName);
		writer.write("\" value=\"");
		writer.write(fieldValue);
		writer.write("\" notcond=\"");
		writer.write(isNot);
		writer.write("\"/>");
	}
	
	private void comparisoncond(String fieldName, String fieldValue, String comparison) {
		writer.write("<comparisoncond field=\"");
		writer.write(fieldName);
		writer.write("\" value=\"");
		writer.write(fieldValue);
		writer.write("\" comparison=\"");
		writer.write(comparison);
		writer.write("\"/>");
	}
	
	private void listcond(String fieldName, String[] pattern, String isText, String isNot) {
		writer.write("<listcond  field=\"");
		writer.write(fieldName);
		writer.write("\" istext=\"");
		writer.write(isText);
		writer.write("\" notcond=\"");
		writer.write(isNot);
		writer.write("\">");
		writer.write(EscapeUtils.escapeXml(StringUtils.join(pattern, " ")));
		writer.write("</listcond>");
	}
	
	private void groupcond(String fieldName, String fieldValue, String isNot,
			String includeChild) {
		writer.write("<groupcondweb field=\"");
		writer.write(fieldName);
		writer.write("\" value=\"");
		writer.write(EscapeUtils.escapeXml(fieldValue));
		writer.write("\" notcond=\"");
		writer.write(isNot);
		writer.write("\" includechild=\"");
		writer.write(includeChild);
		writer.write("\"/>");
	}
	private void usercond(String fieldName, String fieldValue, String isNot) {
		writer.write("<usercondweb field=\"");
		writer.write(fieldName);
		writer.write("\" value=\"");
		writer.write(EscapeUtils.escapeXml(fieldValue));
		writer.write("\" notcond=\"");
		writer.write(isNot);		
		writer.write("\"/>");
	}
	
	private void textcond(String fieldName, String fieldValue, String method, String isNot) {
		writer.write("<textcond field=\"");
		writer.write(fieldName);
		writer.write("\" keyword=\"");
		writer.write(EscapeUtils.escapeXml(fieldValue));
		writer.write("\" method=\"");
		writer.write(method);
		writer.write("\" notcond=\"");
		writer.write(isNot);
		writer.write("\"/>");
	}
	
	private void treecond(String fieldName, String fieldValue, String isNot,
			String includeChild) {
		writer.write("<treecondweb field=\"");
		writer.write(fieldName);
		writer.write("\" value=\"");
		writer.write(fieldValue);
		writer.write("\" notcond=\"");
		writer.write(isNot);
		writer.write("\" includechild=\"");
		writer.write(includeChild);
		writer.write("\"/>");
	}
	
}
