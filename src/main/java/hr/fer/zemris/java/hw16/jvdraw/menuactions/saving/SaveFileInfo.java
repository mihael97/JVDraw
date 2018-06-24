package hr.fer.zemris.java.hw16.jvdraw.menuactions.saving;

import java.nio.file.Path;
import java.util.Objects;

public abstract class SaveFileInfo {
	private static boolean saved = true;
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
		SaveFileInfo.path = Objects.requireNonNull(path, "path cannot be null!");
	}

	public static boolean isModified() {
		return !saved;
	}
}
