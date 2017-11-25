package uk.ac.herts.odeon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.herts.odeon.service.mock.MockCustomerService;
import uk.ac.herts.odeon.service.mock.MockMovieService;
import uk.ac.herts.odeon.service.mock.MockScreenService;
import uk.ac.herts.odeon.service.mock.MockReviewService;
import uk.ac.herts.odeon.service.mock.MockPaymentService;

@Service
public class MockInitService {

	@Autowired
	protected MockCustomerService customerService;

	@Autowired
	protected MockMovieService movieService;

	@Autowired
	protected MockScreenService screenService;

	@Autowired
	protected MockReviewService reviewService;

	@Autowired
	protected MockPaymentService paymentService;

  public void init() {
		customerService.initData();
		movieService.initData();
		screenService.initData();
		paymentService.initData();
		reviewService.initData();
  }

}
