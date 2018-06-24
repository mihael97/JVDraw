package hr.fer.zemris.java.hw16.jvdraw.menuactions;

import java.nio.file.Path;
import java.util.Objects;

public abstract class SaveFile {
	private static boolean saved = false;
	private static Path path = null;

	public static void setUnModified() {
		saved = true;
	}

	public static void setModified() {
		saved = false;
	}

	public static Path getPath() {
		return path;
	}

	public static void setPath(Path path) {
		SaveFile.path = Objects.requireNonNull(path, "path cannot be null!");
	}

	public static boolean isModified() {
		return !saved;
	}
}
