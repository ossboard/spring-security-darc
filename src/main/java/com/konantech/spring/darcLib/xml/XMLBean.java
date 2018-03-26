package com.konantech.spring.darcLib.xml;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class XMLBean extends XMLConfiguration {

    private static final long serialVersionUID = 1L;

    public XMLBean(String string) throws ConfigurationException, IOException {

        this(IOUtils.toInputStream(string, "UTF-8"));
    }

    public XMLBean(InputStream is) throws ConfigurationException {
        super();
        setAttributeSplittingDisabled(true);
        setDelimiterParsingDisabled(true);
        load(is);
    }

}
