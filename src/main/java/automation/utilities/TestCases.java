package automation.utilities;

public enum TestCases {
    
    T1("Testing the authentication"),
    T2("Testing the purchase of two items");

    private String testName;

    TestCases(String value){
        this.testName=value;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
