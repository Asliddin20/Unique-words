import java.io.*;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.*;

class TextReader extends Separate{
    public static void main(String[] args){
        ArrayList<String> strB = new ArrayList<>();
        Hashtable<String, Integer> map = new Hashtable<String, Integer>();
        Separate separate = new Separate();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the file direction: ");
        String sc = scan.next();

        String a;
        try {
            BufferedReader write = new BufferedReader(
                    new FileReader(sc));

            while((a = write.readLine()) != null){

                strB.add(a);
            }
            write.close();
        }catch(Exception e){
            return;
        }

        String d="";
        for(int i=0; i<strB.size(); i++){
            d+=strB.get(i) + " ";
        }
        ArrayList<String> neew = new ArrayList<String>();
        neew = separate.separate(d);

        int ab=0;
        for(String x:neew){
            x = x.replaceAll(" ", "");
            neew.set(ab, x);
            ab++;
        }

        int[] integer = new int[neew.size()];
        for(int i=0; i<neew.size(); i++){
            int g = 0;
            for(int j=i; j>=0; j--){
                if(neew.get(i).equals(neew.get(j))) g++;
            }
            map.put(neew.get(i), g);
            integer[i] = g;

        }

        ArrayList<Map.Entry<?, Integer>> l = new ArrayList(map.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<?, Integer>>(){

            public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }});


//todo ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Connecting to DATABASE AND STORE DATA ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/text_output", "root", "MySQL/me1");
            PreparedStatement prepare = null;
            //TODO DELETE ALL DATA...............
            /*prepare = connection.prepareStatement("delete from txt_output text");
            prepare.execute();*/

            prepare = connection.prepareStatement("Insert into txt_output   (text, number) VALUES(?,?)");
            for( int ij=l.size()-1; ij>-1; ij--) {
                StringBuilder st= new StringBuilder();
                String sd = l.get(ij).toString();
                int inn=0;
                for(int ii=0; ii<sd.length(); ii++){
                    if(sd.charAt(ii)=='=') {
                        StringBuilder it= new StringBuilder();
                        for(int j=ii+1; j<sd.length(); j++){
                            it.append(sd.charAt(j));
                        }
                        inn = Integer.parseInt(it.toString());
                        break;
                    }
                    st.append(sd.charAt(ii));

                }
                prepare.setString(1, st.toString());
                prepare.setInt(2,inn);
                prepare.execute();

            }
            prepare = connection.prepareStatement("Select * from txt_output");
            ResultSet rs = prepare.executeQuery();

            while(rs.next()){
                String st = rs.getString("text");
                int in = rs.getInt("number");
                System.out.println(st + " - " + in);
            }



            prepare.close();
            connection.close();

        }catch(Exception ex){

            ex.printStackTrace();
        }
    }
}
