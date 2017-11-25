package uk.ac.herts.odeon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;

import uk.ac.herts.odeon.repo.PurchaseRepository;
import uk.ac.herts.odeon.repo.ReviewRepository;
import uk.ac.herts.odeon.repo.ScreenRepository;
import uk.ac.herts.odeon.service.IReporter;

@Controller
public class MainController {

	@Autowired
	protected ScreenRepository sRepo;

	@Autowired
	protected ReviewRepository rRepo;

	@Autowired
	protected PurchaseRepository pRepo;

	@Autowired
	protected IReporter reporter;

	@RequestMapping("/")
	public String index(ModelMap model) {
		model.put("title", "The Odeon Cinema System");
		model.put("screens", sRepo.findAll());
		model.put("reviews", rRepo.findFirst4ByOrderByIdDesc());
		model.put("purchases", pRepo.findFirst5ByOrderByIdDesc());
		return "index";
	}

	@RequestMapping("/report")
	public String report(ModelMap model) {
		model.put("title", "Odeon Cinema System: Report");
		model.put("report", reporter.generate());
		return "report";
	}

	@RequestMapping("/reviews")
	public String reviews(ModelMap model) {
		model.put("title", "Odeon Cinema System: Reviews");
		model.put("reviews", rRepo.findAll());
		return "reviews";
	}

	@RequestMapping("/purchases")
	public String purchases(ModelMap model) {
		model.put("title", "Odeon Cinema System: Purchases");
		model.put("purchases", pRepo.findAll());
		return "purchases";
	}
}
