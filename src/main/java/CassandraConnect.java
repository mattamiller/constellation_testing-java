import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class CassandraConnect {

    public static void main(String[] args) {
        // Create the DseSession object:
        try (CqlSession session = CqlSession.builder()
                .withCloudSecureConnectBundle("/Users/matthew.miller/cassandra/secure-connect-mattsdb2.zip")
                .withAuthCredentials("mmiller","cassandra")
                .withKeyspace("keyspace1")
                .build()) {
            // Select the release_version from the system.local table:
            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            //Print the results of the CQL query to the console:
            if (row != null) {
                System.out.println(row.getString("release_version"));
            } else {
                System.out.println("An error occurred.");
            }
        }
        System.exit(0);
    }
}