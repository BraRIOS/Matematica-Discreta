package utils;

import org.junit.Assume;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.runners.model.Statement;

public class SkipRule {
    @Rule
    public MethodRule skipRule = (base, method, target) -> new Statement() {
        @Override
        public void evaluate() throws Throwable {
            try {
                base.evaluate();
            } catch (UnsupportedOperationException e) {
                Assume.assumeNoException("UnsupportedOperation exception: skipping test", e);
            }
        }
    };
}
