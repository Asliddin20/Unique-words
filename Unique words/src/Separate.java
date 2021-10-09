import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;

public class Separate extends Separate2{
    Separate2 sep = new Separate2();
    Separate(){
    }
    public ArrayList<String> separate(String ss){
        String str = "";
        for(int e=0; e<ss.length(); e++) {
            for ( int i = 0; i < ss.length(); i++ ) {
                String a = "";
                if (ss.charAt(i) == '<') {
                    boolean bool = true;
                    int j = i;
                    do {
                        if (ss.charAt(j) == '>') {
                            bool = false;
                        }
                        a += ss.charAt(j);
                        str = ss.replaceFirst(a, "");
                        j++;
                    } while (bool);
                    ss = str + " ";
                }
            }
        }

        ArrayList<String> stt = new ArrayList<String>();
        String ab="";
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)!=' '){
                ab+=str.charAt(i);
            }
            else{
                stt.add(ab);
                ab= "";
            }
        }

        int count = 0;
        for(String x:stt){
            stt.set(count, sep.cahrr(x));
            count++;
        }

        stt.removeAll(Arrays.asList("", null));
        stt.removeAll(Arrays.asList(" ", null));
        return stt;
    }
/*
    public static void main(String[] args){
        Separate sepp = new Separate();
        System.out.println(sepp.separate("A@sd$id<4>"));
    }*/
}
