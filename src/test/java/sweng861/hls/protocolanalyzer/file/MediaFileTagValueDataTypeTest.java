/**
 * 
 */
package sweng861.hls.protocolanalyzer.file;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Scott
 *
 */
public class MediaFileTagValueDataTypeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType#getDataTypeRegEx()}.
	 */
	@Test
	public void testGetDataTypeRegEx_ExpectedMatches() {
		assertTrue("".matches(MediaFileTagValueDataType.NONE.getDataTypeRegEx()));
		assertTrue("123".matches(MediaFileTagValueDataType.INTEGER.getDataTypeRegEx()));
		assertTrue("123456789".matches(MediaFileTagValueDataType.DECIMAL_INTEGER.getDataTypeRegEx()));
		assertTrue("\"Quote\"".matches(MediaFileTagValueDataType.QUOTED_STRING.getDataTypeRegEx()));
		assertTrue("EnumeratedType".matches(MediaFileTagValueDataType.ENUMERATED_STRING.getDataTypeRegEx()));
		assertTrue("800x1200".matches(MediaFileTagValueDataType.DECIMAL_RESOLUTION.getDataTypeRegEx()));
		assertTrue("ATTRIBUTENAME=ATTRIBUTEVALUE".matches(MediaFileTagValueDataType.ATTRIBUTE_LIST.getDataTypeRegEx()));
		assertTrue("ATTRIBUTE-NAME=ATTRIBUTE-VALUE,ATTRIBUTE2-NAME=ATTRIBUTE2-VALUE".matches(MediaFileTagValueDataType.ATTRIBUTE_LIST.getDataTypeRegEx()));
	}
	
	/**
	 * Test method for {@link sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType#getDataTypeRegEx()}.
	 */
	@Test
	public void testGetDataTypeRegEx_ExpectedNoMatch_NONE() {
		assertFalse("a".matches(MediaFileTagValueDataType.NONE.getDataTypeRegEx()));
		assertFalse(" ".matches(MediaFileTagValueDataType.NONE.getDataTypeRegEx()));
	}
	
	/**
	 * Test method for {@link sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType#getDataTypeRegEx()}.
	 */
	@Test
	public void testGetDataTypeRegEx_ExpectedNoMatch_INTEGER() {
		assertFalse("1.2".matches(MediaFileTagValueDataType.INTEGER.getDataTypeRegEx()));
		assertFalse("a".matches(MediaFileTagValueDataType.INTEGER.getDataTypeRegEx()));
	}
	
	/**
	 * Test method for {@link sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType#getDataTypeRegEx()}.
	 */
	@Test
	public void testGetDataTypeRegEx_ExpectedNoMatch_DECIMAL_INTEGER() {
		assertFalse("1.2".matches(MediaFileTagValueDataType.DECIMAL_INTEGER.getDataTypeRegEx()));
		assertFalse("a".matches(MediaFileTagValueDataType.DECIMAL_INTEGER.getDataTypeRegEx()));
		assertFalse("123456789012345678901".matches(MediaFileTagValueDataType.DECIMAL_INTEGER.getDataTypeRegEx()));
	}
	
	/**
	 * Test method for {@link sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType#getDataTypeRegEx()}.
	 */
	@Test
	public void testGetDataTypeRegEx_ExpectedNoMatch_QUOTED_STRING() {
		assertFalse("string".matches(MediaFileTagValueDataType.QUOTED_STRING.getDataTypeRegEx()));
		assertFalse("'String'".matches(MediaFileTagValueDataType.QUOTED_STRING.getDataTypeRegEx()));
		assertFalse("1234".matches(MediaFileTagValueDataType.QUOTED_STRING.getDataTypeRegEx()));
	}
	
	/**
	 * Test method for {@link sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType#getDataTypeRegEx()}.
	 */
	@Test
	public void testGetDataTypeRegEx_ExpectedNoMatch_ENUMERATED_STRING() {
		assertFalse("\"string\"".matches(MediaFileTagValueDataType.ENUMERATED_STRING.getDataTypeRegEx()));
		assertFalse("'String'".matches(MediaFileTagValueDataType.ENUMERATED_STRING.getDataTypeRegEx()));
	
	}
	
	/**
	 * Test method for {@link sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType#getDataTypeRegEx()}.
	 */
	@Test
	public void testGetDataTypeRegEx_ExpectedNoMatch_DECIMAL_RESOLUTION() {
		assertFalse("1234x".matches(MediaFileTagValueDataType.DECIMAL_RESOLUTION.getDataTypeRegEx()));
		assertFalse("400X800".matches(MediaFileTagValueDataType.DECIMAL_RESOLUTION.getDataTypeRegEx()));
		assertFalse("400x200x".matches(MediaFileTagValueDataType.DECIMAL_RESOLUTION.getDataTypeRegEx()));
		assertFalse("x800".matches(MediaFileTagValueDataType.DECIMAL_RESOLUTION.getDataTypeRegEx()));
	
	}
	
	/**
	 * Test method for {@link sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType#getDataTypeRegEx()}.
	 */
	@Test
	public void testGetDataTypeRegEx_ExpectedNoMatch_ATTRIBUTE_LIST() {
		assertFalse("ATTRIBUTE = VALUE".matches(MediaFileTagValueDataType.ATTRIBUTE_LIST.getDataTypeRegEx()));
		assertFalse("ATTRIBUTE=VALUE ATTRIBUTE2=VALUE2".matches(MediaFileTagValueDataType.ATTRIBUTE_LIST.getDataTypeRegEx()));
		assertFalse("attribute".matches(MediaFileTagValueDataType.ATTRIBUTE_LIST.getDataTypeRegEx()));
		assertFalse("=value".matches(MediaFileTagValueDataType.ATTRIBUTE_LIST.getDataTypeRegEx()));
	
	}


}
