package hr.fer.zemris.java.hw16.jvdraw.menuactions.saving;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Method contains informations about current saving status
 * 
 * @author Mihael
 *
 */
public abstract class SaveFileInfo {
	/**
	 * File is saved,there wasn't any change
	 */
	private static boolean saved = true;
	/**
	 * Path to saved file
	 */
	private static Path path = null;

	/**
	 * Method sets that file has been saved
	 */
	public static void setUnModified() {
		saved = true;
	}

	/**
	 * Method sets that file has been modified
	 */
	public static void setModified() {
		saved = false;
	}

	/**
	 * Method returns storing path of current active file
	 * 
	 * @return path to current active path
	 */
	public static Path getPath() {
		return path;
	}

	/**
	 * Method sets path of current active path
	 * 
	 * @param path
	 *            - path
	 * 
	 * @throws NullPointerException
	 *             - if path is <code>null</code>
	 */
	public static void setPath(Path path) {
		SaveFileInfo.path = Objects.requireNonNull(path, "path cannot be null!");
	}

	/**
	 * Method returns if file is modified
	 * 
	 * @return if fill is modified
	 */
	public static boolean isModified() {
		return !saved;
	}
}
