package br.com.matheuscruz.projetomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static String decodeParam(String url) {
		try {
			return URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Integer> decodeIntList(String url) {

		return Arrays.asList(url.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());

	}

}
