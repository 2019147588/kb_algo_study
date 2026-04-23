package kyh.p5_1322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long X = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long Y = 0L;
        int count = 0;
        while(K>0){
            while((X&(1L << count))!= 0) count++;
            if((K&1)==1) Y |= (1L<<count);

            K >>= 1;
            count++;

        }
        System.out.println(Y);


    }
}
