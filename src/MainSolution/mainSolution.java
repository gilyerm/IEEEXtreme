package MainSolution;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

public class mainSolution
{
	public static void main(String[] args) throws Exception {
		String inputPath = "src/Drawing_Rooted_Binary_Trees/input";
		setIn(inputPath);
		Drawing_Rooted_Binary_Trees.Solution.main(args);
	}
	public static InputStream setIn(String inputPath) throws FileNotFoundException {
		InputStream in = System.in;
		System.setIn(
			new FileInputStream(
					Paths.get(inputPath).toFile()
			)
		);
		return in;
	}
}
