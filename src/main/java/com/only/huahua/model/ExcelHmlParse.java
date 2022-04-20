package com.only.huahua.model;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;


/**
 * @description: ExcelHmlParse
 * @author: leiming5
 * @date: 2022/2/23 12:24
 */
public class ExcelHmlParse {


    public XSSFRichTextString parseHtmlStrToRichText(String htmlStr) {
        Document document = parseHtmlStrToDocument(htmlStr);
        XSSFRichTextString rts = parseDocementToRichText(document.getChildNodes().item(0));
        return rts;
    }

    private Document parseHtmlStrToDocument(String content) {
        content = "<content>" + content + "</content>";//html的换行标签变为excel的换行
        content = content.replaceAll("<br/>", "\n");
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        Document document = null;
        try {
            documentBuilder = documentbuilderfactory.newDocumentBuilder();
            document = documentBuilder.parse(new ByteArrayInputStream(content.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    private XSSFRichTextString parseDocementToRichText(Node contentNode) {
        String wholeTextContent = contentNode.getTextContent();
        XSSFRichTextString rts = new XSSFRichTextString();
        rts.append(wholeTextContent);
        setRichText(rts, contentNode, 0, null);
        return rts;
    }

    private void setRichText(RichTextString rts, Node contentNode, int startIndex, XSSFFont parentFont) {
        if (contentNode.getNodeType() == Node.TEXT_NODE) {
            return;
        } else {
            String textContent = contentNode.getTextContent();
            XSSFFont font = null;
            if (!contentNode.getNodeName().equals("content")) {
                font = getRichTextFontOfNode(contentNode, parentFont);
                rts.applyFont(startIndex, startIndex + textContent.length(), font);
            }
            NodeList nodeList = contentNode.getChildNodes();
            int fontLength = startIndex;
            String subTextContent = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                subTextContent = node.getTextContent();
                if (node.getNodeType() == Node.TEXT_NODE) {
                } else {
                    setRichText(rts, node, fontLength, font);
                }
                fontLength += subTextContent.length();
            }
        }
    }

    private XSSFFont getRichTextFontOfNode(Node node, XSSFFont parentFont) {
        XSSFFont font = new XSSFFont();
//        font.setFontName(FONT_NAME);
//        font.setFontHeightInPoints(RICH_FONT_SIZE_DEFAULT);
//        short fontHeight = RICH_FONT_SIZE_DEFAULT;
        short fontHeight = 10;
        if (parentFont != null) {
            fontHeight = parentFont.getFontHeightInPoints();
            font.setFontHeightInPoints(fontHeight);
            font.setColor(parentFont.getXSSFColor());
            font.setBold(parentFont.getBold());
            font.setUnderline(parentFont.getUnderline());
        }
        if ("span".equals(node.getNodeName())) {
            String className = node.getAttributes().getNamedItem("class").getNodeValue();
//            switch (className) {
//                case RICH_CSS_01: {
//                    font.setBold(true);
//                    break;
//                }
//                case RICH_CSS_02: {
//                    font.setUnderline(XSSFFont.U_SINGLE);
//                    break;
//                }
//                case RICH_CSS_03: {
//                    font.setFontHeightInPoints(RICH_FONT_SIZE_3);
//                    font.setBold(true);
//                    break;
//                }
//                case RICH_CSS_04: {
//                    int[] rgbColor = P2StringUtil.hexToRgb(RICH_ARGB_BLACK);
//                    XSSFColor xssfColor = new XSSFColor(new java.awt.Color(rgbColor[0], rgbColor[1], rgbColor[2]), new DefaultIndexedColorMap());
//                    font.setColor(xssfColor);
//                    break;
//                }
//                case RICH_CSS_05: {
//                    int[] rgbColor = P2StringUtil.hexToRgb(RICH_ARGB_RED);
//                    XSSFColor xssfColor = new XSSFColor(new java.awt.Color(rgbColor[0], rgbColor[1], rgbColor[2]), new DefaultIndexedColorMap());
//                    font.setColor(xssfColor);
//                    break;
//                }
//                case RICH_CSS_06: {
//                    int[] rgbColor = P2StringUtil.hexToRgb(RICH_ARGB_BLUE);
//                    XSSFColor xssfColor = new XSSFColor(new java.awt.Color(rgbColor[0], rgbColor[1], rgbColor[2]), new DefaultIndexedColorMap());
//                    font.setColor(xssfColor);
//                    break;
//                }
//            }
        } else if ("font".equals(node.getNodeName())) {
            int fontSize = Integer.parseInt(node.getAttributes().getNamedItem("size").getNodeValue());
//            switch (fontSize) {
//                case 1:
//                    fontHeight = RICH_FONT_SIZE_1;
//                    break;
//                case 2:
//                    fontHeight = RICH_FONT_SIZE_2;
//                    break;
//                case 3:
//                    fontHeight = RICH_FONT_SIZE_3;
//                    break;
//                case 4:
//                    fontHeight = RICH_FONT_SIZE_4;
//                    break;
//                case 5:
//                    fontHeight = RICH_FONT_SIZE_5;
//                    break;
//                case 6:
//                    fontHeight = RICH_FONT_SIZE_6;
//                    break;
//                default:
//                    fontHeight = RICH_FONT_SIZE_7;
//                    break;
//            }
            font.setFontHeightInPoints(fontHeight);
        }
        return font;
    }

    private String parseRichTextToHtmlStr(XSSFCell cell) {
        String htmlStr = "";
        String cellType = cell.getCellType().toString();
        if (!"STRING".equals(cellType)) {
//            htmlStr = InstructionUtil.getCellValueByCell(cell);
            return htmlStr;
        }
        XSSFRichTextString rts = cell.getRichStringCellValue();
        String wholeContentText = rts.getString();
        if (StringUtils.isEmpty(wholeContentText)) {
            return wholeContentText;
        }
        if (rts.numFormattingRuns() == 0) {
            XSSFCellStyle cellStyle = cell.getCellStyle();
            XSSFFont font = cellStyle.getFont();
            htmlStr = parseFontToHtmlStr(font, wholeContentText);
        } else {
            for (int i = 0; i < rts.numFormattingRuns(); i++) {
                String subContent = wholeContentText.substring(rts.getIndexOfFormattingRun(i), rts.getIndexOfFormattingRun(i) + rts.getLengthOfFormattingRun(i));
                XSSFFont font = rts.getFontAtIndex(rts.getIndexOfFormattingRun(i));
                if (!"".equals(subContent)) {
                    htmlStr += parseFontToHtmlStr(font, subContent);
                }
            }
        }
        return htmlStr;
    }

    private String parseFontToHtmlStr(XSSFFont font, String contentText) {
        String htmlStr = "";
        StringBuffer sb = new StringBuffer();
        XSSFColor color = font.getXSSFColor();
        String colorClass = null;
        boolean underlined = false;
        boolean isBold = font.getBold();
        if (color != null && color.getARGBHex() != null) {
//            switch (color.getARGBHex()) {
//                case RICH_ARGB_BLACK:
//                    colorClass = RICH_CSS_04;
//                    break;
//                case RICH_ARGB_RED:
//                    colorClass = RICH_CSS_05;
//                    break;
//                case RICH_ARGB_BLUE:
//                    colorClass = RICH_CSS_06;
//                    break;
//            }
        }
        if (Font.U_SINGLE == font.getUnderline()) {
            underlined = true;
        }
        short fontHeight = font.getFontHeightInPoints();
        short fontSize = 3;
        boolean isDefaultFontSize = false;
//        if (fontHeight <= RICH_FONT_SIZE_1) {
//            fontSize = 1;
//        } else if (fontHeight == RICH_FONT_SIZE_DEFAULT) {
//            isDefaultFontSize = true;
//        } else if (fontHeight <= RICH_FONT_SIZE_2) {
//            fontSize = 2;
//        } else if (fontHeight <= RICH_FONT_SIZE_3) {
//            fontSize = 3;
//        } else if (fontHeight <= RICH_FONT_SIZE_4) {
//            fontSize = 4;
//        } else if (fontHeight <= RICH_FONT_SIZE_5) {
//            fontSize = 5;
//        } else if (fontHeight <= RICH_FONT_SIZE_6) {
//            fontSize = 6;
//        } else {
//            fontSize = 7;
//        }
        if (colorClass != null) {
            sb.append("<span class=\"" + colorClass + "\">");
        }
//        if (underlined) {
//            sb.append("<span class=\"" + RICH_CSS_02 + "\">");
//        }
//        if (isBold) {
//            sb.append("<span class=\"" + RICH_CSS_01 + "\">");
//        }
        if (!isDefaultFontSize) {
            sb.append("<font size=\"" + fontSize + "\">");
        }        //excelの値の特殊文字（例：<span></span>など）をescape
//        contentText = StringEscapeUtils.escapeXml11(contentText);
        sb.append(contentText);
        if (!isDefaultFontSize) {
            sb.append("</font>");
        }
        if (isBold) {
            sb.append("</span>");
        }
        if (underlined) {
            sb.append("</span>");
        }
        if (colorClass != null) {
            sb.append("</span>");
        }
        htmlStr = sb.toString();
        htmlStr = htmlStr.replaceAll("\\R", "<br/>");
        return htmlStr;
    }
}
