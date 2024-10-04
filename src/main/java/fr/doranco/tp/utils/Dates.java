package fr.doranco.tp.utils;

import java.text.SimpleDateFormat;

public final class Dates {

	private Dates() {
	}
	
	private static final String formatDate = "dd/MM/yyyy"; 
	
	public static final java.util.Date convertStringToDate(String dateStr) throws Exception {
		return (new SimpleDateFormat(formatDate)).parse(dateStr);
	}

	public static final String convertDateToString(java.util.Date date) throws Exception {
		return (new SimpleDateFormat(formatDate)).format(date);
	}

	public static final java.sql.Date convertDateUtilToDateSql(java.util.Date utilDate) {
		return new java.sql.Date(utilDate.getTime());
	}

	public static final java.util.Date convertDateSqlToDateUtil(java.sql.Date sqlDate) {
		return new java.util.Date(sqlDate.getTime());
	}

}
