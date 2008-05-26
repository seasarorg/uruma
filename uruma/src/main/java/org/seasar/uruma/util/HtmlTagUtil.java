/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.uruma.util;

/**
 * HTMLタグの文字列を生成するユーティリティです。<br />
 * 
 * @author y.sugigami
 */
public class HtmlTagUtil {

    public static String createTable() {
        return "<table> ";
    }

    public static String createTable(final String key, final String value) {
        return "<table><tr><td class=\"header\" colspan=\"2\">" + key + " = "
                + value + "</td></tr>";
    }

    public static String closeTable() {
        return "</table> ";
    }

    public static String createTrSub1(final String key, final String value) {
        return "<tr><td class=\"sub_header\" colspan=\"2\">" + key + " = "
                + value + "</td></tr>";
    }

    public static String createTrSub2(final String key, final String value) {
        return "<tr><td class=\"sub_sub_header\" colspan=\"2\">" + key + " = "
                + value + "</td></tr>";
    }

    public static String createTr(final String td1, final String td2) {
        String tmp = "";
        tmp += "<tr>";
        tmp += "<th>";
        tmp += td1;
        tmp += "</td>";
        tmp += "<td>";
        tmp += td2;
        tmp += "</td>";
        tmp += "</td>";
        return tmp;
    }

    public static String createH1(final String value) {
        return "<h1>" + value + "</h1>";
    }

    public static String createH2(final String value) {
        return "<h2>" + value + "</h2>";
    }

    public static String createH3(final String value) {
        return "<h3>" + value + "</h3>";
    }

    public static String createHeader() {
        String header = "<html><head>\n";
        header += "<style type=\"text/css\">\n";
        header += "<!--\n";
        header += "h1 {font-size: 24px; padding: 2px; color: #ffffff; background-color: #666699; line-height: 130%;}\n";
        header += "h2 {font-size: 18px; padding: 2px; color: #ffffff; background-color: #7778AA; line-height: 130%;}\n";
        header += "h3 {font-size: 16px; padding: 2px; color: #ffffff; background-color: #9999CC; line-height: 130%;}\n";
        header += "\n";
        header += "table {font-size: 12px; color: #666666; border: 1px solid #CCCCCC; border-collapse: separate; border-spacing: 1px; margin-left: 10px;}\n";
        header += "th {padding: 5px; border-right: 1px solid #CCCCCC; border-bottom: 1px solid #CCCCCC; background-color: #E1E2E6; text-align: left;}\n";
        header += "td {padding: 5px; border-right: 1px solid #CCCCCC; border-bottom: 1px solid #CCCCCC; background-color: #EFF2F3;}\n";
        header += "td.header {font-weight: bold; font-size: 18px; padding: 5px; border-right: 1px solid #CCCCCC; border-bottom: 1px solid #CCCCCC; background-color: #666699; color: #ffffff;}\n";
        header += "td.sub_header {font-weight: bold; font-size: 12px; padding: 5px; border-right: 1px solid #CCCCCC; border-bottom: 1px solid #CCCCCC; background-color: #666699; color: #ffffff;}\n";
        header += "td.sub_sub_header {font-weight: bold; font-size: 12px; padding: 5px; border-right: 1px solid #CCCCCC; border-bottom: 1px solid #CCCCCC; background-color: #AAAADD; color: #ffffff;}\n";
        header += "\n";
        header += "-->\n";
        header += "</style>\n";
        header += "</head>\n";
        header += "<body>\n";

        return header;
    }

    public static String closeHeader() {
        return "</body></html>";
    }
}
