package clients.review;

public class ReviewController {
  private ReviewModel model = null;
  private ReviewView  view  = null;

  /**
   * Constructor
   * @param model The model 
   * @param view  The view from which the interaction came
   */
  public ReviewController( ReviewModel model, ReviewView view )
  {
    this.view  = view;
    this.model = model;
  }

  /**
   * Check interaction from view
   * @param pn The product number to be checked
   */
  public void doCheck( String pn )
  {
    model.doCheck(pn);
  }

  /**
   * reserve product
   * @param pn The product number to be reserved
   */
  public void doReview( String review, String sRating )
  {
	  double rating = 0.0;
	  try {
		  rating = Double.parseDouble(sRating);
	  } catch (NumberFormatException e) {
		  rating = 0.0;
	  }
	  model.doReview(review, rating);
  }
}

