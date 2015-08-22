/**
 * 
 */
package br.com.caiopaulucci.ast.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Caio Paulucci
 *
 */
public class FileUtils {

	public static StringBuffer readFile(String path) throws IOException {
		Charset utf8charset = Charset.forName("UTF-8");
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		StringBuffer ret = new StringBuffer(utf8charset.decode(ByteBuffer.wrap(encoded)));
		return ret;
	}
	
}
