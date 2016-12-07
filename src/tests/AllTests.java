package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all tests.
 * @author GROUP8
 * @version 12/6/2016
 *
 */
@RunWith(Suite.class)
@SuiteClasses({AlumniCollectionTests.class, AlumniTest.class, TestInternship.class, TestJob.class,
    TransferCollegeTest.class})
public class AllTests {

}