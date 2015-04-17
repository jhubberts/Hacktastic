
import java.util.Arrays;
import java.util.List;

/**
 * Created by hubberts on 4/16/15.
 */
public class MyTest {
    public static final String SUPER_SECURE_TOKEN = "017ad10646b25f76234d73aa89f3ac779e38b705";
    public static final String DEVICE_ID = "54ff70066672524820431267";
    public static final String RIG_ID = "rigId";
    public static final String OUNCES = "ounces";

    public static class IngredientPourer {
        private int rigId;
        private double ounces;

        public IngredientPourer( int rigId, double ounces ) {
            this.rigId = rigId;
            this.ounces = ounces;
        }

        @Override
        public String toString() {
            return String.format( "{\"rigId\":%d,\"ounces\":%.1f}", this.rigId, this.ounces );
        }
    }

    public static String serializeIngredients( List<IngredientPourer> pourers ) {
        StringBuilder sb = new StringBuilder( "[" );
        for( int i = 0; i < pourers.size() - 1 ; i++ ) {
            sb.append( pourers.get( i ).toString() + ',' );
        }
        sb.append( pourers.get( pourers.size() - 1 ) );
        sb.append( "]" );
        return sb.toString();
    }

    public static void sendIngredients( List<IngredientPourer> pourers ) throws Exception {
        String cmdString = "/Users/hubberts/scripts/request-drinks.sh " + serializeIngredients( pourers );
        System.out.println ( cmdString );
        Runtime.getRuntime().exec( cmdString );
    }

    public static void main(String[] args) throws Exception{
        IngredientPourer ip1 = new IngredientPourer( 0, 1.0f );
        IngredientPourer ip2 = new IngredientPourer( 1, 2.0f );
        sendIngredients( Arrays.asList( ip1, ip2) );
    }
}
