// package com.example.demo.security;

// import org.testng.ITestContext;
// import org.testng.ITestListener;
// import org.testng.ITestResult;

// public class TestResultListener implements ITestListener {

//     @Override
//     public void onTestStart(ITestResult result) {
//         System.out.println("Test Started: " + result.getName());
//     }

//     @Override
//     public void onTestSuccess(ITestResult result) {
//         System.out.println("Test PASSED: " + result.getName());
//     }

//     @Override
//     public void onTestFailure(ITestResult result) {
//         System.out.println("Test FAILED: " + result.getName());
//         result.getThrowable().printStackTrace();
//     }

//     @Override
//     public void onTestSkipped(ITestResult result) {
//         System.out.println("Test SKIPPED: " + result.getName());
//     }

//     @Override
//     public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//     }

//     @Override
//     public void onStart(ITestContext context) {
//         System.out.println("Test Suite Started: " + context.getName());
//     }

//     @Override
//     public void onFinish(ITestContext context) {
//         System.out.println("Test Suite Finished: " + context.getName());
//     }
// }
