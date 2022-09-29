package pruebas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {
	

	public static void main(String[] args) {
		
		
		BigDecimal a = new BigDecimal(5);
		BigDecimal b = new BigDecimal(3);
		
		System.out.println("Suma: " + a.add(b));
		System.out.println("Resta: " + a.add(b.negate()));
//		Date currentDate = new Date();
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
//		String formattedDate = format.format(currentDate);
//		
//		Double v = null;
//		try {
//			v = validarFormatoNumerico("5");
//		} catch (Exception e) {
//			System.out.println("ERROR: " + e.getMessage());
//			return;
//		}
//
//		System.out.println(v);
		
/*		int x = 0;
		
		while(++x < 5) { x+=1;  System.out.println(x);}
		String message = x > 5 ? "Greater than" : "Less Than";
		System.out.println(message+","+x);
	*/
	
		
		// redondeo();
		
		//Double aux = roundHalfUp(increaseAmount, 4);
	    //System.out.println(aux);
        
	    //System.out.println(roundHalfUp(46151.384999999995, 2));//((roundUp(increaseAmount, 2)));
	    //System.out.println(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	private static void redondeo() {
		
		List<String> aa = new ArrayList<>();
		
		double a = 43952.1;
		double b = 43953.00;
		double uno = Double.valueOf(a) * Integer.valueOf(5); //43952.1
		//System.out.println(uno);
		uno = uno / 100; 
		System.out.println("5% de " + a + " = " + uno + " -> " + roundHalfUp(uno, 2));
		uno = roundHalfUp(uno, 2);
		uno = uno + Double.valueOf(a); //2197.685
												//46151.384999999995
		System.out.println("1: " + uno);
		System.out.println();
		double tres = Integer.valueOf(5) * Double.valueOf(b); //System.out.println(tres);
		tres = tres / 100; 	//46151.384999999995 
		System.out.println("5% de " + b + " = " + tres + " -> " + roundHalfUp(tres, 2));
		tres = roundHalfUp(tres, 2);
		tres = tres + Double.valueOf(b);System.out.println("2: " + tres);
		
		//46151.384999999995
		System.out.println();
		double increaseAmount = (tres)
								-
								(uno);			
		System.out.println("Resultado " + increaseAmount);
		
		System.out.println("Redondeado: " + roundHalfUp(increaseAmount, 2));
		
		System.out.println(); System.out.println();
		System.out.println(a + " - " + b + " = " + (a-b) );
		System.out.println((a-b) + " *5% = " + ((a-b) * 5 / 100));
		System.out.println(roundHalfUp(roundHalfUp((a-b),2) + roundHalfUp(((a-b) * 5 / 100), 2), 2));
	}
	
//	public static Double roundingUp(Double value) {
//		BigDecimal bd = new BigDecimal(value);
//		bd = bd.setScale(2, RoundingMode.UP);
//		return bd.doubleValue();
//	}
//	public static BigDecimal roundUp(double value) {
//		BigDecimal bd = new BigDecimal(Double.toString(value));
//		bd = bd.setScale(2, RoundingMode.HALF_UP);
//		return bd;
//	}
	
	public static double roundHalfUp(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        double value = bd.doubleValue();
        return value;
    }
	
	public static double roundUp(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_UP);
        return bd.floatValue();
    }
	
	private static Double validarFormatoNumerico(String amount) {
		amount = amount.substring(0, amount.length()-2) + "." + amount.substring(amount.length() - 2);
		amount = amount.replaceAll("^0+", "");
		return Double.valueOf(amount);
	}
	
	public void ordenarListaObjetos() {
		List<Algo> lista = new ArrayList<>();
		lista.add(new Algo("Que tal", 2));
		lista.add(new Algo("Hola", 1));
		lista.add(new Algo("Adios", 3));
		
		List<Algo> listaOrdenada = lista.stream().sorted((Algo a1, Algo a2)-> a2.getNum() - a1.getNum()).collect(Collectors.toList());
		
		listaOrdenada.stream().forEach(a -> System.out.println(a.getNum() + " -> " + a.getName()));
//		List<Algo> listaOrdenada = lista.stream().sorted(new Comparator<Algo>() {
//			@Override
//			public int compare(Algo a1, Algo a2) {
//				return a2.getNum() - a1.getNum();
//			}
//		}).collect(Collectors.toList());
	}
	
}


class Algo {
	
	private String name;
	private int num;
	
	public Algo(String name, int num) {
		this.name = name;
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
