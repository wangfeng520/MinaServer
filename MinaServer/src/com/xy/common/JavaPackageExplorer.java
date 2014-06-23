package com.xy.common;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 包浏览器, 列出指定包及子包中所有类

 * 
 */
public class JavaPackageExplorer {
	private String name; // 包名

	public JavaPackageExplorer(String name) {
		this.name = name;
	}

	public List<String> listAllClasses() throws Exception {
		List<String> list = new ArrayList<String>();

		String s = name.replace('.', '/');
		for (Enumeration<URL> e = Thread.currentThread().getContextClassLoader().getResources(s); e.hasMoreElements();) {
			URL url = e.nextElement();
			if (url.getProtocol().equals("jar")) {
				JarURLConnection con = (JarURLConnection) url.openConnection();
				JarFile file = con.getJarFile();
				addToList(list, listAllClassesFromJarFile(file));
			}
			else {
				File file = new File(new URI(url.toExternalForm()));
				addToList(list, listAllClassesFromFileSystem(file));
			}
		}

		return list;
	}

	public List<String> listSubPackages() throws Exception {
		List<String> list = new ArrayList<String>();

		String s = name.replace('.', '/');
		for (Enumeration<URL> e = getClass().getClassLoader().getResources(s); e.hasMoreElements();) {
			URL url = e.nextElement();
			if (url.getProtocol().equals("jar")) {
				JarURLConnection con = (JarURLConnection) url.openConnection();
				JarFile file = con.getJarFile();
				list.addAll(listSubPackagesFromJarFile(file));
			}
			else {
				File file = new File(new URI(url.toExternalForm()));
				list.addAll(listSubPackagesFromFileSystem(file));
			}
		}

		return list;
	}

	private void addToList(List<String> container, List<String> items) {
		for (String item : items) {
			if (container.contains(item)) continue;
			container.add(item);
		}
	}

	private List<String> listSubPackagesFromJarFile(JarFile file) {
		List<String> list = new ArrayList<String>();
		for (Enumeration<JarEntry> je = file.entries(); je.hasMoreElements();) {
			JarEntry entry = je.nextElement();

			String s = entry.getName();
			if (s.indexOf('/', name.length()) < 0) continue;

			s = s.substring(0, s.length()).replace('/', '.');
			if (!s.startsWith(name)) continue;

			list.add(s);
		}

		return list;
	}

	private List<String> listSubPackagesFromFileSystem(File path) {
		List<String> list = new ArrayList<String>();

		int n = path.getAbsolutePath().length() - name.length();
		for (File file : path.listFiles()) {
			if (file.isDirectory()) {
				String s = file.getAbsolutePath().substring(n).replaceAll("[/\\\\]", ".");
				list.add(s);
			}
		}

		return list;
	}

	private List<String> listAllClassesFromJarFile(JarFile file) {
		List<String> list = new ArrayList<String>();
		for (Enumeration<JarEntry> je = file.entries(); je.hasMoreElements();) {
			JarEntry entry = je.nextElement();

			String s = entry.getName();
			if (!s.endsWith(".class")) continue;

			s = s.substring(0, s.length() - 6).replace('/', '.');
			if (!s.startsWith(name)) continue;

			list.add(s);
		}

		return list;
	}

	private List<String> listAllClassesFromFileSystem(File path) {
		List<String> list = new ArrayList<String>();

		int n = path.getAbsolutePath().length() - name.length();
		Stack<File> stack = new Stack<File>();

		stack.push(path);
		while (!stack.isEmpty()) {
			File file = stack.pop();

			if (!file.exists()) continue;

			if (file.isFile()) {
				String s = file.getAbsolutePath();
				if (!s.endsWith(".class")) continue;
				s = s.substring(n, s.length() - 6).replaceAll("[/\\\\]", ".");
				list.add(s);
				continue;
			}

			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					stack.push(files[i]);
				}
			}
		}

		return list;
	}
}
