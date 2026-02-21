import java.math.BigInteger;
import java.nio.file.*;
import java.util.*;

public class secretPolynomial {

    static class Point{
        BigInteger x,y;
        Point(BigInteger x, BigInteger y){
            this.x=x; this.y=y;
        }
    }

    static BigInteger lagrangeAtZero(List<Point> pts, int k){
        BigInteger secret = BigInteger.ZERO;

        for(int i=0;i<k;i++){
            BigInteger num = BigInteger.ONE;
            BigInteger den = BigInteger.ONE;

            for(int j=0;j<k;j++){
                if(i!=j){
                    num = num.multiply(pts.get(j).x.negate());
                    den = den.multiply(pts.get(i).x.subtract(pts.get(j).x));
                }
            }

            BigInteger term = pts.get(i).y.multiply(num);

            if(den.signum()<0){
                den = den.negate();
                term = term.negate();
            }

            term = term.divide(den);   // exact division
            secret = secret.add(term);
        }
        return secret;
    }

    public static void main(String[] args) throws Exception {

        String json = Files.readString(Paths.get("input.json"));
        int k = Integer.parseInt(json.split("\"k\":")[1].split("}")[0].trim());

        List<Point> pts = new ArrayList<>();

        String[] parts = json.split("\\},");
        for(String p: parts){
            if(p.contains("\"base\"")){
                String key = p.split(":")[0].replaceAll("[^0-9]","");
                String base = p.split("\"base\":")[1].split(",")[0].replaceAll("[^0-9]","");
                String value = p.split("\"value\":")[1].replaceAll("[^0-9a-zA-Z]","");

                BigInteger x = new BigInteger(key);
                BigInteger y = new BigInteger(value,Integer.parseInt(base));

                pts.add(new Point(x,y));
                if(pts.size()==k) break;
            }
        }

        // sort by x (VERY IMPORTANT)
        pts.sort(Comparator.comparing(p -> p.x));

        System.out.println("Secret = " + lagrangeAtZero(pts,k));
    }
}