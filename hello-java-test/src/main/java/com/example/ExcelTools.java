package com.example;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;

import java.util.Map;
import java.util.StringJoiner;

public class ExcelTools {

    public static void main(String[] args) {

        String fileName = "C:\\Users\\Gmw\\Desktop\\草稿.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        ExcelReaderBuilder reader = EasyExcel.read(fileName, ColumnRow.class, new AnalysisEventListener<ColumnRow>() {

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.printf(") comment '';%n");
            }

            @Override
            public void invoke(ColumnRow data, AnalysisContext context) {
                // System.out.println(data.print(context.readSheetHolder().getSheetName()));
                System.out.printf("  %s %s %s comment '%s',%n", handleColumn(data),
                                  handleType(data), handleRequired(data), handleComment(data));
            }

            @Override
            public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
                System.out.printf("create table if not exists %s (%n", context.readSheetHolder().getSheetName());
            }
        });
        reader.sheet(5).doRead();
        System.out.println("=============");
    }

    private static String handleColumn(ColumnRow data) {
        return data.column;
    }

    private static String handleType(ColumnRow data) {
        if (data.type.startsWith("DATE")) {
            return "datetime";
        }
        if (data.type.startsWith("STRING")) {
            return "varchar(240)";
        }
        if (data.type.startsWith("NUMBER")) {
            return "int";
        }
        if (data.type.startsWith("VARCHAR2")) {
            return data.type.replace("VARCHAR2", "varchar");
        }
        throw new RuntimeException("类型为空");
    }

    private static String handleRequired(ColumnRow data) {
        if (data.required.equals("Y")) {
            return "not null";
        }
        return "null";
    }

    private static String handleComment(ColumnRow data) {
        String comment = "";
        if (data.name != null && !"".equals(data.name.trim())) {
            comment += data.name;
        }
        if (data.desc != null && !"".equals(data.desc.trim())) {
            comment += " - " + data.desc;
        }
        return comment;
    }

    public static class ColumnRow {
        @ExcelProperty(index = 0)
        private String required;
        @ExcelProperty(index = 1)
        private String column;
        @ExcelProperty(index = 2)
        private String name;
        @ExcelProperty(index = 3)
        private String type;
        @ExcelProperty(index = 4)
        private String desc;

        public String print(String tableName) {
            return new StringJoiner(", ", tableName + "[", "]")
                    .add("required='" + required + "'")
                    .add("column='" + column + "'")
                    .add("name='" + name + "'")
                    .add("type='" + type + "'")
                    .add("desc='" + desc + "'")
                    .toString();
        }

        public String getRequired() {
            return required;
        }

        public void setRequired(String required) {
            this.required = required;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
