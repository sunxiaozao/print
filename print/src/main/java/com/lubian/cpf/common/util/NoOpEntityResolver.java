package com.lubian.cpf.common.util;

import java.io.ByteArrayInputStream;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

class NoOpEntityResolver implements EntityResolver {

	NoOpEntityResolver() {
	}

	public InputSource resolveEntity(String publicId, String systemId) {
		return new InputSource(new ByteArrayInputStream(new byte[0]));
	}
}
