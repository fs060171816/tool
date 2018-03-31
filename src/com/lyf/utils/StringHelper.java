package com.lyf.utils;
public class StringHelper {

	public static String getClassName(String sqlName, String prefixs) {

		String removedPrefixSqlName = removeTableSqlNamePrefix(sqlName, prefixs);
		return makeAllWordFirstLetterUpperCase(toUnderscoreName(removedPrefixSqlName));

	}

	public static String removeTableSqlNamePrefix(String sqlName, String prefixs) {
		if (null == prefixs || "".equals(prefixs)) {
			return sqlName;
		}
		String arr$[] = prefixs.split(",");
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			String prefix = arr$[i$];
			String removedPrefixSqlName = removePrefix(sqlName, prefix, true);
			if (!removedPrefixSqlName.equals(sqlName))
				return removedPrefixSqlName;
		}

		return sqlName;
	}

	public static String removePrefix(String str, String prefix,
			boolean ignoreCase) {
		if (str == null)
			return null;
		if (prefix == null)
			return str;
		if (ignoreCase) {
			if (str.toLowerCase().startsWith(prefix.toLowerCase()))
				return str.substring(prefix.length());
		} else if (str.startsWith(prefix))
			return str.substring(prefix.length());
		return str;
	}

	public static String toUnderscoreName(String name) {
		if (name == null)
			return null;
		String filteredName = name;
		if (filteredName.indexOf("_") >= 0
				&& filteredName.equals(filteredName.toUpperCase()))
			filteredName = filteredName.toLowerCase();
		if (filteredName.indexOf("_") == -1
				&& filteredName.equals(filteredName.toUpperCase()))
			filteredName = filteredName.toLowerCase();
		StringBuffer result = new StringBuffer();
		if (filteredName != null && filteredName.length() > 0) {
			result.append(filteredName.substring(0, 1).toLowerCase());
			for (int i = 1; i < filteredName.length(); i++) {
				String preChart = filteredName.substring(i - 1, i);
				String c = filteredName.substring(i, i + 1);
				if (c.equals("_")) {
					result.append("_");
					continue;
				}
				if (preChart.equals("_")) {
					result.append(c.toLowerCase());
					continue;
				}
				if (c.matches("\\d")) {
					result.append(c);
					continue;
				}
				if (c.equals(c.toUpperCase())) {
					result.append("_");
					result.append(c.toLowerCase());
				} else {
					result.append(c);
				}
			}

		}
		return result.toString();
	}

	public static String makeAllWordFirstLetterUpperCase(String sqlName) {
		String strs[] = sqlName.toLowerCase().split("_");
		String result = "";
		String preStr = "";
		for (int i = 0; i < strs.length; i++) {
			if (preStr.length() == 1)
				result = (new StringBuilder()).append(result).append(strs[i])
						.toString();
			else
				result = (new StringBuilder()).append(result)
						.append(capitalize(strs[i])).toString();
			preStr = strs[i];
		}

		return result;
	}

	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	private static String changeFirstCharacterCase(String str,
			boolean capitalize) {
		if (str == null || str.length() == 0)
			return str;
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize)
			buf.append(Character.toUpperCase(str.charAt(0)));
		else
			buf.append(Character.toLowerCase(str.charAt(0)));
		buf.append(str.substring(1));
		return buf.toString();
	}
}
