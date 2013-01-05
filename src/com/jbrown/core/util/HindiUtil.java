//package com.jbrown.core.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.xerces.impl.xpath.regex.RegularExpression;
//
//public class HindiUtil {
//
//	private String _lang;
//	private String _halfchar;
//	private Map<String, String> _chars;
//	private Map<String, String> _ivowels;
//	private Map<String, String> _specs;
//
//	public HindiUtil() {
//		_lang = "Hindi";
//		_halfchar = "&#2381;";
//
//		_chars = new HashMap<String, String>();
//		_chars.put("k", "&#2325;");
//		_chars.put("c", "&#2325;");
//		_chars.put("kh", "&#2326;");
//		_chars.put("g", "&#2327;");
//		_chars.put("gh", "&#2328;");
//		_chars.put("ch", "&#2330;");
//		_chars.put("Ch", "&#2331;");
//		_chars.put("chh", "&#2331;");
//		_chars.put("j", "&#2332;");
//		_chars.put("jh", "&#2333;");
//		_chars.put("z", "&#2333;");
//		_chars.put("T", "&#2335;");
//		_chars.put("Th", "&#2336;");
//		_chars.put("D", "&#2337;");
//		_chars.put("Dh", "&#2338;");
//		_chars.put("N", "&#2339;");
//		_chars.put("t", "&#2340;");
//		_chars.put("th", "&#2341;");
//		_chars.put("d", "&#2342;");
//		_chars.put("dh", "&#2343;");
//		_chars.put("n", "&#2344;");
//		_chars.put("p", "&#2346;");
//		_chars.put("ph", "&#2347;");
//		_chars.put("f", "&#2347;");
//		_chars.put("b", "&#2348;");
//		_chars.put("bh", "&#2349;");
//		_chars.put("m", "&#2350;");
//		_chars.put("y", "&#2351;");
//		_chars.put("r", "&#2352;");
//		_chars.put("l", "&#2354;");
//		_chars.put("L", "&#2355;");
//		_chars.put("v", "&#2357;");
//		_chars.put("w", "&#2357;");
//		_chars.put("sh", "&#2358;");
//		_chars.put("Sh", "&#2359;");
//		_chars.put("shh", "&#2359;");
//		_chars.put("s", "&#2360;");
//		_chars.put("h", "&#2361;");
//		_chars.put("ksh", "&#2325;&#2381;&#2359;");
//		_chars.put("a", "null");
//		_chars.put("aa", "&#2366;");
//		_chars.put("A", "&#2366;");
//		_chars.put("i", "&#2367;");
//		_chars.put("ii", "&#2368;");
//		_chars.put("I", "&#2368;");
//		_chars.put("ee", "&#2368;");
//		_chars.put("u", "&#2369;");
//		_chars.put("uu", "&#2370;");
//		_chars.put("U", "&#2370;");
//		_chars.put("oo", "&#2370;");
//		_chars.put("e", "&#2375;");
//		_chars.put("ai", "&#2376;");
//		_chars.put("o", "&#2379;");
//		_chars.put("au", "&#2380;");
//		_chars.put("M", "&#2306;");
//		_chars.put("H", "&#2307;");
//		_chars.put("0", "&#2406;");
//		_chars.put("1", "&#2407;");
//		_chars.put("2", "&#2408;");
//		_chars.put("3", "&#2409;");
//		_chars.put("4", "&#2410;");
//		_chars.put("5", "&#2411;");
//		_chars.put("6", "&#2412;");
//		_chars.put("7", "&#2413;");
//		_chars.put("8", "&#2414;");
//		_chars.put("9", "&#2415;");
//
//		_ivowels.put("a", "&#2309;");
//		_ivowels.put("aa", "&#2310;");
//		_ivowels.put("A", "&#2310;");
//		_ivowels.put("i", "&#2311;");
//		_ivowels.put("ii", "&#2312;");
//		_ivowels.put("I", "&#2312;");
//		_ivowels.put("ee", "&#2312;");
//		_ivowels.put("u", "&#2313;");
//		_ivowels.put("uu", "&#2314;");
//		_ivowels.put("U", "&#2314;");
//		_ivowels.put("oo", "&#2314;");
//		_ivowels.put("e", "&#2319;");
//		_ivowels.put("ai", "&#2320;");
//		_ivowels.put("o", "&#2323;");
//		_ivowels.put("au", "&#2324;");
//		_ivowels.put("aM", "&#2309;&#2306;");
//		_ivowels.put("aH", "&#2309;&#2307;");
//
//		_specs.put("OM", "&#2384;");
//		_specs.put("OM", "&#2384;");
//		_specs.put("g.n", "&#2327;&#2306;");
//		_specs.put("D.N", "&#2337;&#2305;");
//		_specs.put(".n", "&#2306;");
//		_specs.put(".N", "&#2305;");
//	}
//
//	public String updateViewBox() {
//	 //var fm = document.getElementById('fm');
//	 String html = "";
//	 String defText = "[Your message will appear here]";
//	 if(true) {
//	 String text = "Raaja Khaan";
//	
//	 String[] out = new String[0];
//	 char ch = ' ';
//	 char ch2 = ' ';
//	 char ch3 = ' ';
//	 int i;
//	 int nextwordstart = 0;
//	 String nextword = "";
//	 String textleft = text;
//	 char lastch = ' ';
//	 for(i = 0; i < text.length;i++) {
//	 textleft = text.substring(i);
//	 ch = text.charAt(i);
//	 if(ch == '\r') {
//	 continue;
//	 } else if(ch == '\n') {
//	 lastch = ch;
//	 out[out.length] = "<br />";
//	 continue;
//	 }
//	 ch2 = ' ';
//	 ch3 = ' ';
//	 char ucode = ' ';
//	 int wordstart = nextwordstart;
//	 if(nextwordstart == i) {
//	 if(textleft.matches("/^([a-zA-Z]+)/")) {
//	 nextword = 1;//RegularExpression.$1;
//	 if(words[nextword]) {a
//	 ucode = words[nextword];
//	 nextwordstart = i + nextword.length;
//	 i += nextword.length - 1;
//	 lastch = ' ';
//	 } else {
//	 //if(!checked[nextword] && (nextword.length > 1)) {
//	 // checkword(nextword);
//	 //}
//	 nextwordstart = i + nextword.length;
//	 }
//	 } else {
//	 var wordboundary = ' ';
//	 if(textleft.match("/^([^a-zA-Z]+)/")) {
//	 wordboundary = RegExp.$1;
//	 }
//	 nextwordstart += wordboundary.length ? wordboundary.length : 1;
//	 }
//	 }
//	 if(ucode == ' ') {
//	 if(i < text.length) {
//	 ch2 = ch + text.charAt(i+1);
//	 if(i < text.length-2) {
//	 ch3 = ch2 + text.charAt(i+2);
//	 }
//	 }
//	
//	 String val = (((wordstart == i) || ivowels[lastch]) && ivowels[ch]) ?
//	 ivowels[ch] : (chars[ch] ? chars[ch] : chars[ch.toLowerCase()]);
//	 var val2 = (((wordstart == i) || ivowels[lastch]) && ivowels[ch2]) ?
//	 ivowels[ch2] : (specs[ch2] ? specs[ch2] : chars[ch2]);
//	 var val3 = (typeof chars[ch3] == 'string') ? chars[ch3] : '';
//	 var thech = val3 ? ch3 : (val2 ? ch2 : ch);
//	 var thechlen = thech.length;
//	 var theval = val3 ? val3 : (val2 ? val2 : val);
//	 if(chars[lastch] && !ivowels[lastch] && chars[thech] && !ivowels[thech]
//	 && !lastch.match(/\d/) && !specs[lastch] && !specs[thech] &&
//	 !thech.match(/\d|M|H/)) {
//	 theval = halfchar+theval; // half the letter.
//	 }
//	 lastch = thech;
//	 if(theval) {
//	 ucode = theval;
//	 i += thech.length - 1;
//	 } else {
//	 code = text.charCodeAt(i);
//	 }
//	 }
//	 if(ucode == '') {
//	 ucode = '&#'+code+';';
//	 }
//	 if(ucode == 'null') {
//	 ucode = '';
//	 }
//	
//	 if(0 && ch == 'i') {
//	 if(i > 0) {
//	 var tmp = out[out.length-1];
//	 out[out.length-1] = ucode;
//	 ucode = tmp;
//	 }
//	 }
//	 out[out.length] = ucode;
//	 }
//	 var ob = document.getElementById('viewbox');
//	 if(ob) {
//	 for(i = 0;i< out.length;i++) {
//	 html += out[i];
//	 }
//	 if (text == '') {
//	 html = defText;
//	 }
//	 ob.innerHTML = html;
//	 }
//	 }
//	 return html;
//	 }
// }
