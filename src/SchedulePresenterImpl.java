
public class SchedulePresenterImpl implements SchedulePresenter {

	String status = "";
	
	@Override
	public void succeed() {
		status = "success";
	}

	@Override
	public void fail() {
		status = "fail";
	}

	public String getStatus() {
		return status;
	}

}
