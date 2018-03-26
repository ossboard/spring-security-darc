package com.konantech.spring.darcLib.spi;

import com.konantech.spring.darcLib.SchemaViewManager;
import com.konantech.spring.darcLib.model.Code;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class FieldDefinition {

    private String type;
    private String ref;
    private String caption;
    private String controlType;
    private boolean readOnly = false;
    private boolean visible = false;

    public FieldDefinition(String type, String caption, boolean readOnly) {
        this.type = type;
        this.caption = caption;
        this.readOnly = readOnly;
        this.visible = true;
    }

    public FieldDefinition(String type, String caption) {
        this(type, caption, false);
    }

    public String getRequiredType() {
        return type;
    }

    public String getReferenceValue() {
        return ref;
    }

    public void setReferenceValue(String ref) {
        this.ref = ref;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public String getControlType() {
        return this.controlType;
    }

    public List<Code.CodeItem> getCodeItems() {
        if ("code".equals(type) && StringUtils.isNotEmpty(ref)) {
            Code code = (Code) SchemaViewManager.getInstance().getCode(ref);
            if (code != null) {
                return code.getCodeItems();
            }
        }
        return null;
    }

}
