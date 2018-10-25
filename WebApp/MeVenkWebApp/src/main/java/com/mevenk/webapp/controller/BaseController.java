/**
 *
 */
package com.mevenk.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mevenk.webapp.bdo.BaseBDO;
import com.mevenk.webapp.modelattribute.SampleForm;
import com.mevenk.webapp.validator.sampleform.SampleFormValidator;

/**
 * @author venky
 *
 */
@Controller
public class BaseController {

	private static final String WELCOME = "welcome";

	@Autowired
	private BaseBDO baseBDO;

	@GetMapping(value = WELCOME)
	public ModelAndView welcome(ModelMap modelMap, HttpServletRequest httpServletRequest) {
		ModelAndView modelAndViewWelcome = new ModelAndView(WELCOME);
		modelAndViewWelcome.addObject("welcomeMessage", "Hello!!" + new Date());
		Date databaseTime = baseBDO.databaseTime();
		String databaseTimeFormatted = baseBDO.databaseTimeFormatted();
		modelAndViewWelcome.addObject("databaseTime", databaseTime);
		modelAndViewWelcome.addObject("databaseTimeFormatted", databaseTimeFormatted);
		return modelAndViewWelcome;
	}

	@GetMapping(value = "/sampleFormSubmitter")
	public ModelAndView sampleFormSubmitter(ModelMap modelMap, HttpServletRequest httpServletRequest) {
		ModelAndView modelAndViewSampleFormSubmitter = new ModelAndView("sampleFormSubmission");

		modelAndViewSampleFormSubmitter.addObject("sampleForm", new SampleForm());

		updateModelAndViewForViewParameters(modelAndViewSampleFormSubmitter);

		return modelAndViewSampleFormSubmitter;
	}

	@PostMapping(value = "/sampleFormSubmit")
	public ModelAndView sampleFormSubmit(ModelMap modelMap, HttpServletRequest httpServletRequest,
			@ModelAttribute("sampleForm") SampleForm sampleForm, BindingResult bindingResult) {

		ModelAndView modelAndViewSampleFormSubmit = new ModelAndView("sampleFormSubmission");

		updateModelAndViewForViewParameters(modelAndViewSampleFormSubmit);

		if (new SampleFormValidator(bindingResult, sampleForm, httpServletRequest).hasErrors()) {
			System.out.println("Validator ERRORS!!!");
		}

		modelAndViewSampleFormSubmit.addObject("sampleForm", sampleForm);
		return modelAndViewSampleFormSubmit;
	}

	/**
	 * @return
	 */
	private static void updateModelAndViewForViewParameters(ModelAndView modelAndView) {
		Map<String, Integer> radioButtonItems = new HashMap<>();
		for (int indexRadioButtonItems = 1; indexRadioButtonItems <= 10; indexRadioButtonItems++) {
			radioButtonItems.put(String.valueOf(indexRadioButtonItems), indexRadioButtonItems);
		}
		modelAndView.addObject("radioButtonItems", radioButtonItems);
	}

}
