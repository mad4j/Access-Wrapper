package dolmisani.test.accesswrapper.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    AccessWrapperTest.class,
    SignatureToolkitTest.class
})
public class AllTests {}
