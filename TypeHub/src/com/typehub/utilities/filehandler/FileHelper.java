package com.typehub.utilities.filehandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;

/**
 * The {@code FileHelper} class provides utility methods for handling text files
 * related to the TypeHub typing speed test application. It allows for reading
 * sample text from a file, which can be displayed in the typing area.
 * 
 * <p>This class currently contains a method that returns a sample paragraph
 * string, but it can be expanded to include additional file operations as needed.
 * </p>
 * 
 * <p><strong>Example Usage:</strong></p>
 * <pre>
 *     String paragraph = FileHelper.getSampleParagraph();
 * </pre>
 * 
 * @author Omkar Kshirsagar
 * @version 1.0
 * @since 1.0
 */


public class FileHelper {
	
	private static final String sampleFilePath = "src/resources/sampleFile.txt";
	
	
	
	/**
	 * Reads and returns a paragraph of text from an external file specified by {@code sampleFilePath}.
	 * This text can be displayed in the typing area of the TypeHub application.
	 * <p>
	 * If the file is not found or an error occurs during reading, the method will log the issue and
	 * return an error message.
	 * </p>
	 *
	 * @return A string containing the text to be typed by the user. If an error occurs, 
	 *         the returned string will contain the error message.
	 *
	 * @throws FileNotFoundException if the specified file does not exist at {@code sampleFilePath}.
	 * @throws IOException if an error occurs while reading from the file.
	 *
	 * <p><strong>Example Usage:</strong></p>
	 * <pre>
	 *     String paragraph = FileHelper.getSampleParagraph();
	 *     System.out.println(paragraph);
	 * </pre>
	 */
    public static String getSampleParagraph() {
        // Actual file logic or predefined text can be added here
    	
    	String paragraph = "";
    	
        try(BufferedReader bufferedReader = new BufferedReader( new FileReader( sampleFilePath ) )){
        	
        	
        	String line = null;
        	while( (line= bufferedReader.readLine()) != null ) {
        		paragraph += line;
        	}
        }
        catch(FileNotFoundException e) {
        	System.out.println("File Not Found : " + e.getMessage());
        	e.printStackTrace();
        	return "Error: " + e.getMessage();
        }
        catch(IOException e) {
        	System.out.println("Error while Reading File : " + e.getMessage());
        	e.printStackTrace();
        	return "Error: " + e.getMessage();
        }
        return paragraph;
    }
}
















