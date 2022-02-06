package com.alidasoftware.pos.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.alidasoftware.pos.bean.ApplicationBean;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Persona;

public class Utils implements Serializable {

	private static final long serialVersionUID = -7476168688469281659L;

	public static Object getManagedBean(String beanName) {
		Object o = getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());
		return o;
	}
	
	private static String getJsfEl(String value) {
		return "#{" + value + "}";
	}
	
	private static ValueBinding getValueBinding(String el) {
		return getApplication().createValueBinding(el);
	}
	
	private static Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory)FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
		return appFactory.getApplication();
	}
	
	public static String getFolio(String module, String postfix) {
		String result = "";
		SimpleDateFormat format = new SimpleDateFormat(ApplicationBean.PATTERN_DATE);
		result = format.format(new Date());		
		result = result.substring(0, 15) + result.substring(18, result.length());
		return module + result + postfix;
	}
	
	public static Date getExpirationDate(Date recordDate, int days, int typeDay) throws AlidaPosException {
		List<Date> holidays = ApplicationBean.getHolidays();
		DateTime day = new DateTime(recordDate);
		int cnt = 0;
		int weekday = 0;
		int typeBusiness = Integer.parseInt( ApplicationBean.TYPE_DAY_BUSINESS );
		int typeCalendar = Integer.parseInt( ApplicationBean.TYPE_DAY_CALENDAR );
		while (cnt < days) {
			day = day.plusDays(1);
			weekday = day.getDayOfWeek();
			if (( weekday >= 1 && weekday <=5 ) && typeDay == typeBusiness) {
				cnt++; 
			} else if (typeDay == typeCalendar) {
				cnt++;
			}
		}
		return day.toDate();
	}
	
	public static Boolean checkDate(Date startDate, Date endDate, Date checkDate) { 
	    Interval interval = new Interval(new DateTime(startDate), new DateTime(endDate));   
	    return interval.contains(new DateTime(checkDate)); 
	}
	
	public static List<Cliente> mergeClientPersonList(List<Cliente> clientList, List<Persona> personList) {
		if (clientList == null) {
			clientList = new ArrayList<Cliente>();
		}
		Cliente client;
		boolean exists = false;
		for (int i = 0; i < personList.size(); i++) {
			client = personList.get(i).getClientes().get(0);
			for (int j = 0; j < clientList.size(); j++) {
				if (client.getIdcliente() == clientList.get(j).getIdcliente()) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				clientList.add(client);
			}
		}
		return clientList; 
	}

	/**
	 * Se  calcula la lista de distancias entre un objetivo y una lista de cadenas
	 * @param stringlist es  la lista de cadenas en donde se buscará
	 * @param targetString la cadena que estamos buscando
	 * @return una lista de distancias calculada
	 */
	public List<Integer> getDistanceListFromStringArray(List<String> stringlist, String targetString){
		List<Integer> distanceList = new ArrayList<Integer>();
        //recorremos la lista de cadenas y calculamos distancias
        Iterator<String> iteratorString = stringlist.iterator();
        while(iteratorString.hasNext())
        	distanceList.add(stringsDistance(targetString, iteratorString.next(),false));             
		return distanceList;
	}
	
	/**
	 * Distancia entre dos cadenas - Levenshtein Distance
	 * http://en.wikipedia.org/wiki/Levenshtein_distance
	 * @param s0 cadena 1
	 * @param s1 cadena 2
	 * @return distance between the two strings
	 */
	
    public static int stringsDistance(String str1,String str2, Boolean caseSensitive) {      
		if (caseSensitive == false) {
			str1 = str1.toLowerCase();
			str2 = str2.toLowerCase();
		}
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];        
 
        for (int i = 0; i <= str1.length(); i++)                                 
            distance[i][0] = i;                                                  
        for (int j = 1; j <= str2.length(); j++)                                 
            distance[0][j] = j;                                                  
 
        for (int i = 1; i <= str1.length(); i++)                                 
            for (int j = 1; j <= str2.length(); j++)                             
                distance[i][j] = minimum(                                        
                        distance[i - 1][j] + 1,                                  
                        distance[i][j - 1] + 1,                                  
                        distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
 
        return distance[str1.length()][str2.length()];                           
    }  
    /**
     * Calcula el mínimo de 3 números, usada en la funcion de ditancia
     * @param a
     * @param b
     * @param c
     * @return minimo valor entre a,b,c
     */
    private static int minimum(int a, int b, int c) {                            
        return Math.min(Math.min(a, b), c);                                      
    }         
	
}
