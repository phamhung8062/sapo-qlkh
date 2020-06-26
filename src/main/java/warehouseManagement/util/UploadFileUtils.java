package warehouseManagement.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UploadFileUtils {
	private final static String root = "F:/trainningReact/TT-SAPO/public";
	// private final String root = "/uploads/";

	public static String writeOrUpdate(byte[] bytes, String path) {
		File file = new File(StringUtils.substringBeforeLast(root + path, "/"));
		if (!file.exists()) {
			file.mkdir();
		}
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(new File(root + path));
			fileOutputStream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (path);

	}

}
