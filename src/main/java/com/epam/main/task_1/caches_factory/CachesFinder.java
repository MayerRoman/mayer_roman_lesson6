package com.epam.main.task_1.caches_factory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Mayer Roman on 23.05.2016.
 */
class CachesFinder {

    public static ArrayList<Class<?>> findClassesInPackage(String packageName) {
        String pkgName = packageName;
        ArrayList<Class<?>> classes;


        String relPath = pkgName.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
        if (resource == null) {
            throw new RuntimeException("No resource for " + relPath);
        }
        String fullPath = resource.getFile();

        File directory;
        try {
            directory = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(pkgName +
                    " (" + resource + ") does not appear to be a valid URL / URI. ", e);
        } catch (IllegalArgumentException e) {
            directory = null;
        }

        if (directory != null && directory.exists()) {

            classes = findUsingClassFiles(pkgName, directory);

        } else {

            try {
                classes = findUsingJarFile(fullPath, relPath);
            } catch (IOException e) {
                throw new RuntimeException(pkgName + " (" + directory + ") does not appear to be a valid package", e);
            }
        }

        return classes;
    }

    private static ArrayList<Class<?>> findUsingClassFiles(String packageName, File fileDirectory) {
        String pkgName = packageName;
        File directory = fileDirectory;

        ArrayList<Class<?>> classes = new ArrayList<>();

        String[] files = directory.list();
        for (int i = 0; i < files.length; i++) {
            if (files[i].endsWith(".class")) {
                String className = pkgName + '.' + files[i].substring(0, files[i].length() - 6);
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("ClassNotFoundException loading " + className);
                }
            }
        }
        return classes;
    }

    private static ArrayList<Class<?>> findUsingJarFile(String fullPath, String relPath) throws IOException {
        String jarPath = fullPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        JarFile jarFile = new JarFile(jarPath);

        ArrayList<Class<?>> classes = new ArrayList<>();

        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();

            if (entryName.startsWith(relPath) && entryName.length() > (relPath.length() + "/".length())) {
                String className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
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
