package com.tgy.util;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleXmlSerializer;
import org.htmlcleaner.TagNode;
import org.jdom2.Document;
import org.jdom2.Element;

import com.google.gson.Gson;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;

public class UploadBookmarkUtil {
	
	public static Folder toFolder(String source){
		
		try {

			if(source==null) return null;
			
			source = source.replaceAll("<DT>", "");
			source = source.replaceAll("<dt>", "");
			source = source.replaceAll("<HR>", "");
			source = source.replaceAll("<hr>", "");
			source = source.replaceAll("<P>", "");
			source = source.replaceAll("<p>", "");
			source = source.replaceAll("<DD>", "");
			source = source.replaceAll("<dd>", "");
			
			HtmlCleaner hc = new HtmlCleaner();
			TagNode node = hc.clean(source);

			String cleanedSource = getPageSourceFromNode(node);

			//System.out.println(cleanedSource);
			//
			Document doc = new org.jdom2.input.SAXBuilder()
					.build(new StringReader(cleanedSource));
			//
			Folder parentFolder = new Folder();
			scan(doc.getRootElement(), parentFolder);
			
			return parentFolder;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		try {

			HtmlCleaner hc = new HtmlCleaner();
			// TagNode node = hc.clean(new File(
			// "f://bookmarksf.html"));

			String source = getFileContent("f://bookmarks_14-9-28.html");

			source = source.replaceAll("<DT>", "");
			source = source.replaceAll("<dt>", "");
			source = source.replaceAll("<HR>", "");
			source = source.replaceAll("<hr>", "");
			source = source.replaceAll("<P>", "");
			source = source.replaceAll("<p>", "");
			source = source.replaceAll("<DD>", "");
			source = source.replaceAll("<dd>", "");

			TagNode node = hc.clean(source);

			String cleanedSource = getPageSourceFromNode(node);

			System.out.println(cleanedSource);
			//
			Document doc = new org.jdom2.input.SAXBuilder()
					.build(new StringReader(cleanedSource));
			//
			Folder parentFolder = new Folder();
			scan(doc.getRootElement(), parentFolder);

			System.out.println(new Gson().toJson(parentFolder));
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void scan(Element element, Folder parentFolder) {

		if (element == null) {
			return;
		}

		if (StringUtils.equalsIgnoreCase(element.getName(), "dl")) {

			Element prev = prev(element);
			String title = "未分类";
			if (prev != null) {
				title = prev.getText();
			}

			Folder folder = new Folder();
			folder.name = title;

			for (Element a : element.getChildren("a")) {
				String name = a.getText();
				String link = "#";
				if (a.getAttribute("href") != null) {
					link = a.getAttribute("href").getValue();
				}

				if(StringUtils.isNotBlank(link) && link.startsWith("http")){
					Page page = new Page();
					page.name = name;
					page.url = link;
					folder.add(page);
				}
				
				
			}
			
			parentFolder.add(folder);

			for (Element e : element.getChildren()) {
				scan(e, folder);
			}

		} else {
			for (Element e : element.getChildren()) {
				scan(e, parentFolder);
			}
		}

	}

	public static Element prev(Element e) {
		if (e == null || e.getParentElement() == null) {
			return null;
		}
		Element tempE = null;
		for (Element subE : e.getParentElement().getChildren()) {

			if (!subE.equals(e)   ) { //&& (subE.getName().startsWith("h") || subE.getName().startsWith("H")  )
				tempE = subE;
			} else {
				break;
			}
		}
		return tempE;
	}

	public static String getPageSourceFromNode(TagNode node) {
		// long start = System.currentTimeMillis();
		HtmlCleaner hc = new HtmlCleaner();
		CleanerProperties props = hc.getProperties();
		props.setNamespacesAware(false);
		props.setOmitCdataOutsideScriptAndStyle(true);
		props.setOmitComments(true);
		props.setOmitXmlDeclaration(true);
		props.setOmitDoctypeDeclaration(true);
		// PrettyXmlSerializer SimpleXmlSerializer
		SimpleXmlSerializer serializer = new SimpleXmlSerializer(props);
		String pageSource = "";
		try {
			pageSource = serializer.getAsString(node, "UTF-8");
		} catch (Exception e) {
			// no thing
		}
		// pageSource = U.clean(pageSource);
		// L.trace("Connecter getPageSourceFromNode ", " finished, time is " +
		// (System.currentTimeMillis() -start));
		return pageSource;
	}

	public static String getFileContent(String path) {
		String fileContent = "";
		try {
			fileContent = IOUtils.toString(new FileInputStream(path), "UTF-8");
		} catch (Exception e) {

			return "";
		}
		return fileContent;
	}

}
