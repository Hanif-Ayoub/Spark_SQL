package ma.enset.tpsparksql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataType;

import  static org.apache.spark.sql.functions.col;
public class App2 {
    public static void main(String[] args) {
        SparkSession ss=SparkSession.builder().appName("TP SQPARKS SQL ").master("local[*]").getOrCreate();
        Dataset<Row> df1=ss.read().option("header",true).option("inferSchema",true).csv("products.csv");
       // df1.show();
        df1.printSchema();
        //df1.select(col("price").plus(2000)).show();
    }
}
