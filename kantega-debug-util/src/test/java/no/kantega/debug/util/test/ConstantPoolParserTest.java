package no.kantega.debug.util.test;

import junit.framework.Assert;
import no.kantega.debug.bytecode.ConstantPool;
import no.kantega.debug.bytecode.ConstantResolver;

import org.junit.Test;

public class ConstantPoolParserTest {

	
	@Test
	public void testConstantPoolFromJdi() {
		byte[] pool={7, 0, 2, 1, 0, 19, 100, 101, 98, 117, 103, 116, 101, 115, 116, 47, 68, 101, 98, 117, 103, 84, 101, 115, 116, 7, 0, 4, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3, 40, 41, 86, 1, 0, 4, 67, 111, 100, 101, 10, 0, 3, 0, 9, 12, 0, 5, 0, 6, 1, 0, 15, 76, 105, 110, 101, 78, 117, 109, 98, 101, 114, 84, 97, 98, 108, 101, 1, 0, 18, 76, 111, 99, 97, 108, 86, 97, 114, 105, 97, 98, 108, 101, 84, 97, 98, 108, 101, 1, 0, 4, 116, 104, 105, 115, 1, 0, 21, 76, 100, 101, 98, 117, 103, 116, 101, 115, 116, 47, 68, 101, 98, 117, 103, 84, 101, 115, 116, 59, 1, 0, 4, 116, 101, 115, 116, 1, 0, 25, 82, 117, 110, 116, 105, 109, 101, 86, 105, 115, 105, 98, 108, 101, 65, 110, 110, 111, 116, 97, 116, 105, 111, 110, 115, 1, 0, 16, 76, 111, 114, 103, 47, 106, 117, 110, 105, 116, 47, 84, 101, 115, 116, 59, 9, 0, 18, 0, 20, 7, 0, 19, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 121, 115, 116, 101, 109, 12, 0, 21, 0, 22, 1, 0, 3, 111, 117, 116, 1, 0, 21, 76, 106, 97, 118, 97, 47, 105, 111, 47, 80, 114, 105, 110, 116, 83, 116, 114, 101, 97, 109, 59, 8, 0, 24, 1, 0, 11, 72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 10, 0, 26, 0, 28, 7, 0, 27, 1, 0, 19, 106, 97, 118, 97, 47, 105, 111, 47, 80, 114, 105, 110, 116, 83, 116, 114, 101, 97, 109, 12, 0, 29, 0, 30, 1, 0, 7, 112, 114, 105, 110, 116, 108, 110, 1, 0, 21, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 41, 86, 10, 0, 3, 0, 32, 12, 0, 33, 0, 34, 1, 0, 8, 116, 111, 83, 116, 114, 105, 110, 103, 1, 0, 20, 40, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 10, 0, 36, 0, 32, 7, 0, 37, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 1, 0, 1, 97, 1, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 1, 0, 10, 83, 111, 117, 114, 99, 101, 70, 105, 108, 101, 1, 0, 14, 68, 101, 98, 117, 103, 84, 101, 115, 116, 46, 106, 97, 118, 97};
		ConstantResolver resolver=new ConstantPool(pool);
		Assert.assertEquals("Method reference correctly parsed", "java.lang.String.toString()Ljava/lang/String;",resolver.getConstant(35).toString());
//		Assert.assertEquals("", actual);
	}

}
