package ma.enset.tpsparksql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class IncidentsSparkApp {
    public static void main(String[] args) {
        SparkSession ss=SparkSession.builder().appName("IncidentsAppSparkSql").master("local[*]").getOrCreate();

        // Charger le fichier CSV en tant que DataFrame
        Dataset<Row> incidentsDF = ss.read().option("header",true).option("inferSchema",true).csv("incidents.csv");

        // Afficher le nombre d'incidents par service
        incidentsDF.groupBy("service").count().show();

        // Extraire l'année à partir de la colonne date
        incidentsDF = incidentsDF.withColumn("date", year(col("date")));

        // Afficher les deux années avec le plus d'incidents
        incidentsDF.groupBy("date").count().orderBy(desc("count")).show(2);

        ss.stop();
    }
}
