package com.mcb.iminitializr.constant;

import java.nio.charset.StandardCharsets;

public abstract class Constant {

    public static String UTF8 = StandardCharsets.UTF_8.name();

    /*----------------------------------Path--------------------------------*/

    public static final String JAVA_ROOT_PATH = "/src/main/java";
    public static final String RESOURCE_ROOT_PATH = "/src/main/resources";
    public static final String TEST_JAVA_ROOT_PATH = "/src/test/java";
    public static final String TEST_RESOURCE_ROOT_PATH = "/src/test/resources";
    public static final String TEST_PATH = "/test";
    public static final String XML_PATH = "/mapper";
    public static final String TEMPLATE_PATH = "/templates";

    public static final String GLOBAL_PATH = "/global";

    /*----------------------------------Template--------------------------------*/
    public static final String FREEMARKER_SUFFIX = ".ftl";
    public static final String APPLICATION_TEMPLATE = "application.java";
    public static final String PACKAGE_INFO_TEMPLATE = "package-info.java";
    public static final String TEST_TEMPLATE = "test.java";
    public static final String YML_TEMPLATE = "application.yml";
    public static final String POM_TEMPLATE = "pom.xml";
    public static final String GITIGNORE_TEMPLATE = ".gitignore";
    public static final String LOG4J2_TEMPLATE = "log4j2.xml";
    public static final String RESULT_TEMPLATE = "result.java";
    public static final String RESULT_ENUM_TEMPLATE = "resultenum.java";

    /*----------------------------------Java File--------------------------------*/
    public static final String JAVA_SUFFIX = ".java";
    public static final String APPLICATION_NAME = "Application";
    public static final String TEST_NAME = "ApplicationTests";
    public static final String RESULT_NAME = "Result";
    public static final String RESULT_ENUM_NAME = "ResultEnum";

    /*----------------------------------String--------------------------------*/

    public static final String AMPERSAND = "&";
    public static final String AND = "and";
    public static final String AT = "@";
    public static final String ASTERISK = "*";
    public static final String STAR = ASTERISK;
    public static final String BACK_SLASH = "\\";
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String DASH = "-";
    public static final String DOLLAR = "$";
    public static final String DOT = ".";
    public static final String DOTDOT = "..";
    public static final String DOT_CLASS = ".class";
    public static final String DOT_JAVA = ".java";
    public static final String DOT_XML = ".xml";
    public static final String EMPTY = "";
    public static final String EQUALS = "=";
    public static final String FALSE = "false";
    public static final String SLASH = "/";
    public static final String HASH = "#";
    public static final String HAT = "^";
    public static final String LEFT_BRACE = "{";
    public static final String LEFT_BRACKET = "(";
    public static final String LEFT_CHEV = "<";
    public static final String DOT_NEWLINE = ",\n";
    public static final String NEWLINE = "\n";
    public static final String N = "n";
    public static final String NO = "no";
    public static final String NULL = "null";
    public static final String NUM = "NUM";
    public static final String OFF = "off";
    public static final String ON = "on";
    public static final String PERCENT = "%";
    public static final String PIPE = "|";
    public static final String PLUS = "+";
    public static final String QUESTION_MARK = "?";
    public static final String EXCLAMATION_MARK = "!";
    public static final String QUOTE = "\"";
    public static final String RETURN = "\r";
    public static final String TAB = "\t";
    public static final String RIGHT_BRACE = "}";
    public static final String RIGHT_BRACKET = ")";
    public static final String RIGHT_CHEV = ">";
    public static final String SEMICOLON = ";";
    public static final String SINGLE_QUOTE = "'";
    public static final String BACKTICK = "`";
    public static final String SPACE = " ";
    public static final String SQL = "sql";
    public static final String TILDA = "~";
    public static final String LEFT_SQ_BRACKET = "[";
    public static final String RIGHT_SQ_BRACKET = "]";
    public static final String TRUE = "true";
    public static final String UNDERSCORE = "_";
    public static final String UTF_8 = "UTF-8";
    public static final String US_ASCII = "US-ASCII";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String Y = "y";
    public static final String YES = "yes";
    public static final String ONE = "1";
    public static final String ZERO = "0";
    public static final String DOLLAR_LEFT_BRACE = "${";
    public static final String HASH_LEFT_BRACE = "#{";
    public static final String CRLF = "\r\n";

    public static final String HTML_NBSP = "&nbsp;";
    public static final String HTML_AMP = "&amp";
    public static final String HTML_QUOTE = "&quot;";
    public static final String HTML_LT = "&lt;";
    public static final String HTML_GT = "&gt;";

    public static final String[] EMPTY_ARRAY = new String[0];
    byte[] BYTES_NEW_LINE = Constant.NEWLINE.getBytes();

}
