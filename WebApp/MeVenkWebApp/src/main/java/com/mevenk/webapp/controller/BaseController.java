/**
 *
 */
package com.mevenk.webapp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mevenk.webapp.bdo.BaseBDO;

/**
 * @author venky
 *
 */
@Controller
public class BaseController {

	private static final String WELCOME = "welcome";

	@Autowired
	private BaseBDO baseBDO;

	@RequestMapping(value = WELCOME, method = GET)
	public ModelAndView welcome(ModelMap modelMap, HttpServletRequest httpServletRequest) {
		ModelAndView modelAndViewWelcome = new ModelAndView(WELCOME);
		modelAndViewWelcome.addObject("welcomeMessage", "Hello!!" + new Date());
		Date databaseTime = baseBDO.databaseTime();
		String databaseTimeFormatted = baseBDO.databaseTimeFormatted();
		modelAndViewWelcome.addObject("databaseTime", databaseTime);
		modelAndViewWelcome.addObject("databaseTimeFormatted", databaseTimeFormatted);
		return modelAndViewWelcome;
	}

}
