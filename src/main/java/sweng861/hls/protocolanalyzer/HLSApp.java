package sweng861.hls.protocolanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

public class HLSApp {
	
//	private static final String tagPattern = "^#EXT.+$";
//	private static final String tsFilePattern = "^.+\\.ts$";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String urlStr = "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8";
		String urlStr = "http://146.186.90.203:8080/Arris/ipad.m3u8";
		
		MediaStreamAnalyzerResult result;
		HLSMediaStreamAnalyzerService fileService = new HLSMediaStreamAnalyzerServiceImpl();
		try {
			result = fileService.analyzeFiles(urlStr, true);
		}catch (Exception e){
			e.printStackTrace();
		}
		

	}

//	private static void iterateOverFiles(String urlStr)
//			throws MalformedURLException, IOException {
//		int lastIndex = urlStr.lastIndexOf('/');
//		String baseUrl = urlStr.substring(0, lastIndex+1);
//		System.out.println(urlStr);
//		//System.out.println(baseUrl.length);
//		
//		URL url;
//		url = new URL(urlStr);
//		//Need to determine base URL by parsing at last /
//		URLConnection connection = url.openConnection();
//		connection.connect();
//		InputStream inStream = (InputStream)connection.getContent();
//		InputStreamReader inStreamReader = new InputStreamReader(inStream);
//		BufferedReader reader = new BufferedReader(inStreamReader);
//		String line = "";
//		do {
//			line = reader.readLine();
////			System.out.println(line);
//			if(line != null && !line.matches(tagPattern)){
//
//				String nextURL = baseUrl.concat(line);
//			
////				System.out.println(nextURL);
//				if (!line.matches(tsFilePattern)){
//					iterateOverFiles(nextURL);
//				}
//			}
//			
//			
//		}while (line != null);
//	}


	

}
