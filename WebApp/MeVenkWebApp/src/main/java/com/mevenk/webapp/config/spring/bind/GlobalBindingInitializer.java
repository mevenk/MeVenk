/**
 *
 */
package com.mevenk.webapp.config.spring.bind;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;

/**
 * @author venky
 *
 */
@Component
public class GlobalBindingInitializer extends ConfigurableWebBindingInitializer {

	public GlobalBindingInitializer() {
		setConversionService(new FormattingConversionService());
		setAutoGrowNestedPaths(true);
	}

	@Override
	public void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(""), true));
	}
}
