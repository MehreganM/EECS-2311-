
import java.util.ArrayList;
import java.util.List;
	
public class labratory {



	public class Laboratory {
	    private List<TestRequest> testRequests;

	    public Laboratory() {
	        this.testRequests = new ArrayList<>();
	    }

	    public boolean addTestRequest(TestRequest request) {
	        return testRequests.add(request);
	    }

	    public TestResult processTestRequest(TestRequest request) {

	        return new TestResult(request, "Result Details");
	    }
	}


	

}

