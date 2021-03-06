package tw.Utils;

import com.google.common.collect.Lists;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RnkeyUtils {

    private final static LinkedList<String> keyPool = Lists.newLinkedList();
    private final static Pattern RNKEY_HTML_PATTERN = Pattern.compile("(.*)(function leastFactor\\(n\\).*)(function go\\(\\) \\{ )(.*)(n=leastFactor\\(p\\);\\{)(.*?=)(.*?;)(.*)");
    private static ScriptEngine javaScriptEngine = null;

    public static String nextKey() {
        int next = new Random().nextInt(keyPool.size());
        return keyPool.get(next);
    }

    static {
        keyPool.add("2832329*4704551:4259017186:1074632092:1");
        keyPool.add("4022903*5367977:4063241930:1340522969:1");
        keyPool.add("3864089*4174873:1760808845:3575982077:1");
        keyPool.add("2917037*2937757:335342078:2921989523:1");
        keyPool.add("3221899*3808327:341031270:2844087510:1");
        keyPool.add("2689601*2698321:1520587235:3883226924:1");
        keyPool.add("4168741*5061857:2900222849:285928235:1");
        keyPool.add("4524607*6147131:233016430:2956232214:1");
        keyPool.add("4290823*5007791:1494207740:3839410404:1");
        keyPool.add("7557167*8225929:3185077567:1013941:1");
        keyPool.add("7557167*8225929:3185077567:1013941:1");
        keyPool.add("3820153*4708381:2314890721:875394895:1");
        keyPool.add("3035059*3728293:2643426132:542789035:1");
        keyPool.add("3221899*3808327:341031270:2844087510:1");
        keyPool.add("2903893*4159403:2471765288:780352483:1");

        ScriptEngineManager factory = new ScriptEngineManager();
        javaScriptEngine = factory.getEngineByName("JavaScript");
    }

    public static void genRnKey(String html) throws ScriptException {
        Matcher htmlMatcher = RNKEY_HTML_PATTERN.matcher(html);
        if(htmlMatcher.find()) {
            String functionP = htmlMatcher.group(4);
            String functionF = htmlMatcher.group(2);
            String functionRNKEY = htmlMatcher.group(7);
            String p = javaScriptEngine.eval(functionP).toString();

            String rnkey = functionRNKEY
                    .replace("p", p)
                    .replace("n", javaScriptEngine.eval(functionF+"leastFactor(" + p +")").toString());

            if(keyPool.size() !=0 && keyPool.size() > 20) {
                keyPool.removeLast();
            }

            keyPool.push(javaScriptEngine.eval(rnkey).toString().replace("RNKEY=", ""));
        }
//        String functionP =  html.replaceAll("(.*)(function leastFactor\\(n\\).*)(function go\\(\\) \\{ )(.*)(n=leastFactor\\(p\\);\\{)(.*?=)(.*?;)(.*)", "$4");
//        String functionF =  html.replaceAll("(.*)(function leastFactor\\(n\\).*)(function go\\(\\) \\{ )(.*)(n=leastFactor\\(p\\);\\{)(.*?=)(.*?;)(.*)", "$2");
//        String functionRNKEY =  html.replaceAll("(.*)(function leastFactor\\(n\\).*)(function go\\(\\) \\{ )(.*)(n=leastFactor\\(p\\);\\{)(.*?=)(.*?;)(.*)", "$7");
    }
}
