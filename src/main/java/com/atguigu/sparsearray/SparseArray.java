package com.atguigu.sparsearray;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SparseArray {


    public static void main(String[] args) throws Exception{
        int[][] sparseArrayFromFile = fuPan();


        System.out.println("复盘的稀疏数组:");
        printArray(sparseArrayFromFile);

        //稀疏数组转为 原始数组



        //创建一个原始的二维数组 11 * 11
        //0:表示没有棋子  1：表示黑子  2：表示蓝子
        int[][] chess = new int[sparseArrayFromFile[0][0]][sparseArrayFromFile[0][1]];
        for(int i=1;i<sparseArrayFromFile.length;i++){
            chess[sparseArrayFromFile[i][0]][sparseArrayFromFile[i][1]] =
                    sparseArrayFromFile[i][2];
        }
        /*chess[1][2]=1;
        chess[2][3]=2;*/

        //打印原始的二维数组
        //使用java的增强for循环
        System.out.println("原始的二维数组:");
        printArray(chess);

        //先遍历二维数组 得到非0数值的个数
        int num = 0;
        for (int[] arr : chess) {
            for (int data : arr) {
                if(data>0){
                    num++;
                }
            }
        }

        //构造稀疏数组
        int[][] sparseArray = new int[num+1][3];
        //第0行
        sparseArray[0][0] = chess.length;
        sparseArray[0][1] = chess[0].length;
        sparseArray[0][2] = num;
        //具体数值从第2行开始
        int index = 1;

        for (int i = 0; i < chess.length; i++) {
            for(int j=0; j<chess[i].length;j++){
                if(chess[i][j]>0){
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = chess[i][j];
                    index++;
                }
            }
        }

        System.out.println("稀疏数组:");
        printArray(sparseArray);

        //保存稀疏数组到文件中
        saveSparseArrayToFile(sparseArray);
    }

    private static int[][] fuPan() throws IOException {
        //复盘  复盘最新的  即 文件名称包含的数值最大的
        File dataDir = new File("data");
        File[] files = dataDir.listFiles();
        String sparseFileName = "data"+File.separator+files[files.length-1].getName();

        BufferedReader br = new BufferedReader(new FileReader(sparseFileName));
        String line = null;
        //读出数据转换为稀疏数组
        List<String> listData = new ArrayList<String>();
        while ((line=br.readLine()) != null){
            listData.add(line);
        }
        br.close();
        int[][] sparseArrayFromFile = new int[listData.size()][3];
        for(int i=0; i<listData.size(); i++){
            String oneData = listData.get(i);
            String[] fields = oneData.split(",");
            sparseArrayFromFile[i][0] = Integer.valueOf(fields[0]);
            sparseArrayFromFile[i][1] = Integer.valueOf(fields[1]);
            sparseArrayFromFile[i][2] = Integer.valueOf(fields[2]);
        }
        return sparseArrayFromFile;
    }

    private static void saveSparseArrayToFile(int[][] sparseArray) throws IOException {
        String dateStr = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        File dataDir = new File("data");
        if(!dataDir.isDirectory()){
            dataDir.mkdir();
        }
        File dataFile = new File("data/sparse_text" + dateStr);
        if(!dataFile.exists()){
            dataFile.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("data/sparse_text" + dateStr));
//        FileWriter fileWriter = new FileWriter(new File("data/sparse_text" + dateStr));
        for (int[] arr : sparseArray) {
            String line = "";
            for (int data : arr) {
                line = line + data + ",";
            }
            line = line.substring(0,line.length()-1);
//            fileWriter.write(line+"\n");
            bw.write(line);
            bw.newLine();
        }

        bw.close();
    }

    private static void printArray(int[][] chess) {
        for (int[] arr : chess) {
            for (int data : arr) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
