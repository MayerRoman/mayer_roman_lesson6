package com.epam.main.task_1.caches_factory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Mayer Roman on 23.05.2016.
 */
class CachesFinder {

    private final static String CLASS_EXT = ".class";

    public static List<Class<?>> findClassesInPackage(String packageName) {
        List<Class<?>> classes;

        String relPath = packageName.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
        if (resource == null) {
            throw new RuntimeException("No resource for " + relPath);
        }
        String fullPath = resource.getFile();

        File directory;
        try {
            directory = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(packageName +
                    " (" + resource + ") does not appear to be a valid URL / URI. ", e);
        } catch (IllegalArgumentException e) {
            directory = null;
        }

        if (directory != null && directory.exists()) {

            classes = findUsingClassFiles(packageName, directory);

        } else {

            try {
                classes = findUsingJarFile(fullPath, relPath);
            } catch (IOException e) {
                throw new RuntimeException(packageName + " (" + directory + ") does not appear to be a valid package", e);
            }
        }

        return classes;
    }

    private static List<Class<?>> findUsingClassFiles(String packageName, File fileDirectory) {
        List<Class<?>> classes = new ArrayList<>();


        String[] files = fileDirectory.list();
        for (int i = 0; i < files.length; i++) {
            if (files[i].endsWith(CLASS_EXT)) {
                String className = packageName + '.' + files[i].substring(0, files[i].length() - CLASS_EXT.length());
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("ClassNotFoundException loading " + className);
                }
            }
        }
        return classes;
    }

    private static List<Class<?>> findUsingJarFile(String fullPath, String relPath) throws IOException {
        String jarPath = fullPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        JarFile jarFile = new JarFile(jarPath);

        List<Class<?>> classes = new ArrayList<>();

        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();

            if (entryName.startsWith(relPath) && entryName.length() > (relPath.length() + "/".length())) {
                String className = entryName.replace('/', '.').replace('\\', '.').replace(CLASS_EXT, "");
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("ClassNotFoundException loading " + className);
                }
            }
        }

        return classes;
    }
}
