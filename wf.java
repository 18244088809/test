

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class wf {
	public static void main(String args[]) throws InterruptedException {
		
		if ("-s".equals(args[0])){
			String path = "D:/redirect/" + args[1] + ".txt";
			try {
				textCount(path);
			} catch (Exception ex) {
				System.out.println("请重新输入");
			}
		}else if ("-f".equals(args[0])){
			
			String path1 = args[1];
			File file = new File(path1);
			if (file.isDirectory()) {
				File[] filelist = file.listFiles();
				for (File filePath : filelist) {
					try {
						String s = filePath.getPath();
						System.out.println(filePath.getName());
						textCount(s);
					} catch (Exception ex) {
						System.out.println("请重新输入");
					}
				}
			}
		}else if("-d".equals(args[0])){
			
			try {
					TxtCount();
				} catch (Exception ex) {
					System.out
							.println("请重新输入");
				}
		}
	
	}
		
	public static void textCount(String path) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(path));
		List<String> list = new ArrayList<String>(); 
		String sLine = null;
		while ((sLine = br.readLine()) != null) {
			String[] wordsArr = sLine.split("[^a-zA-Z]"); 
			for (String word : wordsArr) {
				String wordLower = word.toLowerCase();

				if (wordLower.length() != 0) { // 去除长度为0的行
					list.add(wordLower);
				}
			}
		}
		br.close();
		WordCount(list); 
	}

	public static void TxtCount() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> list = new ArrayList<String>(); 
		String readLine = null;
		while ((readLine = br.readLine()) != null) {
			String[] wordsArr = readLine.split("[^a-zA-Z]"); 
			for (String word : wordsArr) {
				if (word.length() != 0) { 
					list.add(word);
				}
			}
		}
		br.close();
		WordCount(list); 
	}


	public static void WordCount(List<String> list) throws InterruptedException {
		Map<String, Integer> wordsCount = new TreeMap<String, Integer>(); 
		
		for (String i : list) {
			if (wordsCount.get(i) != null) {
				wordsCount.put(i, wordsCount.get(i) + 1);
			} else {
				wordsCount.put(i, 1);
			}
		}
		System.out.println("-----");
		System.out.println("total" + "	" + wordsCount.size() + " words\n");
		SortMap(wordsCount); 
		System.out.println("-----");
	}

	public static void SortMap(Map<String, Integer> oldmap) throws InterruptedException {
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				oldmap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1,Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue(); 
			}
		});
		if (list.size()<10){
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getKey() + "	" + list.get(i).getValue());
		}
		}else {
			for (int i = 0; i < 10; i++) {
				System.out.println(list.get(i).getKey() + "	" + list.get(i).getValue());
		}
		}
	}
}

