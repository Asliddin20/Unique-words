
public class Separate2 {
    String aa = "!@#$%^*()_+=-<>?:;/!№[]1234567890.,";
    String cahrr(String s){
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='&'){
                int hold = i;
                String get="";
                boolean bool=true;
                do{
                    get += s.charAt(i);
                    if(s.charAt(i)==';'){
                        bool=false;
                        s=s.replaceFirst(get, " ");
                    }
                    i++;
                }while(bool);
                i=hold;
            }
            for(int j=0; j<aa.length(); j++){
                if(s.charAt(i)=='-'){
                    continue;
                }
                if(s.charAt(i)==aa.charAt(j)){
                    s = s.replace(aa.charAt(j), ' ');
                }
            }
        }

        return s;
    }

/*    public static void main(String[] args){
        Separate2 getCharss = new Separate2();
        System.out.println(getCharss.cahrr("Hel1o  mother&gakz;s fuckers"));
    }*/
}