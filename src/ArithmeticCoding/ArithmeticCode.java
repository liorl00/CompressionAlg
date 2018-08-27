
package arifcod;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class ArifCod {
    private static float mass[]=new float[27];
    private static int lens=0;
    public static double eps=0.05;

    public static void cod()throws Exception{
        float low=0,high=1;
        int cur;
        for (int i=0;i<27;i++)
            mass[i]=0;
        Scanner scanner = new Scanner(new File("input.txt"));
        while (scanner.hasNext()){
            String str=scanner.next();
            lens+=str.length();
            for (int i=0;i<str.length();i++)
                if (str.charAt(i)=='_')
                    mass[26]++;
                else mass[Integer.valueOf(str.charAt(i))-Integer.valueOf('A')]++;
        }
        scanner.close();
        for (int i=0;i<27;i++)
            if (mass[i]!=0)
                mass[i]/=lens;
        int oldi=0;
        for (int i=0;i<27;i++)
            if (mass[i]!=0){
                oldi=i;
                break;
            }
        for (int i=oldi+1;i<27;i++)
            if (mass[i]!=0){
                mass[i]+=mass[oldi];
                oldi=i;
            }
        scanner = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter(new File("middle.txt"));
         while (scanner.hasNext()){
            String str=scanner.next();
            int curpos=1;
            cur=Integer.valueOf(str.charAt(0))-Integer.valueOf('A');
                    high=mass[cur];
                    for (int i=cur-1;i>=0;i--)
                        if (mass[i]!=0){
                            low=mass[i];
                            break;
                        }
            while (curpos<str.length()){
                float rl=0,rh=1;
                cur=Integer.valueOf(str.charAt(curpos))-Integer.valueOf('A');
                if (cur<0 || cur>26)
                    cur=26;
                rh=mass[cur];
                for (int i=cur-1;i>=0;i--)
                    if (mass[i]!=0){
                        rl=mass[i];
                        break;
                    }
                high=low+(high-low)*rh;
                low=low+(high-low)*rl;
                curpos++;
            }
            fw.write(String.valueOf(low));
         }
         fw.close();
         scanner.close();
    }
    
    public static void decod() throws Exception{
        Scanner scanner = new Scanner(new File("middle.txt"));
        FileWriter fw = new FileWriter(new File("output.txt"));
        float rl=0,rh=1;
        float numb=Float.valueOf(scanner.next());
        for (int j=0;j<lens;j++){
            for (int i=0;i<26;i++)
                if (mass[i]>=numb+eps){
                    rl=0;
                    for (int k=i-1;k>=0;k--)
                        if (mass[k]!=0){
                            rl=mass[k];
                            break;
                        }
                    rh=mass[i];
                    fw.write((char)(i+'A'));
                    break;
                }
            numb=(numb-rl)/(rh-rl);
        }
        fw.close();
    }
    
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        cod(); 
        decod();
    }
    
}
