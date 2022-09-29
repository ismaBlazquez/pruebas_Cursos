package ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MultiplicarFichero {

	public static void main(String[] args) {
		//File fileR = new File("/home/ismael.blazquez/Documentos/multiplicarFichero/MTF_D_INTERFAZ_ETL_FSA_D220620_1.dat");
		//File fileW = new File("/home/ismael.blazquez/Documentos/multiplicarFichero/MTF_D_INTERFAZ_ETL_FSA_D220620_2.dat");
		
		int n = 10000;
		
		for (int i = 0; i < n; i++) {
			crearNuevoFichero(new File("/home/ismael.blazquez/Documentos/multiplicarFichero/"));
		}	

	}

	private static void crearNuevoFichero(File folder) {
		//.sorted(Comparator.comparing(File::getName)).
		List<File> filesList = Arrays.stream(folder.listFiles()).filter(File::isFile).collect(Collectors.toList());
		
		Map<Integer, String> filesMap = new HashMap<>();
		for(File file : filesList) {
			String fileName = file.getName();
			String fileNameRep = fileName.replace('.', '_');
			String[] split = fileNameRep.split("_");
			int number = Integer.parseInt(split[(split.length - 2)]);
			filesMap.put(number, fileName);
		}
		Map<Integer, String> filesMapOrder = new TreeMap<>(filesMap);

		Integer[] array = filesMapOrder.keySet().toArray(new Integer[0]);

		int number = array[array.length-1];
		String lastFileName = filesMapOrder.get(number);
		String lastFileNameRep = lastFileName.replace('.', '_');
		String[] split = lastFileNameRep.split("_");

		number++;
		StringBuilder nameBuilder = new StringBuilder();
		//System.out.println("NUEVO: " + number);
		for(int i = 0; i < split.length; i++) {
			if(i == (split.length - 2)) {
				nameBuilder.append(number).append(".");
			}else if (i == (split.length - 1)) {
				nameBuilder.append(split[i]);
			}else {
				nameBuilder.append(split[i]).append("_");
			}
		}
		
		//LECTURA
		StringBuilder sb = lecturaFichero(new File(folder.getAbsolutePath() + "/" + lastFileName));	
			
		//ESCRITURA
		System.out.println(folder.getAbsolutePath() + "/" + nameBuilder.toString());
		escrituraFichero(new File(folder.getAbsolutePath() + "/" + nameBuilder.toString()), sb);
	}

	private static void escrituraFichero(File fileW, StringBuilder sb) {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(fileW);
			pw = new PrintWriter(fw);
			
			pw.print(sb);
		}catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		} finally {
			if(pw != null) {
				pw.close();
			}
		}
	}

	private static StringBuilder lecturaFichero(File file) {
		FileReader fr = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			
			fr = new FileReader(file);
			br = new BufferedReader(fr);	
			
			int i = 0;
			
			String line = br.readLine();
			
			while (line != null) {
				if(i == 0) {
					StringBuilder lineBuilder = new StringBuilder();
					
					String[] split = line.split(";");
					String substringNumber = split[0].substring(1);
					String substringFirst = split[0].substring(0, 1);
					
					int parseInt = Integer.parseInt(substringNumber);
					
					lineBuilder.append(substringFirst).append(++parseInt).append(";");
					for(int j = 1; j < split.length; j++) {
						lineBuilder.append(split[j]);
						if(j < (split.length - 1)) {
							lineBuilder.append(";");
						}
					}
					line = lineBuilder.toString();
				}
				sb.append(line).append("\n");
				line = br.readLine();
				i++;
			}
			
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fr != null){   	
					fr.close();    
				} 
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb;
	}

}
